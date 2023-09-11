package com.example.enterprisecredit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.enterprisecredit.entity.Dto.IncomeDto;
import com.example.enterprisecredit.entity.Dto.MarketDto;
import com.example.enterprisecredit.entity.Dto.ProfitDto;
import com.example.enterprisecredit.entity.Dto.NumDto;
import com.example.enterprisecredit.entity.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProvinceMapper extends BaseMapper<Province> {

    // 查询数量信息
    @Select("select * from num where area = #{area} order by num desc")
    List<NumDto> queryAllNum(String area);
    // 查询利润信息
    @Select("select * from profit where area = #{area} order by profit desc")
    List<ProfitDto> queryAllProfit(String area);
    // 查询收入信息
    @Select("select * from income where area = #{area} order by income desc")
    List<IncomeDto> queryAllIncome(String area);
//    @Select("${sql}")
//    public List<Map<String,Object>> queryUsersByCondition(@Param("sql") String sql);

    @Select("select * from market")
    List<MarketDto> queryAllMarket();
}
