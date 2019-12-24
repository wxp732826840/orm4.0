package com.wxp;

import com.wxp.bas.entity.User;
import com.wxp.equator.FieldBaseEquator;
import com.wxp.equator.FieldInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
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
        user1.setMm(new BigDecimal(123.00));
        user1.setNn(new BigDecimal(123.45));
        user1.setAa(new Date());



        User user2 = new User();
        user2.setAge(56);
        user2.setEmail("7328268401");
        user2.setName("wxp");
        user2.setMm(new BigDecimal(123.0000));
        user2.setNn(new BigDecimal(123.46));
        user2.setAa(new Date());
        FieldBaseEquator fieldBaseEquator = new FieldBaseEquator();
        fieldBaseEquator.setFiledStrategys(new CustomeEquatorStrategy());
        List<FieldInfo> diffFields = fieldBaseEquator.getDiffFields(user1, user2);
        diffFields.forEach(x->log.info("输出信息：{}->{}",x.getFieldName(),x.getFieldNameInfo()));

    }
}
