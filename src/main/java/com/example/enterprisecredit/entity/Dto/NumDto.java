package com.example.enterprisecredit.entity.Dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("num")
public class NumDto {
    public NumDto() {
        industry = "无";
        num = 0;
    }
    String industry;

    int num;
}
