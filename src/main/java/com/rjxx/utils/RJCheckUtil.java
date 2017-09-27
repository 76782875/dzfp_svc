package com.rjxx.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyahui on 2017/8/11 0011
 */
public class RJCheckUtil {

    //一茶一坐验签（仅供一茶一坐使用）
    public static Boolean checkMD5(String key,String q){
        try {
            Map map  =decode(q);
            if(map==null){
                return false;
            }
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
            if(!sign.equalsIgnoreCase(MD5dbs)){
                return false;
            }
            return true;
        } catch(Exception e){
            return false;
        }
    }

    //一茶一坐解析（仅供一茶一坐使用）
    public static Map decode(String q){
        try {
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
        } catch(Exception e){
            return null;
        }
    }

    //全家验签（仅供全家使用）
    public static Boolean check2MD5(String key,String q){
        try {
            Map map  =decodeV2(q);
            if(map==null){
                return false;
            }
            String tqm = map.get("tqm").toString();
            String sign = map.get("sign").toString();

            String dbs = "on="+tqm+"&key="+key;
            String MD5dbs = "";
            try {
                MD5dbs = MD5Util.generatePassword(dbs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(!sign.equalsIgnoreCase(MD5dbs)){
                return false;
            }
            return true;
        } catch(Exception e){
            return false;
        }
    }

    //全家解析（仅供全家使用）
    public static Map decodeV2(String q){
        try {
            byte[] bytes = Base64.decodeBase64(q);
            String paramsUrl = new String(bytes);
            String[] paramsArray = paramsUrl.split("&");
            String tqm = paramsArray[0].substring(paramsArray[0].lastIndexOf("=") + 1);//提取码
            String sign = paramsArray[1].substring(paramsArray[1].lastIndexOf("=") + 1);//签名

            Map<String, String> map = new HashMap<>();
            map.put("tqm", tqm);
            map.put("sign", sign);
            return map;
        } catch(Exception e){
            return null;
        }
    }


    /**
     * 通用验签方法
     * @param key   由容津信息生成，告知客户
     * @param q     按照二维码生成策略生成，每家公司生成策略不同
     * @return      返回验签成功或失败
     * @author      wangyahui
     */
    public static Boolean checkMD5ForAll(String key,String q){
        try {
            Map map  =decodeForAll(q);
            if(map==null){
                return false;
            }
            List args = (List) map.get("args");
            Integer size = (Integer) map.get("size");
            String sign = map.get("A" + (size-1)).toString();
            StringBuilder dbs = new StringBuilder();
            if((size-1)==args.size()){
                for (int i=0;i<args.size();i++){
                    dbs.append(args.get(i) + "=" + map.get("A" + i).toString()+"&");
                }
                dbs.append("key="+key);
                String MD5dbs = "";
                try {
                    MD5dbs = MD5Util.generatePassword(dbs.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(!sign.equalsIgnoreCase(MD5dbs)){
                    return false;
                }
                return true;
            }else{
                return false;
            }
        } catch(Exception e){
            return false;
        }
    }

    /**
     * 通用解析q参数方法
     * @param q 按照二维码生成策略生成，每家公司生成策略不同
     * @return  返回一个Map,里面必有的key为:
     *    size:      传入的q中有几个参数，包含si
     *    A+数字:    数字从零开始，顺序为传入q的参数的顺序
    * @author   wangyahui
     */
    public static Map decodeForAll(String q){
        try {
            byte[] bytes = Base64.decodeBase64(q);
            String paramsUrl = new String(bytes);
            Integer size = getSize(paramsUrl, "=");
            String[] paramsArray = paramsUrl.split("&");
            List list = new ArrayList<>();
            Map map = new HashMap<>();
            map.put("size", size);
            for (int i=0;i<size;i++){
                map.put("A" + i, paramsArray[i].substring(paramsArray[i].lastIndexOf("=") + 1));
                String x=paramsArray[i].substring(0, paramsArray[i].lastIndexOf("="));
                if(!"si".equals(x)){
                    list.add(x);
                }
            }
            map.put("args", list);
            System.out.println("解析后的Q为："+map.toString());
            return map;
        } catch(Exception e){
            return null;
        }
    }

    public static Integer getSize(String after,String reg){
        Integer beforeLength = after.length();
        String paramsUrlAfter = after.replaceAll(reg, "");
        Integer afterLength = paramsUrlAfter.length();
        Integer paramSize = beforeLength - afterLength;
        return paramSize;
    }

    /**
     * 生成Q通用方法
     * @param key   由容津信息生成，告知客户
     * @param map   参数值，key为A+数字，从零开始，与args对应，顺序不能错
     * @param args  参数名，与map的参数值对应
     * @return      q
     * @author      wangyahui
     */
    public static String getQForAll(String key,Map map,String... args){
        StringBuilder haveKey = getSb(map, args);
        haveKey.append("key=" + key);
        String sign = "";
        try {
            sign = MD5Util.generatePassword(haveKey.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder haveSign=getSb(map, args);
        haveSign.append("si=" + sign);
        byte[] bytes = Base64.encodeBase64(haveSign.toString().getBytes());
        return new String(bytes);
    }

    public static StringBuilder getSb(Map map,String... args){
        if(map.size()!=args.length){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<args.length;i++){
            sb.append(args[i] + "=" + map.get("A" + i).toString()+"&");
        }
        return sb;
    }

    public static String decodeXml(String key,String orderData,String sign){
        String signSourceData = "data=" + orderData + "&key=" + key;
        String newSign = DigestUtils.md5Hex(signSourceData);
        if (!sign.equals(newSign)) {
            return "0"; //失败
        }else{
            return "1"; //成功
        }
    }

    /**
     * 以下所有方法为白盒测试时候使用，不涉及业务
     */
        //生成Q
    public static void main(String[] args) {
        //一茶一坐
//       String key = "3f7626939b146cc47c31daf43edc42bd";
        String key="42709f25722653a5d7b5b8dde426f494";
        //德克士
//        String key = "dbd4ae1c59837510b70411e20f3a84f9";
        Map map = new HashMap();
        map.put("A0", String.valueOf(System.currentTimeMillis()));
        map.put("A1", new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
        map.put("A2", "0.11,0.12");
        map.put("A3", "shn");
        map.put("A4", "1,1");
        String result = getQForAll(key, map,"on","ot","pr","sn","sp");
        System.out.println(result);
    }

    //测试验签
//    public static void main(String[] args) {
//        Boolean b =RJCheckUtil.checkMD5ForAll("3f7626939b146cc47c31daf43edc42bd",
//                "b249MTUwNTI2Njg1NTIyMSZvdD0yMDE3MDkxMzA5NDA1NSZwcj0wLjEmc249c2huJnNwPTEyMzQ1Njc4OTEyMzQ1Njc4OTAmc2k9RTRGNkY3MThFNEI2ODY2RTJFNDVERUQ5MTk4NDk1QzY=");
//        System.out.println(b);
//    }

    //客户示例方法
//    public static void main(String[] args) {
//        Map map = new HashMap();
//        String key = "3f7626939b146cc47c31daf43edc42bd";
//        map.put("A0", "abcdefghijklmn");
//        map.put("A1", "20170912114830");
//        map.put("A2", "100");
//        map.put("A3", "sh001");
//        map.put("A4", "1234567891234567890");
//        String result = getQForAll(key, map,"on","ot","pr","sn","sp");
//        System.out.println(result);
//    }

}
