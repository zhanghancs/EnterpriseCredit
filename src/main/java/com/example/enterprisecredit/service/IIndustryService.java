package com.example.enterprisecredit.service;

import com.example.enterprisecredit.entity.Industry;
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
public interface IIndustryService extends IService<Industry> {

   List<Industry> queryAll(int type);

}
