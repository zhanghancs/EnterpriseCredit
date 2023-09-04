package com.example.enterprisecredit.service;

import com.example.enterprisecredit.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhufu
 * @since 2023-09-01
 */
public interface IMessageService extends IService<Message> {
    List<Message> get();
}
