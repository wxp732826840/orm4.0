package com.wxp.bas.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wxp.utils.common.base.mybatis.WrapperContext;
import com.wxp.bas.entity.RoleMenu;
import com.wxp.bas.service.IRoleMenuService;
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
 *  前端控制器
 * </p>
 *
 * @author wxp
 * @since 2019-12-13
 */
@RestController
@RequestMapping("/bas/roleMenu")
@Slf4j
public class RoleMenuController {

    @Autowired
    private IRoleMenuService roleMenuService;


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public IPage query(HttpServletRequest request) {
        QueryWrapper queryWrapper = WrapperContext.buildWrapperFromHttpRequest(request);
        IPage iPage = WrapperContext.buildPage(request);
        roleMenuService.page(iPage, queryWrapper);
        return iPage;
    }

    @RequestMapping(value = "/view/{id}")
    public RoleMenu view(@PathVariable String id) {
        RoleMenu roleMenu = roleMenuService.getById(id);
        return roleMenu;
    }

    @RequestMapping(value = "/edit/{id}")
    public RoleMenu edit(@PathVariable String id) {
        RoleMenu roleMenu = roleMenuService.getById(id);
        return roleMenu;
    }

    @RequestMapping(value = "/save")
    public Result edit(RoleMenu roleMenu) {
        if (roleMenuService.save(roleMenu)) {
            Result.success(roleMenu);
        } else {
            Result.failure(Result.FAILTURE, "保存错误");
        }
        return Result.success(roleMenu);
    }
}
