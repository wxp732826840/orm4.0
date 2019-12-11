package com.wxp.bas.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxp.WrapperContext;
import com.wxp.bas.entity.User;
import com.wxp.bas.service.IUserService;
import com.wxp.mybatis.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wxp
 * @since 2019-12-10
 */
@RestController
@RequestMapping("/bas/user")
@Slf4j
public class UserController {
    private static final HashMap<String, Object> INFO;


    @Autowired
    private IUserService userService;

    static {
        INFO = new HashMap<>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return INFO;
    }

    @GetMapping("/hello1")
    public Map<String, Object> hello1() {
        int m = 1 / 0;
        return INFO;
    }

    @GetMapping("/result")
    public Result<Map<String, Object>> helloResult() {
        return Result.success(INFO);
    }

    @GetMapping("/hello2")
    public Object hello2(User user6) throws JsonProcessingException {
        User insertUser = new User();
        insertUser.setAge(23);
        //insertUser.setId(SnowflakeIdWorker.genetateIdStr());
        insertUser.setName("22222");
        //userMapper.insert(insertUser);
        //User user = userMapper.finds(insertUser.getId());
        //List<User> list = userMapper.selectList(null);
        //list.forEach(x -> System.out.println(x.getId()));
        //System.out.println(user.getId());
        System.out.println("===========分页================");
        IPage<User> userPage = new Page<>(2, 2);//参数一是当前页，参数二是每页个数
        // userPage = userMapper.selectPage(userPage, null);
        /*
        List<User> list1 = userPage.getRecords();
        System.out.println("==========="+userPage.getTotal());
        for (User user1 : list) {
            System.out.println(user1);
        }*/
        IPage<User> userPage2 = new Page<>(2, 10);
        //userPage2 = userMapper.findList(userPage2);
        log.info("======userPage======" + new ObjectMapper().writeValueAsString(userPage2));


        return userPage;
    }

    @GetMapping("/hello3")
    public String hello3(HttpServletRequest request) {
        QueryWrapper queryWrapper = WrapperContext.buildWrapperFromHttpRequest(request);
        IPage<User> userPage = WrapperContext.buildPage(request);
        userPage = userService.page(userPage, queryWrapper);
        return "hello World";
    }

    @GetMapping("/hello4")
    public List hello4() {
        List<String> list = new ArrayList();
        list.add("hello World");
        return list;
    }


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public IPage query(HttpServletRequest request) {
        QueryWrapper queryWrapper = WrapperContext.buildWrapperFromHttpRequest(request);
        IPage iPage = WrapperContext.buildPage(request);
        userService.page(iPage, queryWrapper);
        return iPage;
    }

    @RequestMapping(value = "/view/{id}")
    public User view(@PathVariable String id) {
        User user = userService.getById(id);
        return user;
    }


    @RequestMapping(value = "/edit/{id}")
    public User edit(@PathVariable String id) {
        User user = userService.getById(id);
        return user;
    }

    @RequestMapping(value = "/save")
    public Result edit(User user) {
        if (userService.save(user)) {
            Result.success(user);
        } else {
            Result.failure(Result.FAILTURE, "保存错误");
        }
        return Result.success(user);

    }



    /*@RequestMapping("/query")
    public List query(String name, HttpServletRequest request, HttpServletResponse response) {
        log.info(request.toString());
        QueryWrapper queryWrapper = WrapperContext.buildWrapperFromHttpRequest(request);
        IPage<User> userPage = WrapperContext.buildPage(request);
        userPage = userMapper.selectPage(userPage, queryWrapper);
        return userMapper.selectList(queryWrapper);
    }*/

   /* @RequestMapping("/query1")
    public IPage query1(String name, HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper queryWrapper = WrapperContext.buildWrapperFromHttpRequest(request);
        Map<String, Object> stringObjectMap = WrapperContext.buildMapWithPrefix(request);
        stringObjectMap.forEach((x, y) -> log.info("key:[{}] ,value:[{}]", x, y));
        IPage<User> userPage = WrapperContext.buildPage(request);
        return userMapper.selectPage(userPage, queryWrapper);
    }*/

}
