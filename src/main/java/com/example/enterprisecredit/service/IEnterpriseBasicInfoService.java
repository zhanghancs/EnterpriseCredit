package com.example.enterprisecredit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.enterprisecredit.entity.EnterpriseBasicInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.enterprisecredit.mapper.EnterpriseBasicInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhufu
 * @since 2023-08-30
 */
public interface IEnterpriseBasicInfoService extends IService<EnterpriseBasicInfo> {

    public EnterpriseBasicInfo queryEnterpriseBasicInfoByCode(int stockCode);

    public List<EnterpriseBasicInfo> queryTop5Profits();

    public List<EnterpriseBasicInfo> getEnterprise(String area);


    public List<String> getIndustry();

    public List<EnterpriseBasicInfo> queryByIndex(String area, String transferMode, String industry);


    public List<EnterpriseBasicInfo> query2Enterprise(int stockCode1, int stockCode2);

    public List<EnterpriseBasicInfo> queryEnterpriseByKeyword(String keyword, int pageNo, int pageSize);


}
