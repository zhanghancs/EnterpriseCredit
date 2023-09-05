package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enterprisecredit.entity.EnterpriseBasicInfo;
import com.example.enterprisecredit.mapper.EnterpriseBasicInfoMapper;
import com.example.enterprisecredit.service.IEnterpriseBasicInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhufu
 * @since 2023-08-30
 */
@Service
public class EnterpriseBasicInfoServiceImpl extends ServiceImpl<EnterpriseBasicInfoMapper, EnterpriseBasicInfo> implements IEnterpriseBasicInfoService {

    @Autowired
    EnterpriseBasicInfoMapper enterpriseBasicInfoMapper;
    public static int total = 0;
    @Override
    public EnterpriseBasicInfo queryEnterpriseBasicInfoByCode(int stockCode) {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("stockcode", stockCode);
        //2)执行查询
        EnterpriseBasicInfo EnterpriseBasicInfo = enterpriseBasicInfoMapper.selectOne(wrapper);
        return EnterpriseBasicInfo;
    }

    @Override
    public List<EnterpriseBasicInfo> queryTop5Profits() {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.orderByDesc("profit");
        wrapper.last("LIMIT 5");

        //2)执行查询
        List<EnterpriseBasicInfo> resultList = enterpriseBasicInfoMapper.selectList(wrapper);

        //3)提取查询结果
        return resultList;

    }

    @Override
    public List<EnterpriseBasicInfo> getEnterprise(String area) {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.eq("area", area);

        //2)执行查询
        List<EnterpriseBasicInfo> resultList = enterpriseBasicInfoMapper.selectList(wrapper);

        //3)提取查询结果
        return resultList;

    }

    @Override
    public List<String> getIndustry() {
        QueryWrapper wrapper = new QueryWrapper();


        //2)执行查询
        List<String> resultList = enterpriseBasicInfoMapper.selectList(wrapper);

        //3)提取查询结果
        return resultList;


    }

    @Override
    public List<EnterpriseBasicInfo> queryByIndex(String area, String transferMode, String industry) {

        QueryWrapper wrapper = new QueryWrapper();
        if (area != null) {
            wrapper.eq("area", area);
        }
        if (transferMode != null) {
            wrapper.eq("transferMode", transferMode);
        }
        if (industry != null) {
            wrapper.eq("industry", industry);
        }


        //2)执行查询
        List<EnterpriseBasicInfo> EnterpriseBasicInfo = enterpriseBasicInfoMapper.selectList(wrapper);
        return EnterpriseBasicInfo;
    }

    public List<EnterpriseBasicInfo> query2Enterprise(int stockCode1, int stockCode2)
    {
        QueryWrapper<EnterpriseBasicInfo> wrapper = new QueryWrapper<>();
        wrapper.in("stockcode", Arrays.asList(stockCode1, stockCode2));

        // 2) 执行查询
        List<EnterpriseBasicInfo> EnterpriseBasicInfoList = enterpriseBasicInfoMapper.selectList(wrapper);

        return EnterpriseBasicInfoList;

    }
    @Override
    public List<EnterpriseBasicInfo> queryEnterpriseByKeyword(String keyword, int page, int pageSize) {
        // 计算分页的起始位置
        int offset = (page - 1) * pageSize;

        // 1) 封装查询条件 QueryWrapper
        List<EnterpriseBasicInfo> results = new ArrayList<>();

        // 1) 使用creditCode属性进行模糊搜索
        QueryWrapper<EnterpriseBasicInfo> creditCodeWrapper = new QueryWrapper<>();
        creditCodeWrapper.like("stockCode", "%" + keyword + "%");
        List<EnterpriseBasicInfo> enterprisesByCreditCode1 = enterpriseBasicInfoMapper.selectList(creditCodeWrapper);
        if(enterprisesByCreditCode1.size()!=0)
            total = enterprisesByCreditCode1.size();
        creditCodeWrapper.last("LIMIT " + offset + "," + pageSize); // 添加分页限制
        List<EnterpriseBasicInfo> enterprisesByCreditCode = enterpriseBasicInfoMapper.selectList(creditCodeWrapper);

        // 2) 如果creditCode查询结果不为空，将其添加到结果列表中
        if (!enterprisesByCreditCode.isEmpty()) {

            results.addAll(enterprisesByCreditCode);
        } else {
            // 3) 使用name属性进行模糊搜索
            QueryWrapper<EnterpriseBasicInfo> nameWrapper = new QueryWrapper<>();
            nameWrapper.like("name", "%" + keyword + "%");
            List<EnterpriseBasicInfo> enterprisesByCreditCode2 = enterpriseBasicInfoMapper.selectList(nameWrapper);
            if(enterprisesByCreditCode2.size()!=0)
                total = enterprisesByCreditCode2.size();
            nameWrapper.last("LIMIT " + offset + "," + pageSize); // 添加分页限制
            List<EnterpriseBasicInfo> enterprisesByName = enterpriseBasicInfoMapper.selectList(nameWrapper);

            // 4) 如果name查询结果不为空，将其添加到结果列表中
            if (!enterprisesByName.isEmpty()) {

                results.addAll(enterprisesByName);
            } else {
                // 5) 使用shortname属性进行模糊搜索
                QueryWrapper<EnterpriseBasicInfo> shortnameWrapper = new QueryWrapper<>();
                shortnameWrapper.like("shortname", "%" + keyword + "%");
                List<EnterpriseBasicInfo> enterprisesByCreditCode3 = enterpriseBasicInfoMapper.selectList(shortnameWrapper);
                if(enterprisesByCreditCode3.size()!=0)
                    total = enterprisesByCreditCode3.size();
                shortnameWrapper.last("LIMIT " + offset + "," + pageSize); // 添加分页限制
                List<EnterpriseBasicInfo> enterprisesByShortname = enterpriseBasicInfoMapper.selectList(shortnameWrapper);

                // 6) 如果shortname查询结果不为空，将其添加到结果列表中
                if (!enterprisesByShortname.isEmpty()) {

                    results.addAll(enterprisesByShortname);
                }
                else {
                    QueryWrapper<EnterpriseBasicInfo> addressWrapper = new QueryWrapper<>();
                    addressWrapper.like("address", "%" + keyword + "%");
                    List<EnterpriseBasicInfo> enterprisesByCreditCode4 = enterpriseBasicInfoMapper.selectList(addressWrapper);
                    if(enterprisesByCreditCode4.size()!=0)
                    {

                        total = enterprisesByCreditCode4.size();
                    }
                    addressWrapper.last("LIMIT " + offset + "," + pageSize); // 添加分页限制
                    List<EnterpriseBasicInfo> enterprisesByaddress = enterpriseBasicInfoMapper.selectList(addressWrapper);
                    if (!enterprisesByaddress.isEmpty()) {

                        results.addAll(enterprisesByaddress);
                    }
                }

            }
        }

        return results;

    }


}
