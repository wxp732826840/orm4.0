package com.wxp.spi;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 用户权限表信息biao
 * user_id
 * identity_type 登录类型（手机号 邮箱 用户名）或第三方应用名称（微信 微博等）
 * identifier 标识（手机号 邮箱 用户名或第三方应用的唯一标识）
 * credential 密码凭证（站内的保存密码，站外的不保存或保存token）
 * */
@Data
public class UserAuths {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String useId;
    private String identityType;
    private String identifier;
    private String credential;

}
