package com.wxp.bas.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wxp.CRUDController;
import com.wxp.bas.mapper.UserMapper;
import com.wxp.utils.common.base.mybatis.WrapperContext;
import com.wxp.bas.entity.User;
import com.wxp.bas.service.IUserService;
import com.wxp.utils.common.base.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wxp
 * @since 2019-12-13
 */
@RestController
@RequestMapping("/bas/user")
@Slf4j
public class UserController extends CRUDController<User> {

    @Autowired
    private IUserService userService;

}
