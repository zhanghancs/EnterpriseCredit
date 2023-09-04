package com.example.enterprisecredit.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("financialinfo")
public class FinancialInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("stockcode")
    private int stockCode;

    @TableField("totalrevenue")
    private String totalRevenue;

    @TableField("totalcost")
    private String totalCost;

    @TableField("cost")
    private String cost;

    @TableField("expense")
    private String expense;

    @TableField("profit")
    private String profit;


}
