package com.example.enterprisecredit.entity.Dto;

import com.example.enterprisecredit.entity.Publicinfo;

import java.util.ArrayList;
import java.util.List;

public class Publicinfohelper {
    public List<String> get(Publicinfo publicinfo)
    {

        List<String> result = new ArrayList<>();
        result.add(publicinfo.getCreditCode());
        for (String part : publicinfo.getAdminLicenInfo().split("\\|")) {
            result.add(part);
            result.add(""); // Add an empty string after each part
        }
        for (String part : publicinfo.getAdminPenaltyInfo().split("\\|")) {
            result.add(part);
            result.add(""); // Add an empty string after each part
        }

        for (String part : publicinfo.getBadAdminSuper().split("\\|")) {
            result.add(part);
            result.add(""); // Add an empty string after each part
        }
        for (String part : publicinfo.getAdminCominfo().split("\\|")) {
            result.add(part);
            result.add(""); // Add an empty string after each part
        }
        for (String part : publicinfo.getQualityinspecinfo().split("\\|")) {
            result.add(part);
            result.add(""); // Add an empty string after each part
        }
        for (String part : publicinfo.getWebfilinfo().split("\\|")) {
            result.add(part);
            result.add(""); // Add an empty string after each part
        }
        return  result;
    };
}
