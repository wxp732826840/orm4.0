package com.wxp.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("t_user")
//@KeySequence("customIdGenerator")
public class User {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    //@TableField("name_")
    private String name;

    //@TableField("age_")
    private Integer age;

    //@TableField("email_")
    private String email;
}

