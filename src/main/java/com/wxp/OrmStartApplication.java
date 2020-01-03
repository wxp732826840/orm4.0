package com.wxp;

import org.minbox.framework.logging.spring.context.annotation.client.EnableLoggingClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wxp.**.mapper")
@EnableLoggingClient
public class OrmStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrmStartApplication.class, args);
    }

}
