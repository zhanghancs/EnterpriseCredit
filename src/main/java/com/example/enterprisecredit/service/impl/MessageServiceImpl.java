package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enterprisecredit.entity.Message;
import com.example.enterprisecredit.mapper.MessageMapper;
import com.example.enterprisecredit.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhufu
 * @since 2023-09-01
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {
    @Autowired
    MessageMapper messageMapper;

    public List<Message> queryMessage() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("date");
        wrapper.last("LIMIT 20");
        List<Message> list =messageMapper.selectList(wrapper);
        return  list;
    }
}
