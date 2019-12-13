package com.wxp.bas.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wxp.utils.common.WrapperContext;
import com.wxp.bas.entity.Role;
import com.wxp.bas.service.IRoleService;
import com.wxp.utils.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wxp
 * @since 2019-12-13
 */
@RestController
@RequestMapping("/bas/role")
@Slf4j
public class RoleController {

    @Autowired
    private IRoleService roleService;


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public IPage query(HttpServletRequest request) {
        QueryWrapper queryWrapper = WrapperContext.buildWrapperFromHttpRequest(request);
        IPage iPage = WrapperContext.buildPage(request);
        roleService.page(iPage, queryWrapper);
        return iPage;
    }

    @RequestMapping(value = "/view/{id}")
    public Role view(@PathVariable String id) {
        Role role = roleService.getById(id);
        return role;
    }

    @RequestMapping(value = "/edit/{id}")
    public Role edit(@PathVariable String id) {
        Role role = roleService.getById(id);
        return role;
    }

    @RequestMapping(value = "/save")
    public Result edit(Role role) {
        if (roleService.save(role)) {
            Result.success(role);
        } else {
            Result.failure(Result.FAILTURE, "保存错误");
        }
        return Result.success(role);
    }
}
