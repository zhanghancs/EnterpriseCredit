package com.example.enterprisecredit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.enterprisecredit.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {

}
