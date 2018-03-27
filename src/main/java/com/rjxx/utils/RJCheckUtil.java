package com.rjxx.utils;

import com.alibaba.fastjson.JSON;
import com.rjxx.taxeasy.dto.*;
import com.rjxx.utils.weixin.HttpClientUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangyahui on 2017/8/11 0011
 */
public class RJCheckUtil {

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
        haveSign.append("si=" + sign.toLowerCase());
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

    public static String decodeXml(String key, String orderData, String sign){
        String signSourceData = "data=" + orderData + "&key=" + key;
        String newSign = DigestUtils.md5Hex(signSourceData);
        System.out.println(newSign);
        System.out.println("手输的"+sign);
        System.out.println(newSign.equalsIgnoreCase(sign));
        if (!sign.equalsIgnoreCase(newSign)) {
            return "0"; //失败
        }else{
            return "1"; //成功
        }
    }

    /**
     * 以下所有方法为白盒测试时候使用，不涉及业务
     */
    //生成Q
//    public static void main(String[] args) {
//        //公司信息的key
//        String key="d8d66cd97983a8569b3f2aab19d7d5e7";
//        Map map = new HashMap();
//        //订单号
//        String on = System.currentTimeMillis() + "X";
//        //订单时间
//        String ot = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        //金额
//        String pr = "10";
//        //门店号
//        String sn = "BF0074";
//        //商品代码
//        String sp = "1";
//        map.put("A0",on);
//        map.put("A1",ot);
//        map.put("A2", pr);
//        map.put("A3", sn);
//        //如果没有商品代码请注释
//        map.put("A4", sp);
//        System.out.println(JSON.toJSONString(map));
//        String result = getQForAll(key, map,"on","ot","pr","sn"
//        //如果没有商品代码请注释
//        ,"sp"
//        );
//        System.out.println(result);
//    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        AdapterGet adapterGet = new AdapterGet();
        adapterGet.setType("1");
        adapterGet.setOn("20180326");
        adapterGet.setOt("20180326180900");
        adapterGet.setPr("10");
        String dataJson = JSON.toJSONString(adapterGet);
        String key = "fa19f6c4d0e4144e8115ed71b0e4c349";
        String sign = DigestUtils.md5Hex("data=" + dataJson + "&key=" + key);
        String str = "data=" + dataJson + "&si=" + sign;
        String encode = Base64Util.encode(str);
        System.out.println(encode);


//        Map map1 = new HashMap();
//        //订单号
//        String on = System.currentTimeMillis() + "X";
//        //订单时间
//        String ot = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        //金额
//        String pr = "10";
//        //门店号
//        String sn = "BF0074";
//        //商品代码
//        String sp = "1";
//        map1.put("A0",on);
//        map1.put("A1",ot);
//        map1.put("A2", pr);
//        map1.put("A3", sn);
//        //如果没有商品代码请注释
//        map1.put("A4", sp);
//        System.out.println(JSON.toJSONString(map1));
//        String result1 = getQForAll(key, map1,"on","ot","pr","sn"
//        //如果没有商品代码请注释
//        ,"sp"
//        );
//        System.out.println(result1);
    }
//    public static void main(String[] args) {
//        AdapterPost post = new AdapterPost();
//        AdapterData data = new AdapterData();
//        AdapterDataOrder order = new AdapterDataOrder();
//        AdapterDataSeller seller = new AdapterDataSeller();
//        AdapterDataOrderBuyer buyer = new AdapterDataOrderBuyer();
//        List<AdapterDataOrderDetails> details = new ArrayList<>();
//        List<AdapterDataOrderPayments> payments = new ArrayList<>();
//
//        //数据
//        data.setDrawer("王亚辉");
//        data.setVersion("19");
//        data.setInvType("12");
//        data.setSerialNumber("20180323103125X");
//        data.setOrder(order);
//        data.setSeller(seller);
//
//        //销方
//        seller.setName("上海百旺测试3643");
//        seller.setIdentifier("500102010003643");
//        seller.setAddress("销方地址");
//        seller.setTelephoneNo("110");
//        seller.setBank("销方银行");
//        seller.setBankAcc("123");
//
//        //订单
//        order.setBuyer(buyer);
//        order.setPayments(payments);
//        order.setOrderDetails(details);
//        order.setOrderNo(System.currentTimeMillis()+"");
//        order.setOrderDate(new Date());
//        order.setTotalAmount(10d);
//        order.setChargeTaxWay("0");//普通征收
//        order.setInvoiceList("0");//不打印清单
//        order.setInvoiceSplit("1");//拆票
//        order.setInvoiceSfdy("0");//不立即打印
//        order.setTaxMark("1");//金额含税
//        order.setRemark("这是备注");
//
//        //购方
//        buyer.setName("法国ankama信息技术有限公司");
//        buyer.setIdentifier("500102010003643");
//        buyer.setAddress("购方地址");
//        buyer.setTelephoneNo("120");
//        buyer.setBank("购方银行");
//        buyer.setBankAcc("321");
//        buyer.setCustomerType("1");
//        buyer.setEmail("243409312@qq.com");
//        buyer.setIsSend("1");
//
//        //明细
//        for (int i=2;i>0;i--){
//            AdapterDataOrderDetails detail = new AdapterDataOrderDetails();
//            detail.setAmount(5d);
//            detail.setMxTotalAmount(5d);
//            detail.setPolicyMark("0");
//            detail.setProductCode("3070401000000000000");
//            detail.setProductName("餐饮服务");
//            detail.setQuantity(1d);
//            detail.setUnitPrice(5d);
//            detail.setUtil("次");
//            detail.setRowType("0");
//            detail.setTaxRate(0.06);
//            details.add(detail);
//        }
//
//        //支付
//        AdapterDataOrderPayments payment = new AdapterDataOrderPayments();
//        payment.setPayCode("02");
//        payment.setPayPrice(5d);
//        payments.add(payment);
//
//        AdapterDataOrderPayments payment2 = new AdapterDataOrderPayments();
//        payment2.setPayCode("04");
//        payment2.setPayPrice(5d);
//        payments.add(payment2);
//
//        //请求
//        post.setAppId("RJ17634f1a0279");
//        post.setTaxNo("500102010003643");
//        String dataJson = JSON.toJSONString(data);
//        System.out.println("data="+dataJson);
//        String key = "fa19f6c4d0e4144e8115ed71b0e4c349";
//        String sign = DigestUtils.md5Hex("data=" + dataJson + "&key=" + key);
//        System.out.println("sign="+sign);
//        post.setSign(sign);
//        post.setData(data);
//        post.setClientNo("test1");
//        String postJson=JSON.toJSONString(post);
//        System.out.println("request="+postJson);
//
//        String url = "http://localhost:8080/adapter";
//        String result = HttpClientUtil.doPostJson(url, postJson);
//        System.out.println(result);
//    }
}
