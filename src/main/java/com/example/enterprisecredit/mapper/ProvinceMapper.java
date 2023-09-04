package com.example.enterprisecredit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.enterprisecredit.entity.Dto.Income;
import com.example.enterprisecredit.entity.Dto.Industry;
import com.example.enterprisecredit.entity.Dto.Profit;
import com.example.enterprisecredit.entity.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProvinceMapper extends BaseMapper<Province> {

    @Select("select * from industry where area = #{area} order by number desc")
    List<Industry> queryAllIndustry(String area);
    @Select("select * from profit where area = #{area} order by profit desc")
    List<Profit> queryAllProfit(String area);

    @Select("select * from income where area = #{area} order by income desc")
    List<Income> queryAllIncome(String area);
    @Select("${sql}")
    public List<Map<String,Object>> queryUsersByCondition(@Param("sql") String sql);

}
