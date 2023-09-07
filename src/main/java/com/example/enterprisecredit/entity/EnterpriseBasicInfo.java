package com.example.enterprisecredit.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

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
 * @since 2023-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("enterprisebasicinfo")
public class EnterpriseBasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("creditcode")
    private String creditCode;

    @TableField("name")
    private String name;

    @TableField("shortname")
    private String shortname;

    @TableField("stockcode")
    private String stockCode;

    @TableField("legalrepresentative")
    private String legalRepresentative;

    @TableField("address")
    private String address;

    @TableField("phone")
    private String phone;

    @TableField("area")
    private String area;

    @TableField("postcode")
    private String postcode;

    @TableField("website")
    private String website;

    @TableField("broker")
    private String broker;

    @TableField("industry")
    private String industry;

    @TableField("totalstockequity")
    private String totalStockEquity;

    @TableField("transfermode")
    private String transferMode;

    @TableField("stockholder1")
    private String stockholder1;

    @TableField("stockholder2")
    private String stockholder2;

    @TableField("stockholder3")
    private String stockholder3;

    @TableField("stockholder4")
    private String stockholder4;

    @TableField("stockholder4")
    private String stockholder5;

    @TableField("stockholder5")
    private String stockholder6;

    @TableField("stockholder7")
    private String stockholder7;

    @TableField("stockholder8")
    private String stockholder8;

    @TableField("stockholder9")
    private String stockholder9;

    @TableField("stockholder10")
    private String stockholder10;

    @TableField("income")
    private double income;

    @TableField("profit")
    private double profit;

    @TableField("netprofit")
    private String netProfit;

    @TableField("nondistributeprofit")
    private String nonDistributeProfit;

    @TableField("totalliability")
    private String totalLiability;

    @TableField("totalassets")
    private String totalAssets;

    @TableField("netassets")
    private String netAssets;

    @TableField("earningspershare")
    private String earningsPerShare;

    @TableField("netAssetspershare")
    private String netAssetsPerShare;

    @TableField("netassetsyield")
    private String netAssetsYield;

    @TableField("score")
    private double score;

}
