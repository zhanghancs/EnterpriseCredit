package com.example.enterprisecredit.entity.Dto;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Income")
public class Income {

    String industry;


    double income;
}
