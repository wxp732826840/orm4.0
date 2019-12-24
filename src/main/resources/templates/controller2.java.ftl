package ${package.Controller};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wxp.utils.common.base.mybatis.WrapperContext;
import com.wxp.bas.entity.${entity};
import com.wxp.bas.service.I${entity}Service;
import com.wxp.utils.common.base.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
@Slf4j
public class ${table.controllerName} {
</#if>

    @Autowired
    private I${entity}Service ${table.entityPath}Service;


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public IPage query(HttpServletRequest request) {
        QueryWrapper queryWrapper = WrapperContext.buildWrapperFromHttpRequest(request);
        IPage iPage = WrapperContext.buildPage(request);
        ${table.entityPath}Service.page(iPage, queryWrapper);
        return iPage;
    }

    @RequestMapping(value = "/view/{id}")
    public ${entity} view(@PathVariable String id) {
        ${entity} ${table.entityPath} = ${table.entityPath}Service.getById(id);
        return ${table.entityPath};
    }

    @RequestMapping(value = "/edit/{id}")
    public ${entity} edit(@PathVariable String id) {
        ${entity} ${table.entityPath} = ${table.entityPath}Service.getById(id);
        return ${table.entityPath};
    }

    @RequestMapping(value = "/save")
    public Result edit(${entity} ${table.entityPath}) {
        if (${table.entityPath}Service.save(${table.entityPath})) {
            Result.success(${table.entityPath});
        } else {
            Result.failure(Result.FAILTURE, "保存错误");
        }
        return Result.success(${table.entityPath});
    }
}
</#if>
