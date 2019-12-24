package com.wxp.bas.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wxp.equator.FieldNameInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxp
 * @since 2019-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bas_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    private String name;

    private Integer age;

    @FieldNameInfo("邮箱")
    private String email;

    private BigDecimal mm;

    private BigDecimal nn;

    private Date aa;


}
