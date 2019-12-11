package com.wxp.utils.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxp.utils.tools.CommonUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class WrapperContext {
    public static String prefix = "_";
    public static Long CURRENT_PAGE = 1L;
    public static String CURRENT_PAGE_STR = "current";
    public static Long SIZE_PAGE = 10L;
    public static String SIZE_PAGE_STR = "size";


    public static QueryWrapper buildWrapperFromHttpRequest(@NotNull HttpServletRequest request) {
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames != null && parameterNames.hasMoreElements()) {
            String params = String.valueOf(parameterNames.nextElement());
            if (StringUtils.isNotBlank(prefix) && params.startsWith(prefix)) {
                String unprefix = params.substring(prefix.length());
                String[] parameterValues = request.getParameterValues(params);
                if (parameterValues.length == 0) {

                } else if (parameterValues == null) {
                    queryWrapper = WrapperContextFilter.buildWrapper(queryWrapper, unprefix, null);
                } else if (parameterValues.length > 1) {
                    queryWrapper = WrapperContextFilter.buildWrapper(queryWrapper, unprefix, Arrays.asList(parameterValues));
                } else {
                    queryWrapper = WrapperContextFilter.buildWrapper(queryWrapper, unprefix, parameterValues[0]);
                }
            }
        }
        return queryWrapper;
    }

    public static IPage buildPage(@NotNull HttpServletRequest request) {
        Long current = CURRENT_PAGE;
        Long size = SIZE_PAGE;
        if (StringUtils.isNotBlank(request.getParameter(CURRENT_PAGE_STR)) && CommonUtils.isNumeric(request.getParameter(CURRENT_PAGE_STR))) {
            current = Long.valueOf(request.getParameter(CURRENT_PAGE_STR));
        }
        if (StringUtils.isNotBlank(request.getParameter(SIZE_PAGE_STR)) && CommonUtils.isNumeric(request.getParameter(SIZE_PAGE_STR))) {
            size = Long.valueOf(request.getParameter(SIZE_PAGE_STR));
        }
        return new Page<>(current, size);
    }


    /***
     * 带有前缀的map 转换
     *  例 es_name
     * @param request
     * @return
     */
    public static Map<String, Object> buildMapWithPrefix(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        String params = String.valueOf(parameterNames.nextElement());
        if (StringUtils.isNotBlank(prefix) && params.startsWith(prefix)) {
            String unprefix = params.substring(prefix.length());
            map.put(unprefix, request.getParameter(params));
        }
        return map;
    }



}
