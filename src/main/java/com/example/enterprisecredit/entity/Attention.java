package com.example.enterprisecredit.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

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
 * @since 2023-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attention")
public class Attention implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "stockcode")
    private int stockCode;

    @TableField("username")
    private String userName;

    @TableField("companyname")
    private String companyName;


}
