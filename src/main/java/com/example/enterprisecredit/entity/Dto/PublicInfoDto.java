package com.example.enterprisecredit.entity.Dto;

import com.example.enterprisecredit.entity.PublicInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PublicInfoDto {
    public PublicInfoDto(PublicInfo publicInfo) {
        this.licenInfo = spiltList(publicInfo.getAdminLicenInfo());
        this.penatlyInfo = spiltList(publicInfo.getAdminPenaltyInfo());
        this.badInfo = spiltList(publicInfo.getBadAdminSuper());
        this.comInfo = spiltList(publicInfo.getAdminCominfo());
        this.qualityInfo = spiltList(publicInfo.getQualityinspecinfo());
        this.webInfo = spiltList(publicInfo.getWebfilinfo());

    }

    List<String> licenInfo;
    List<String> penatlyInfo;
    List<String> badInfo;
    List<String> comInfo;
    List<String> qualityInfo;
    List<String> webInfo;


    List<String> spiltList(String old) {
        List<String> list = new ArrayList<String>();
        String[] elements = old.split("\\|");
        for (String element : elements) {
            list.add(element);
        }
        return list;
    }
     public Double getScore()
    {
        Double score = 88.0;

        if(!"-".equals(this.licenInfo.get(0)))
        {
            score-=12*(1-1/Double.parseDouble(this.licenInfo.get(0)));

        }
        if(!"-".equals(this.penatlyInfo.get(0)))
        {
            score-=12*(1-1/Double.parseDouble(this.penatlyInfo.get(0)));

        }
        if(!"-".equals(this.badInfo.get(0)))
        {
            score-=12*(1-1/Double.parseDouble(this.badInfo.get(0)));

        }
        if(!"-".equals(this.comInfo.get(0)))
        {
            score+=12*(1-1/Double.parseDouble(this.comInfo.get(0)));

        }
        if(!"-".equals(this.qualityInfo.get(0)))
        {
            score-=12*(1-1/Double.parseDouble(this.qualityInfo.get(0)));

        }
        if(!"-".equals(this.webInfo.get(0)))
        {
            score-=12*(1-1/Double.parseDouble(this.getWebInfo().get(0)));

        }

return  score;
    }

//    public List<String> get(PublicInfo publicinfo)
//    {
//
//        List<String> result = new ArrayList<>();
//        result.add(publicinfo.getCreditCode());
//        for (String part : publicinfo.getAdminLicenInfo().split("\\|")) {
//            result.add(part);
//            result.add(""); // Add an empty string after each part
//        }
//        for (String part : publicinfo.getAdminPenaltyInfo().split("\\|")) {
//            result.add(part);
//            result.add(""); // Add an empty string after each part
//        }
//
//        for (String part : publicinfo.getBadAdminSuper().split("\\|")) {
//            result.add(part);
//            result.add(""); // Add an empty string after each part
//        }
//        for (String part : publicinfo.getAdminCominfo().split("\\|")) {
//            result.add(part);
//            result.add(""); // Add an empty string after each part
//        }
//        for (String part : publicinfo.getQualityinspecinfo().split("\\|")) {
//            result.add(part);
//            result.add(""); // Add an empty string after each part
//        }
//        for (String part : publicinfo.getWebfilinfo().split("\\|")) {
//            result.add(part);
//            result.add(""); // Add an empty string after each part
//        }
//        return  result;
//    };
}
