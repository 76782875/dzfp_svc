package com.rjxx.utils.weixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 卡券工具类
 * Created by Administrator on 2017-12-14.
 */
@Service
public class CardUtil {

    private static Logger logger = LoggerFactory.getLogger(CardUtil.class);

    public String creatStore(String access_token){
        String poi_id="";
        if(access_token==null||access_token.equals("")){
           return null;
        }
        Map nvps = new HashMap();
        Map business = new HashMap();
        Map base_info = new HashMap();
        nvps.put("business",business);
        business.put("base_info",base_info);
        CardVo cardVo = new CardVo();
        cardVo.setSid("RJ"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        cardVo.setBusiness_name("测试门店");
        cardVo.setBranch_name("光大会展中心分店");
        cardVo.setProvince("上海市");
        cardVo.setCity("上海市");
        cardVo.setDistrict("徐汇区");
        cardVo.setAddress("光大会展中心");
        cardVo.setTelephone("33566700");
        cardVo.setCategories("美食");
        cardVo.setOffset_type("3");
        cardVo.setLongitude("31.167250");
        cardVo.setLatitude("121.428790");
        base_info.put("sid",cardVo.getSid());
        base_info.put("business_name",cardVo.getBusiness_name());
        base_info.put("branch_name",cardVo.getBranch_name());
        base_info.put("province",cardVo.getProvince());
        base_info.put("city",cardVo.getCity());
        base_info.put("district",cardVo.getDistrict());
        base_info.put("address",cardVo.getAddress());
        base_info.put("telephone",cardVo.getTelephone());
        base_info.put("categories",cardVo.getCategories());
        base_info.put("offset_type",cardVo.getOffset_type());
        base_info.put("longitude",cardVo.getLongitude());
        base_info.put("latitude",cardVo.getLatitude());
        return null;
    }
}
