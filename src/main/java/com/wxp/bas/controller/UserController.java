package com.wxp.bas.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wxp.utils.common.WrapperContext;
import com.wxp.bas.entity.User;
import com.wxp.bas.service.IUserService;
import com.wxp.utils.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private IUserService userService;

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

}
