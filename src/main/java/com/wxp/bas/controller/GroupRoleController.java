package com.wxp.bas.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wxp.utils.common.base.WrapperContext;
import com.wxp.bas.entity.GroupRole;
import com.wxp.bas.service.IGroupRoleService;
import com.wxp.utils.common.base.Result;
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
@RequestMapping("/bas/groupRole")
@Slf4j
public class GroupRoleController {

    @Autowired
    private IGroupRoleService groupRoleService;


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public IPage query(HttpServletRequest request) {
        QueryWrapper queryWrapper = WrapperContext.buildWrapperFromHttpRequest(request);
        IPage iPage = WrapperContext.buildPage(request);
        groupRoleService.page(iPage, queryWrapper);
        return iPage;
    }

    @RequestMapping(value = "/view/{id}")
    public GroupRole view(@PathVariable String id) {
        GroupRole groupRole = groupRoleService.getById(id);
        return groupRole;
    }

    @RequestMapping(value = "/edit/{id}")
    public GroupRole edit(@PathVariable String id) {
        GroupRole groupRole = groupRoleService.getById(id);
        return groupRole;
    }

    @RequestMapping(value = "/save")
    public Result edit(GroupRole groupRole) {
        if (groupRoleService.save(groupRole)) {
            Result.success(groupRole);
        } else {
            Result.failure(Result.FAILTURE, "保存错误");
        }
        return Result.success(groupRole);
    }
}
