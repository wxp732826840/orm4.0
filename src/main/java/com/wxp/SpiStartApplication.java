package com.wxp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.wxp.**.mapper")
public class SpiStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpiStartApplication.class, args);
    }

}
