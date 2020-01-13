package com.wxp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxp.utils.common.base.mybatis.WrapperContext;
import com.wxp.utils.common.base.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

public abstract class CRUDController<T> {

    @Autowired
    private IService<T> iService;


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public IPage query(HttpServletRequest request) {
        QueryWrapper queryWrapper = WrapperContext.buildWrapperFromHttpRequest(request);
        IPage iPage = WrapperContext.buildPage(request);
        iService.page(iPage, queryWrapper);
        return iPage;
    }

    @RequestMapping(value = "/get/{id}")
    public <T> T view(@PathVariable String id) {
        return (T) iService.getById(id);
    }


    @RequestMapping(value = "/save")
    public Result save(T t) {
        if (iService.save(t)) {
            Result.success(t);
        } else {
            Result.failure(Result.FAILTURE, "保存错误");
        }
        return Result.success(t);
    }

}
