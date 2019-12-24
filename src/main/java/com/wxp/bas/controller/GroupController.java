package com.wxp.bas.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wxp.utils.common.base.WrapperContext;
import com.wxp.bas.entity.Group;
import com.wxp.bas.service.IGroupService;
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
@RequestMapping("/bas/group")
@Slf4j
public class GroupController {

    @Autowired
    private IGroupService groupService;


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public IPage query(HttpServletRequest request) {
        QueryWrapper queryWrapper = WrapperContext.buildWrapperFromHttpRequest(request);
        IPage iPage = WrapperContext.buildPage(request);
        groupService.page(iPage, queryWrapper);
        return iPage;
    }

    @RequestMapping(value = "/view/{id}")
    public Group view(@PathVariable String id) {
        Group group = groupService.getById(id);
        return group;
    }

    @RequestMapping(value = "/edit/{id}")
    public Group edit(@PathVariable String id) {
        Group group = groupService.getById(id);
        return group;
    }

    @RequestMapping(value = "/save")
    public Result edit(Group group) {
        if (groupService.save(group)) {
            Result.success(group);
        } else {
            Result.failure(Result.FAILTURE, "保存错误");
        }
        return Result.success(group);
    }
}
