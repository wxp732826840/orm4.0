package com.wxp;

import com.wxp.bas.entity.User;
import com.wxp.bas.entity.UserDto;
import com.wxp.utils.copy.CGlibMapper;
import com.wxp.utils.equator.FieldBaseEquator;
import com.wxp.utils.equator.FieldInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class EquatorTest {


    @Test
    public void test1(){
        User user1 = new User();
        user1.setAge(56);
        user1.setEmail("732826840");
        user1.setName("wxp");
        //user1.setMm(new BigDecimal(123.00));
        //user1.setNn(new BigDecimal(123.45));
        //user1.setAa(new Date());



        User user2 = new User();
        user2.setAge(56);
        user2.setEmail("7328268401");
        user2.setName("wxp");
        //user2.setMm(new BigDecimal(123.0000));
        //user2.setNn(new BigDecimal(123.46));
        //user2.setAa(new Date());
        FieldBaseEquator fieldBaseEquator = new FieldBaseEquator();
        fieldBaseEquator.setFiledStrategys(new CustomeEquatorStrategy());
        List<FieldInfo> diffFields = fieldBaseEquator.getDiffFields(user1, user2);
        diffFields.forEach(x->log.info("输出信息：{}->{}",x.getFieldName(),x.getFieldNameInfo()));

    }

    @Test
    public void test2(){
        User user1 = new User();
        user1.setAge(56);
        user1.setEmail("732826840");
        user1.setName("wxp");
        //user1.setMm(new BigDecimal(123.00));
        //user1.setNn(new BigDecimal(123.45));
        //user1.setAa(new Date());

        UserDto user = CGlibMapper.mapper(user1, UserDto.class);
        log.info("user info {}",user.getName());
        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("2");
        user.setList(list);
        User user2 = CGlibMapper.mapper(user, User.class);
        log.info("user info {}",user2.getName());
    }
}
