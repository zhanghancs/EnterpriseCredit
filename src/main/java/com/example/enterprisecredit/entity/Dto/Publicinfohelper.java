package com.example.enterprisecredit.entity.Dto;

import com.example.enterprisecredit.entity.Publicinfo;

import java.util.ArrayList;
import java.util.List;

public class Publicinfohelper {
    public List<String> get(Publicinfo publicinfo)
    {

        List<String> result = new ArrayList<>();
        result.add(publicinfo.getCreditcode());
        for (String part : publicinfo.getAdminliceninfo().split("\\|")) {
            result.add(part);
            result.add(""); // Add an empty string after each part
        }
        for (String part : publicinfo.getAdminpenaltyinfo().split("\\|")) {
            result.add(part);
            result.add(""); // Add an empty string after each part
        }

        for (String part : publicinfo.getBadadminsuper().split("\\|")) {
            result.add(part);
            result.add(""); // Add an empty string after each part
        }
        for (String part : publicinfo.getAdmincominfo().split("\\|")) {
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
