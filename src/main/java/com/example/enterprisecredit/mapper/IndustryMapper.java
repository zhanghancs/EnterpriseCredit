package com.example.enterprisecredit.mapper;

import com.example.enterprisecredit.entity.Industry;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhufu
 * @since 2023-08-30
 */
@Mapper
public interface IndustryMapper extends BaseMapper<Industry> {

//    @Select("select * from industry where area = {area}")
//    List<Industry> findByArea(String area);
//    @Select("select * from industryname")
//    List<Industry> findAll();
}
