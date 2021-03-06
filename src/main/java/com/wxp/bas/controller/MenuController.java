package com.wxp.bas.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wxp.utils.common.base.mybatis.WrapperContext;
import com.wxp.bas.entity.Menu;
import com.wxp.bas.service.IMenuService;
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
@RequestMapping("/bas/menu")
@Slf4j
public class MenuController {

    @Autowired
    private IMenuService menuService;


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public IPage query(HttpServletRequest request) {
        QueryWrapper queryWrapper = WrapperContext.buildWrapperFromHttpRequest(request);
        IPage iPage = WrapperContext.buildPage(request);
        menuService.page(iPage, queryWrapper);
        return iPage;
    }

    @RequestMapping(value = "/view/{id}")
    public Menu view(@PathVariable String id) {
        Menu menu = menuService.getById(id);
        return menu;
    }

    @RequestMapping(value = "/edit/{id}")
    public Menu edit(@PathVariable String id) {
        Menu menu = menuService.getById(id);
        return menu;
    }

    @RequestMapping(value = "/save")
    public Result edit(Menu menu) {
        if (menuService.save(menu)) {
            Result.success(menu);
        } else {
            Result.failure(Result.FAILTURE, "保存错误");
        }
        return Result.success(menu);
    }
}
