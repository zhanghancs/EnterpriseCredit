package com.example.enterprisecredit.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class Attentionlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "creditCode")
    private String creditCode;

    @TableField("userName")
    private String userName;

    @TableField("companyName")
    private String companyName;


}
