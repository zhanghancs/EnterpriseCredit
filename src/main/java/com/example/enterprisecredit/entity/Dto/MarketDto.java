package com.example.enterprisecredit.entity.Dto;

import lombok.Data;

@Data
public class MarketDto {
    String type;
    long number;
    long total;
    long circulate;
    long trade;
    double amount;
}
