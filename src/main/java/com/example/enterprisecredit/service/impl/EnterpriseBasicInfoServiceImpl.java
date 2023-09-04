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
    EnterpriseBasicInfoMapper enterprisebasicinfoMapper;

    @Override
    public EnterpriseBasicInfo queryEnterpriseBasicInfoByCode(int stockCode) {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("stockcode", stockCode);
        //2)执行查询
        EnterpriseBasicInfo enterpriseBasicInfo = enterprisebasicinfoMapper.selectOne(wrapper);
        return enterpriseBasicInfo;
    }

    @Override
    public List<EnterpriseBasicInfo> getEnterpriseMax() {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.orderByDesc("profit");
        wrapper.last("LIMIT 5");

        //2)执行查询
        List<EnterpriseBasicInfo> resultList = enterprisebasicinfoMapper.selectList(wrapper);

        //3)提取查询结果
        return resultList;

    }

    @Override
    public List<EnterpriseBasicInfo> getEnterprise(String area) {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.eq("area", area);

        //2)执行查询
        List<EnterpriseBasicInfo> resultList = enterprisebasicinfoMapper.selectList(wrapper);

        //3)提取查询结果
        return resultList;

    }

    @Override
    public List<String> getIndustry() {
        QueryWrapper wrapper = new QueryWrapper();


        //2)执行查询
        List<String> resultList = enterprisebasicinfoMapper.selectList(wrapper);

        //3)提取查询结果
        return resultList;


    }

    @Override
    public List<EnterpriseBasicInfo> getby(String area, String transferMode, String industry) {

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
        wrapper.last("LIMIT 5");

        //2)执行查询
        List<EnterpriseBasicInfo> enterprisebasicinfo = enterprisebasicinfoMapper.selectList(wrapper);
        return enterprisebasicinfo;
    }

    public List<EnterpriseBasicInfo> gettwo(String creditCode1, String creditCode2)
    {

        QueryWrapper<EnterpriseBasicInfo> wrapper = new QueryWrapper<>();
        wrapper.in("creditCode", Arrays.asList(creditCode1, creditCode2));

        // 2) 执行查询
        List<EnterpriseBasicInfo> enterprisebasicinfoList = enterprisebasicinfoMapper.selectList(wrapper);

        return enterprisebasicinfoList;

    }
    @Override
    public List<EnterpriseBasicInfo> searchEnterprisesByKeyword(String keyword, int pageNo, int pageSize) {
        // 计算分页的起始位置
        int offset = (pageNo - 1) * pageSize;

        // 1) 封装查询条件 QueryWrapper
        List<EnterpriseBasicInfo> results = new ArrayList<>();

        // 1) 使用creditCode属性进行模糊搜索
        QueryWrapper<EnterpriseBasicInfo> creditCodeWrapper = new QueryWrapper<>();
        creditCodeWrapper.like("stockCode", "%" + keyword + "%");
        creditCodeWrapper.last("LIMIT " + offset + "," + pageSize); // 添加分页限制
        List<EnterpriseBasicInfo> enterprisesByCreditCode = enterprisebasicinfoMapper.selectList(creditCodeWrapper);

        // 2) 如果creditCode查询结果不为空，将其添加到结果列表中
        if (!enterprisesByCreditCode.isEmpty()) {
            results.addAll(enterprisesByCreditCode);
        } else {
            // 3) 使用name属性进行模糊搜索
            QueryWrapper<EnterpriseBasicInfo> nameWrapper = new QueryWrapper<>();
            nameWrapper.like("name", "%" + keyword + "%");
            nameWrapper.last("LIMIT " + offset + "," + pageSize); // 添加分页限制
            List<EnterpriseBasicInfo> enterprisesByName = enterprisebasicinfoMapper.selectList(nameWrapper);

            // 4) 如果name查询结果不为空，将其添加到结果列表中
            if (!enterprisesByName.isEmpty()) {
                results.addAll(enterprisesByName);
            } else {
                // 5) 使用shortname属性进行模糊搜索
                QueryWrapper<EnterpriseBasicInfo> shortnameWrapper = new QueryWrapper<>();
                shortnameWrapper.like("shortname", "%" + keyword + "%");
                shortnameWrapper.last("LIMIT " + offset + "," + pageSize); // 添加分页限制
                List<EnterpriseBasicInfo> enterprisesByShortname = enterprisebasicinfoMapper.selectList(shortnameWrapper);

                // 6) 如果shortname查询结果不为空，将其添加到结果列表中
                if (!enterprisesByShortname.isEmpty()) {
                    results.addAll(enterprisesByShortname);
                }
            }
        }

        return results;
    }


}
