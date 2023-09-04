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
public class PublicInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField("creditcode")
    private String creditCode;
    @TableField("adminliceninfo")
    private String adminLicenInfo;
    @TableField("adminpenaltyinfo")
    private String adminPenaltyInfo;
    @TableField("badadminsuper")
    private String badAdminSuper;
    @TableField("admincominfo")
    private String adminCominfo;
    @TableField("qualityinspecinfo")
    private String qualityinspecinfo;
    @TableField("webfilinfo")
    private String webfilinfo;


}
