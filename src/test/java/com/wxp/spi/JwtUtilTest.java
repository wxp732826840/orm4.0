package com.wxp.spi;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class JwtUtilTest {


   /* @Test
    public void  test(){
        User user = new User();
        user.setId("20190905111111");
        user.setUsername("wxp");
        user.setPassword("1111111");
        log.info(JwtUtil.createJWT(user));
    }*/

    @Test
    public void test1(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3eHAiLCJwYXNzd29yZCI6IjExMTExMTEiLCJpZCI6IjIwMTkwOTA1MTExMTExIiwiZXhwIjoxNTY4OTQyODkyLCJpYXQiOjE1Njc2NDY4OTIsImp0aSI6IjY2ZTRhZGZiLTE2NjktNDlhZS1hMjY3LTZhYTk4NjBjMDJhNyIsInVzZXJuYW1lIjoid3hwIn0.DdOx3z57ojvXWRdsD8XkRH76YmUGg6ROnQEQ2FhPYCM";
        log.info(JwtUtil.parseJWT(token).getId());
        log.info(JwtUtil.parseJWT(token).get("username").toString());

    }
}
