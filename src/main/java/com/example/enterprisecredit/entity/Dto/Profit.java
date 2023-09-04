package com.example.enterprisecredit.entity.Dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("profit")
public class Profit {

    @TableField("industry")
    String industry;

    @TableField("profit")
    String profit;


}
