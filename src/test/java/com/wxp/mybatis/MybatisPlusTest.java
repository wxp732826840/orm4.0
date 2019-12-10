package com.wxp.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxp.SpiStartApplication;
import com.wxp.bas.entity.User;
import com.wxp.bas.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpiStartApplication.class)
@Slf4j
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        /*System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);

        User user = userMapper.finds(1L);
        System.out.println("user:="+user);*/
    }

    @Test
    public void testQueryWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", "7328@163.com");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(x -> log.info("链表对应id    ：" + x.getId()));
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", "20191205094218362381694248034304");
        List<User> userList1 = userMapper.selectByMap(map);
        userList1.forEach(x -> log.info("map对应id    ：" + x.getId()));
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(map1 -> {
            map1.forEach((x, y) -> {
                log.info("key:" + x + "    value:" + y);
            });
        });
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.eq("id", "362108646118793216");
        //IPage<User> page = new Page<User>(1, 5);
        IPage<Map<String, Object>> iPage = new Page<>(1, 5);
        iPage = userMapper.selectMapsPage(iPage, queryWrapper1);
        log.info("total:=" + iPage.getTotal());
    }
}
