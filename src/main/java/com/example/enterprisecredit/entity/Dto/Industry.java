package com.example.enterprisecredit.entity.Dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("industry")
public class Industry {
    String industry;

    int number;

    double profit;

    double income;
}
