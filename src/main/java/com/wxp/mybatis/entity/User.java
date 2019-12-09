package com.wxp.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("t_user")
//@KeySequence("customIdGenerator")
public class User {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private Integer age;
    private String email;
    private String password;
}

