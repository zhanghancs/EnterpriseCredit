package com.example.enterprisecredit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.enterprisecredit.entity.Dto.MarketDto;
import com.example.enterprisecredit.entity.Province;

import java.util.List;

public interface IProvinceService extends IService<Province> {
    public List<Province> queryAllProvince();

    public List<MarketDto> queryAllMarket();
}
