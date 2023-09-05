package com.example.enterprisecredit.service;

import com.example.enterprisecredit.entity.Dto.PublicInfoDto;
import com.example.enterprisecredit.entity.PublicInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhufu
 * @since 2023-08-31
 */
public interface IPublicInfoService extends IService<PublicInfo> {
    public PublicInfoDto queryByCode(int stockCode);
}
