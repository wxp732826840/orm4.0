package com.wxp.bas.entity;

import com.wxp.utils.equator.FieldNameInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UserDto {

    private String name;

    private Integer age;

    @FieldNameInfo("邮箱")
    private String email;

    private BigDecimal mm;

    private List<String> list;


}
