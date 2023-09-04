package com.example.enterprisecredit.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhufu
 * @since 2023-08-31
 */
@Data
@TableName("publicinfo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Publicinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField("creditcode")
    private String creditcode;
    @TableField("adminliceninfo")
    private String adminliceninfo;
    @TableField("adminpenaltyinfo")
    private String adminpenaltyinfo;
    @TableField("badadminsuper")
    private String badadminsuper;
    @TableField("admincominfo")
    private String admincominfo;
    @TableField("qualityinspecinfo")
    private String qualityinspecinfo;
    @TableField("webfilinfo")
    private String webfilinfo;


}
