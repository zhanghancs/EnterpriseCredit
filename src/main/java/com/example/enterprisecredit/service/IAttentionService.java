package com.example.enterprisecredit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enterprisecredit.entity.Attention;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhufu
 * @since 2023-08-28
 */

public interface IAttentionService extends IService<Attention> {
    public int insertAttention(Attention attention);

    public int deleteAttention(Attention attention);

    public List<Attention> queryByUsername(String username);
}
