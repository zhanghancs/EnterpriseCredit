package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enterprisecredit.entity.Enterprisebasicinfo;
import com.example.enterprisecredit.mapper.EnterprisebasicinfoMapper;
import com.example.enterprisecredit.service.IEnterprisebasicinfoService;
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
public class EnterprisebasicinfoServiceImpl extends ServiceImpl<EnterprisebasicinfoMapper, Enterprisebasicinfo> implements IEnterprisebasicinfoService {
    @Autowired
    EnterprisebasicinfoMapper enterprisebasicinfoMapper;
public static int total = 0;
    @Override
    public Enterprisebasicinfo getEnterpriseById(String creditCode) {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("creditCode", creditCode);
        //2)执行查询
        Enterprisebasicinfo enterprisebasicinfo = enterprisebasicinfoMapper.selectOne(wrapper);
        return enterprisebasicinfo;
    }

    @Override
    public List<Enterprisebasicinfo> getEnterpriseMax() {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.orderByDesc("profit");
        wrapper.last("LIMIT 5");

        //2)执行查询
        List<Enterprisebasicinfo> resultList = enterprisebasicinfoMapper.selectList(wrapper);

        //3)提取查询结果
        return resultList;

    }

    @Override
    public List<Enterprisebasicinfo> getEnterprise(String area) {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.eq("area", area);

        //2)执行查询
        List<Enterprisebasicinfo> resultList = enterprisebasicinfoMapper.selectList(wrapper);

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
    public List<Enterprisebasicinfo> getby(String area, String transferMode, String industry) {

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
        List<Enterprisebasicinfo> enterprisebasicinfo = enterprisebasicinfoMapper.selectList(wrapper);
        return enterprisebasicinfo;
    }

    public List<Enterprisebasicinfo> gettwo(String creditCode1, String creditCode2)
    {

        QueryWrapper<Enterprisebasicinfo> wrapper = new QueryWrapper<>();
        wrapper.in("creditCode", Arrays.asList(creditCode1, creditCode2));

        // 2) 执行查询
        List<Enterprisebasicinfo> enterprisebasicinfoList = enterprisebasicinfoMapper.selectList(wrapper);

        return enterprisebasicinfoList;

    }
    @Override
    public List<Enterprisebasicinfo> searchEnterprisesByKeyword(String keyword, int pageNo, int pageSize) {
        // 计算分页的起始位置
        int offset = (pageNo - 1) * pageSize;

        // 1) 封装查询条件 QueryWrapper
        List<Enterprisebasicinfo> results = new ArrayList<>();

        // 1) 使用creditCode属性进行模糊搜索
        QueryWrapper<Enterprisebasicinfo> creditCodeWrapper = new QueryWrapper<>();
        creditCodeWrapper.like("stockCode", "%" + keyword + "%");
        List<Enterprisebasicinfo> enterprisesByCreditCode1 = enterprisebasicinfoMapper.selectList(creditCodeWrapper);
        if(enterprisesByCreditCode1.size()!=0)
            total = enterprisesByCreditCode1.size();
        creditCodeWrapper.last("LIMIT " + offset + "," + pageSize); // 添加分页限制
        List<Enterprisebasicinfo> enterprisesByCreditCode = enterprisebasicinfoMapper.selectList(creditCodeWrapper);

        // 2) 如果creditCode查询结果不为空，将其添加到结果列表中
        if (!enterprisesByCreditCode.isEmpty()) {

            results.addAll(enterprisesByCreditCode);
        } else {
            // 3) 使用name属性进行模糊搜索
            QueryWrapper<Enterprisebasicinfo> nameWrapper = new QueryWrapper<>();
            nameWrapper.like("name", "%" + keyword + "%");
            List<Enterprisebasicinfo> enterprisesByCreditCode2 = enterprisebasicinfoMapper.selectList(nameWrapper);
            if(enterprisesByCreditCode2.size()!=0)
                total = enterprisesByCreditCode2.size();
            nameWrapper.last("LIMIT " + offset + "," + pageSize); // 添加分页限制
            List<Enterprisebasicinfo> enterprisesByName = enterprisebasicinfoMapper.selectList(nameWrapper);

            // 4) 如果name查询结果不为空，将其添加到结果列表中
            if (!enterprisesByName.isEmpty()) {

                results.addAll(enterprisesByName);
            } else {
                // 5) 使用shortname属性进行模糊搜索
                QueryWrapper<Enterprisebasicinfo> shortnameWrapper = new QueryWrapper<>();
                shortnameWrapper.like("shortname", "%" + keyword + "%");
                List<Enterprisebasicinfo> enterprisesByCreditCode3 = enterprisebasicinfoMapper.selectList(shortnameWrapper);
                if(enterprisesByCreditCode3.size()!=0)
                    total = enterprisesByCreditCode3.size();
                shortnameWrapper.last("LIMIT " + offset + "," + pageSize); // 添加分页限制
                List<Enterprisebasicinfo> enterprisesByShortname = enterprisebasicinfoMapper.selectList(shortnameWrapper);

                // 6) 如果shortname查询结果不为空，将其添加到结果列表中
                if (!enterprisesByShortname.isEmpty()) {

                    results.addAll(enterprisesByShortname);
                }
                else {
                    QueryWrapper<Enterprisebasicinfo> addressWrapper = new QueryWrapper<>();
                    addressWrapper.like("address", "%" + keyword + "%");
                    List<Enterprisebasicinfo> enterprisesByCreditCode4 = enterprisebasicinfoMapper.selectList(addressWrapper);
                    if(enterprisesByCreditCode4.size()!=0)
                        total = enterprisesByCreditCode4.size();
                    shortnameWrapper.last("LIMIT " + offset + "," + pageSize); // 添加分页限制
                    List<Enterprisebasicinfo> enterprisesByaddress = enterprisebasicinfoMapper.selectList(addressWrapper);
                    if (!enterprisesByaddress.isEmpty()) {

                        results.addAll(enterprisesByaddress);
                    }
                }

            }
        }

        return results;
    }


}
