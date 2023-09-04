package com.example.enterprisecredit.service;

import com.example.enterprisecredit.entity.Enterprisebasicinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhufu
 * @since 2023-08-30
 */
public interface IEnterprisebasicinfoService extends IService<Enterprisebasicinfo> {
    Enterprisebasicinfo getEnterpriseById(String creditCode);
    List<Enterprisebasicinfo> getEnterpriseMax();
    List<Enterprisebasicinfo> getEnterprise(String area);

    List<String> getIndustry();
    List <Enterprisebasicinfo> getby( String area,String transferMode,String industry);
    List <Enterprisebasicinfo> gettwo( String creditCode1,String creditCode2);
}
