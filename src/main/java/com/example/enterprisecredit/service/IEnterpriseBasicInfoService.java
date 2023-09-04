package com.example.enterprisecredit.service;

import com.example.enterprisecredit.entity.EnterpriseBasicInfo;
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
public interface IEnterpriseBasicInfoService extends IService<EnterpriseBasicInfo> {
    EnterpriseBasicInfo queryEnterpriseBasicInfoByCode(int stockCode);
    List<EnterpriseBasicInfo> getEnterpriseMax();
    List<EnterpriseBasicInfo> getEnterprise(String area);

    List<String> getIndustry();
    List <EnterpriseBasicInfo> getby(String area, String transferMode, String industry);
    List <EnterpriseBasicInfo> gettwo(String creditCode1, String creditCode2);
    public List<EnterpriseBasicInfo> searchEnterprisesByKeyword(String Keyword , int pageNo, int pageSizeS);
}
