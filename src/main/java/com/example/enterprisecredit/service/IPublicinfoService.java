package com.example.enterprisecredit.service;

import com.example.enterprisecredit.entity.Enterprisebasicinfo;
import com.example.enterprisecredit.entity.Publicinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhufu
 * @since 2023-08-31
 */
public interface IPublicinfoService extends IService<Publicinfo> {
    Publicinfo getById(String creditCode);
}
