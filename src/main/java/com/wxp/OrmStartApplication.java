package com.wxp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wxp.**.mapper")
public class OrmStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrmStartApplication.class, args);
    }

}
