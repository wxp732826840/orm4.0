package com.wxp.spi;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
public class TestFile {

    @Test
    public void test() throws IOException {


        File file1 = new File("D:\\1.pdf");
        File file2 = new File("D:\\2.pdf");
        File file3 = new File("D:\\3.pdf");
        File file4 = new File("D:\\4.pdf");

        long startTime1=System.currentTimeMillis();
        CopyFileByte(file1,file2);
        long endTime1=System.currentTimeMillis();
        log.info("传统方式时间间隔{}",endTime1-startTime1);

        long startTime2=System.currentTimeMillis();
        FileChannelCopy(file1,file3);
        long endTime2=System.currentTimeMillis();
        log.info("零拷贝方式时间间隔{}",endTime2-startTime2);

        long startTime3=System.currentTimeMillis();
        MappedByte2(file1,file4);
        long endTime3=System.currentTimeMillis();
        log.info("内存映射方式时间间隔{}",endTime3-startTime3);


        //执行结果执行文件是1.2G
       /* 09:56:07.680 [main] INFO com.wxp.spi.TestFile - 传统方式时间间隔22651
        09:56:11.050 [main] INFO com.wxp.spi.TestFile - 零拷贝方式时间间隔3151
        09:56:22.953 [main] INFO com.wxp.spi.TestFile - 内存映射方式时间间隔11887*/


    }


    /**
    * 文件 磁盘  -> os 内核 -> 用户态
    * 原来内存映射文件的效率比标准IO高的重要原因就是因为少了把数据拷贝到OS内核缓冲区这一步（可能还少了native堆中转这一步）
    * */
    /**
     * 为什么要搞一个内核IO缓冲区把原本只需一次拷贝数据的事情搞成需要2次数据拷贝呢？
     * 减少磁盘的IO操作，为了提高性能。磁盘IO操作的速度比直接访问内存慢了好几个数量级，
     * 所以OS根据局部性原理会在一次 read()系统调用过程中预读更多的文件数据缓存在内核IO缓冲区中，
     * 当继续访问的文件数据在缓冲区中时便直接拷贝数据到进程私有空间，避免了再次的低效率磁盘IO操作
     * */

    /**
     *
     * BufferedInputStream 会根据情况自动为我们预读更多的字节数据到它自己维护的一个内部字节数组缓冲区中，
     * 这样我们便可以减少系统调用次数，从而达到其缓冲区的目的。
     * 所以要明确的一点是 BufferedInputStream 的作用不是减少 磁盘IO操作次数（这个OS已经帮我们做了），
     * 而是通过减少系统调用次数来提高性能的
     *
     * */


    /**
     * jdk1.4后，引入了文件通道的概念，还引入了文件内存映射的概念
     * */



    /**
     * 一次读取一个字节
     * */
    private void CopyFile(File fromFile,File toFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fromFile);

        FileOutputStream fileOutputStream = new FileOutputStream(toFile);
        int len;
        // 一次读取一个字节,每读取一个字节都要实现一次与硬盘的交互操作
        while ((len = fileInputStream.read()) != -1) {
            fileOutputStream.write(len);
        }

    }
    /**
     *每次读取1024个字节
     *
     * */

    private void CopyFileByte(File fromFile,File toFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fromFile);

        FileOutputStream fileOutputStream = new FileOutputStream(toFile);
        int len;
        byte[] bs = new byte[1024];
        //这里添加了一个缓存数组，每次从硬盘读取1024个字节，也就是说，每读取1024个字节才与硬盘实现一次交互
        while ((len = fileInputStream.read(bs)) != -1) {
            fileOutputStream.write(bs, 0, len);
        }

    }





    private void mapCopy(){}

    /**
     * 使用缓存
     * */
    private void bufferedCopy(File fromFile,File toFile) throws IOException {
        final int BUFFER_SIZE=1024;
        //BufferedInputStream 的作用不是减少磁盘IO操作次数（这个OS已经帮我们做了,OS内核缓存区）,而是减少系统调用次数来提高性能的
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fromFile));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(toFile)); //默认有8M的缓存
        byte[] buf = new byte[BUFFER_SIZE];//这是直接缓存区（下面是分配缓存区，分配缓存区）
        //分配缓冲区的大小
        //ByteBuffer buf = ByteBuffer.allocate(1024);        //分配缓冲区的大小

        //先从硬盘读出8M到缓存中。然后read，这里的read并不是从硬盘中读取，而是从那8M缓存（内存）中读取，自然要比从硬盘中快得多。8M缓存用完后又会从硬盘补充（也就是说，一次从硬盘获取8M字节的数据） 。每8M与硬盘交互一次
        //注意这里的磁盘可能是OS内核缓存区，也可能是磁盘IO
        while (bufferedInputStream.read(buf) != -1){
            bufferedOutputStream.write(buf);
        }
    }

    /**
     * 内存映射 通道方式
     * 内存映射文件能让你创建和修改那些因为太大而无法放入内存的文件
     *
     * */



    private void MappedByte1(File fromFile,File toFile) throws IOException {
        //通过传统方式打开通道
        FileChannel fileChannelInput = new FileInputStream(fromFile).getChannel();
        //直接打开通道的方式
        //FileChannel.open(Paths.get("E:\\ 1.avi"),StandardOpenOption.READ);

        MappedByteBuffer mappedByteBufferInput = fileChannelInput.map(FileChannel.MapMode.READ_ONLY, 0, fileChannelInput.size());

        //通过传统方式打开通道
        FileChannel fileChannelOutput = new FileOutputStream(toFile).getChannel();
        //zhijie打开通道的方式
        //FileChannel outChennel = FileChannel.open(Paths.get("E:\\ 12.avi"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE_NEW);


        MappedByteBuffer mappedByteBufferOutput = fileChannelOutput.map(FileChannel.MapMode.READ_WRITE, 0, fileChannelOutput.size());
        byte[] dst = new byte[mappedByteBufferInput.limit()];
        mappedByteBufferInput.get(dst);
        mappedByteBufferOutput.put(dst);
    }


    private void MappedByte2(File fromFile,File toFile) throws IOException {
        //通过传统方式打开通道
        FileChannel fileChannelInput = new FileInputStream(fromFile).getChannel();
        MappedByteBuffer mappedByteBufferInput = fileChannelInput.map(FileChannel.MapMode.READ_ONLY, 0, fileChannelInput.size());
        //通过传统方式打开通道
        FileChannel fileChannelOutput = new FileOutputStream(toFile).getChannel();
        fileChannelOutput.write(mappedByteBufferInput);
    }






    /****
     * 零拷贝方式
     * 文件较大，读写较慢，追求速度
     * JVM内存不足，不能加载太大数据
     * 内存宽带不足，
     * 不通过缓存区，直接在通道内完成
     * **/
    private void FileChannelCopy(File fromFile, File toFile) throws IOException {
        FileChannel fromFileChannel = new FileInputStream(fromFile).getChannel();
        FileChannel toFileChannel = new FileOutputStream(toFile).getChannel();
        fromFileChannel.transferTo(0,fromFileChannel.size(),toFileChannel);

    }





}
