package com.example.enterprisecredit.entity.Dto;

import lombok.Data;

import java.util.List;

@Data
public class EnterpriseBaseInfoDto {

    private String creditCode;

    private String name;

    private String shortname;

    private String stockCode;

    private String legalRepresentative;

    private String address;

    private String phone;

    private String area;

    private String postcode;

    private String website;

    private String broker;

    private String industry;

    private String totalStockEquity;

    private String transferMode;

    private List<StockholderDto> stockholders;

    private double income;

    private double profit;

    private double netProfit;

    private double nonDistributeProfit;

    private double totalLiability;

    private double totalAssets;

    private double netAssets;

    private double earningsPerShare;

    private double netAssetsPerShare;

    private double netAssetsYield;
}
