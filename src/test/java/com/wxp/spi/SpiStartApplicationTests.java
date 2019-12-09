package com.wxp.spi;

import com.wxp.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiStartApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
       /* System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);

        User user = userMapper.finds(1L);
        System.out.println("user：="+user);*/
    }

}
