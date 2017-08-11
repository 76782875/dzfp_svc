package com.rjxx.utils;

import org.apache.commons.codec.binary.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyahui on 2017/8/11 0011
 */
public class RJCheckUtil {
    public static Boolean checkMD5(String key,String q){
        Map map  =decode(q);
        String orderNo = map.get("orderNo").toString();
        String orderTime = map.get("orderTime").toString();
        String price = map.get("price").toString();
        String storeNo = map.get("storeNo").toString();
        String sign = map.get("sign").toString();
        String dbs = "on="+orderNo+"&ot="+orderTime+"&pr="+price+"&sn="+storeNo+"&key="+key;
        String MD5dbs = "";
        try {
            MD5dbs = MD5Util.generatePassword(dbs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!sign.equals(MD5dbs)){
            return false;
        }
        return true;
    }

    public static Map decode(String q){
        byte[] bytes = Base64.decodeBase64(q);
        String paramsUrl = new String(bytes);
        String[] paramsArray = paramsUrl.split("&");
        String orderNo = paramsArray[0].substring(paramsArray[0].lastIndexOf("=") + 1);//订单号
        String orderTime = paramsArray[1].substring(paramsArray[1].lastIndexOf("=") + 1);//订单时间
        String price = paramsArray[2].substring(paramsArray[2].lastIndexOf("=") + 1);//价格
        String storeNo = paramsArray[3].substring(paramsArray[3].lastIndexOf("=") + 1);//门店号
        String sign = paramsArray[4].substring(paramsArray[4].lastIndexOf("=") + 1);//签名
        Map<String, String> map = new HashMap<>();
        map.put("orderNo", orderNo);
        map.put("orderTime", orderTime);
        map.put("price", price);
        map.put("storeNo", storeNo);
        map.put("sign", sign);
        return map;
    }

    public static String getQ(String key,String orderNo,String orderTime,String price,String storeNo){
        String str = "on="+orderNo+"&ot="+orderTime+"&pr="+price+"&sn="+storeNo+"&key="+key;
        String sign = "";
        try {
            sign = MD5Util.generatePassword(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String string = "on="+orderNo+"&ot="+orderTime+"&pr="+price+"&sn="+storeNo+"&sign="+sign;
        byte[] bytes = Base64.encodeBase64(string.getBytes());
        return new String(bytes);
    }

    public static void main(String[] args) {
        String key = "3f7626939b146cc47c31daf43edc42bd";
        String orderNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String orderTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String price = "100";
        String storeNo = "sh001";
        System.out.println(getQ(key,orderNo,orderTime,price,storeNo));
    }
}
