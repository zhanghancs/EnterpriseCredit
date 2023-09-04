package com.example.enterprisecredit.entity.Dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("profit")
public class ProfitDto {
    public ProfitDto() {
        industry = "无";
        profit = 0;
    }

    @TableField("industry")
    String industry;

    @TableField("profit")
    double profit;
}
