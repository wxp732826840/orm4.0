package com.wxp.bas.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bas_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String menuCode;

    private String menuName;

    private String url;

    private String disorder;


}
