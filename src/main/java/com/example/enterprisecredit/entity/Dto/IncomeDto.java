package com.example.enterprisecredit.entity.Dto;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Income")
public class IncomeDto {

    public IncomeDto() {
        industry = "无";
        income = 0;
    }

    String industry;

    double income;
}
