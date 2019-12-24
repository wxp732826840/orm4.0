package com.wxp.utils.common.base;

import com.wxp.utils.common.base.ResponseResultBody;
import com.wxp.utils.common.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Map;

@ControllerAdvice
@ResponseResultBody
@Slf4j
public class GlobalExceptionHandler {

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public Result<Map<String, Object>> runtimeExceptionHandler(RuntimeException ex) {
        log.error("RuntimeException 错误信息", ex);
        return Result.failure(Integer.valueOf(1), ex.toString());
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public Result<Map<String, Object>> nullPointerExceptionHandler(NullPointerException ex) {
        log.error("NullPointerException 错误信息", ex);
        return Result.failure(Integer.valueOf(2), ex.toString());
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public Result<Map<String, Object>> classCastExceptionHandler(ClassCastException ex) {
        log.error("ClassCastException 错误信息", ex);
        return Result.failure(Integer.valueOf(3), ex.toString());
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public Result<Map<String, Object>> iOExceptionHandler(IOException ex) {
        log.error("IOException 错误信息", ex);
        return Result.failure(Integer.valueOf(4), ex.toString());
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public Result<Map<String, Object>> noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        log.error("NoSuchMethodException 错误信息", ex);
        return Result.failure(Integer.valueOf(5), ex.toString());
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Result<Map<String, Object>> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        log.error("IndexOutOfBoundsException 错误信息", ex);
        return Result.failure(Integer.valueOf(6), ex.toString());
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Result<Map<String, Object>> requestNotReadable(HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException 错误信息", ex);
        return Result.failure(Integer.valueOf(7), ex.toString());
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    public Result<Map<String, Object>> requestTypeMismatch(TypeMismatchException ex) {
        log.error("TypeMismatchException 错误信息", ex);
        return Result.failure(Integer.valueOf(8), ex.toString());
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public Result<Map<String, Object>> requestMissingServletRequest(MissingServletRequestParameterException ex) {
        log.error("MissingServletRequestParameterException 错误信息", ex);
        return Result.failure(Integer.valueOf(9), ex.toString());
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result<Map<String, Object>> request405(HttpRequestMethodNotSupportedException ex) {
        log.error("HttpRequestMethodNotSupportedException 错误信息", ex);
        return Result.failure(Integer.valueOf(10), ex.toString());
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public Result<Map<String, Object>> request406(HttpMediaTypeNotAcceptableException ex) {
        log.error("HttpMediaTypeNotAcceptableException 错误信息", ex);
        return Result.failure(Integer.valueOf(11), ex.toString());
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public Result<Map<String, Object>> server500(RuntimeException ex) {
        log.error("HttpMessageNotWritableException 错误信息", ex);
        return Result.failure(Integer.valueOf(12), ex.toString());
    }

    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    public Result<Map<String, Object>> requestStackOverflow(StackOverflowError ex) {
        log.error("StackOverflowError 错误信息", ex);
        return Result.failure(Integer.valueOf(13), ex.toString());
    }

    //除数不能为0
    @ExceptionHandler({ArithmeticException.class})
    public Result<Map<String, Object>> arithmeticException(ArithmeticException ex) {
        log.error("ArithmeticException 错误信息", ex);
        return Result.failure(Integer.valueOf(14), ex.toString());
    }


    //其他错误
    @ExceptionHandler({Exception.class})
    public Result<Map<String, Object>> exception(Exception ex) {
        log.error("Exception 错误信息", ex);
        return Result.failure(Integer.valueOf(15), ex.toString());
    }

}

