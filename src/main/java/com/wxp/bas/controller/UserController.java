package com.wxp.bas.controller;

import com.wxp.CRUDController;
import com.wxp.bas.entity.User;
import com.wxp.bas.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
