package com.wxp.bas.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.wxp.utils.equator.FieldNameInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wxp
 * @since 2019-12-10
 */
@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
//@TableName("bas_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    private String name;

    private Integer age;

    @FieldNameInfo("邮箱")
    private String email;


}
