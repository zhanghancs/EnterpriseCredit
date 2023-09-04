package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enterprisecredit.entity.Industry;
import com.example.enterprisecredit.mapper.IndustryMapper;
import com.example.enterprisecredit.service.IIndustryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.text.Collator;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhufu
 * @since 2023-08-30
 */
@Service
public class IndustryServiceImpl extends ServiceImpl<IndustryMapper, Industry> implements IIndustryService {
    @Autowired
    IndustryMapper industryMapper;
    public List<Industry> queryAll(int type) {
        QueryWrapper wrapper = new QueryWrapper();
        List<Industry> industryList =industryMapper.selectList(wrapper);
        return  industryList;
    }

  public  List<Industry> findAll(String first ,String last)
    {
        QueryWrapper wrapper = new QueryWrapper();

        List<Industry> list = industryMapper.findAll();

        // 创建一个Comparator，按照中文首字母排序
        Comparator<Industry> chineseComparator = new Comparator<Industry>() {
            @Override
            public int compare(Industry industry1, Industry industry2) {
                Collator collator = Collator.getInstance(Locale.CHINESE);
                return collator.compare(industry1.getIndustry(), industry2.getIndustry());
            }
        };
        List<Industry> filteredList = new ArrayList<>();
        // 使用Comparator对list进行排序
        list.sort(chineseComparator);
        for (Industry industry : list)
        {
            char firstLetter;
            if(industry.getIndustry().equals("畜牧业"))
            {
                firstLetter ='x';
            }
            else {
                String [] pinyin = PinyinHelper.toHanyuPinyinStringArray(industry.getIndustry().charAt(0));
                firstLetter = pinyin[0].charAt(0);
            }

            System.out.println(industry.getIndustry() + " " +firstLetter);
            if (firstLetter >= first.charAt(0) && firstLetter <= last.charAt(0) ) {
                filteredList.add(industry);
            }
        }
        return filteredList;
    }
}
