package com.rjxx.utils;

import com.alibaba.fastjson.JSON;
import com.rjxx.taxeasy.dto.AdapterGet;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println(orderData);
        String signSourceData = "data=" + orderData + "&key=" + key;
        String newSign = DigestUtils.md5Hex(signSourceData);
        System.out.println(newSign);
        System.out.println("传入的"+sign);
        System.out.println(newSign.equalsIgnoreCase(sign));
        if (!sign.equalsIgnoreCase(newSign)) {
            return "0"; //失败
        }else{
            return "1"; //成功
        }
    }

    /**
     * 白盒测试
     * @param args
     * @throws UnsupportedEncodingException
     */

//    public static void main(String[] args) {
//        //公司信息的key
//        String key="42709f25722653a5d7b5b8dde426f494";
//        Map map = new HashMap();
//        //订单号
//        String on = System.currentTimeMillis()+"";
//        //订单时间
//        String ot = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        //金额
//        String pr = "10";
//        //门店号
//        String sn = "wx002";
//        //商品代码
////        String sp = "1";
//        map.put("A0",on);
//        map.put("A1",ot);
//        map.put("A2", pr);
//        map.put("A3", sn);
//        //如果没有商品代码请注释
////        map.put("A4", sp);
//        System.out.println(JSON.toJSONString(map));
//        String result = getQForAll(key, map,"on","ot","pr","sn"
//        //如果没有商品代码请注释
////        ,"sp"
//        );
//        System.out.println(result);
//    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//        String key = "two";//需修改rjxx ppurl为orderdetail
//        String key = "three";
//        String key = "four";

        AdapterGet adapterGet = new AdapterGet();

//        adapterGet.setPr("10");
//        adapterGet.setType("2");
//        adapterGet.setOn(System.currentTimeMillis() + "");
//        System.out.println(adapterGet.getOn());
//        adapterGet.setOt(new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
//        adapterGet.setSn("two");
//        adapterGet.setSp("1");

//        adapterGet.setOn(System.currentTimeMillis() + "");
//        adapterGet.setTq("EX1530596643651");
//        adapterGet.setType("3");
//        adapterGet.setSn("three");

//        adapterGet.setMi("four1");
//        adapterGet.setType("4");
//        adapterGet.setSn("four");
//        adapterGet.setOt(new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));

//        String dataJson = JSON.toJSONString(adapterGet);
//        String sign = DigestUtils.md5Hex("data=" + dataJson + "&key=" + key);
//        String str = "data=" + dataJson + "&si=" + sign;
//        String encode = Base64Util.encode(str);
//        System.out.println(encode);
//        System.out.println("fpjtest.datarj.com/einv/kptService/" + key + "/" + encode);


        adapterGet.setType("2");
        adapterGet.setOn(""+System.currentTimeMillis());
        adapterGet.setOt(new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
        adapterGet.setPr("11");
        String key = "267b9725bf058e780a194e32cf07e5de";
        String gsdm = "wscy";

        String dataJson = JSON.toJSONString(adapterGet);
        String sign = DigestUtils.md5Hex("data=" + dataJson + "&key=" + key);
        String str = "data=" + dataJson + "&si=" + sign;
        String encode = Base64Util.encode(str);
        System.out.println(encode);
        System.out.println("fpjtest.datarj.com/einv/kptService/" + gsdm + "/" + encode);
    }

//    public static void main(String[] args) throws java.text.ParseException {
//        AdapterPost post = new AdapterPost();
//        AdapterData data = new AdapterData();
//        AdapterDataOrder order = new AdapterDataOrder();
//        AdapterDataSeller seller = new AdapterDataSeller();
//        AdapterDataOrderBuyer buyer = new AdapterDataOrderBuyer();
//        List<AdapterDataOrderDetails> details = new ArrayList<>();
//        List<AdapterDataOrderPayments> payments = new ArrayList<>();
//
//        //数据
//        data.setDrawer("开票人");
//        data.setVersion("19");
//        data.setInvType("12");
//        data.setSerialNumber("TESTJY"+System.currentTimeMillis());
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
////        order.setPayments(payments);
//        order.setOrderDetails(details);
//        order.setOrderNo("TPYE3"+System.currentTimeMillis());
//        order.setOrderDate(new java.util.Date());
//        order.setTotalAmount(100d);
//        order.setChargeTaxWay("0");//普通征收
//        order.setInvoiceList("0");//不打印清单
//        order.setInvoiceSplit("1");//拆票
//        order.setInvoiceSfdy("0");//不立即打印
//        order.setTaxMark("1");//金额含税
//        order.setRemark("这是备注");
//        order.setExtractedCode("   ");
//
//        //购方
////        buyer.setName("法国ankama信息技术有限公司");
////        buyer.setIdentifier("500102010003643");
////        buyer.setAddress("购方地址");
////        buyer.setTelephoneNo("120");
////        buyer.setBank("购方银行");
////        buyer.setBankAcc("321");
////        buyer.setCustomerType("1");
////        buyer.setEmail("123@qq.com");
//        buyer.setIsSend("1");
//
//        //明细
//        AdapterDataOrderDetails detail = new AdapterDataOrderDetails();
//        detail.setAmount(100d);
//        detail.setMxTotalAmount(100d);
//        detail.setPolicyMark("0");
//        detail.setProductCode("1090622020000000000");
//        detail.setProductName("相机");
//        detail.setUnit("个");
//        detail.setQuantity(1d);
//        detail.setUnitPrice(100d);
//        detail.setRowType("0");
//        detail.setTaxRate(0.16);
//        details.add(detail);
//
//        AdapterDataOrderDetails detail2 = new AdapterDataOrderDetails();
//        detail2.setAmount(10d);
//        detail2.setMxTotalAmount(10d);
//        detail2.setPolicyMark("0");
//        detail2.setProductCode("1090622020000000000");
//        detail2.setProductName("冲印");
//        detail2.setUnit("次");
//        detail2.setQuantity(1d);
//        detail2.setUnitPrice(10d);
//        detail2.setRowType("2");
//        detail2.setTaxRate(0.16);
//        details.add(detail2);
//
//        AdapterDataOrderDetails detail4 = new AdapterDataOrderDetails();
//        detail4.setAmount(-10d);
//        detail4.setMxTotalAmount(-10d);
//        detail4.setPolicyMark("0");
//        detail4.setUnit("次");
//        detail4.setProductCode("1090622020000000000");
//        detail4.setProductName("冲印");
//        detail4.setRowType("1");
//        detail4.setTaxRate(0.16);
//        details.add(detail4);
//
//        //请求
//        post.setAppId("three");
//        String key = "three";
//        String sn = "three";
//        post.setClientNo(sn);
//        post.setReqType("02");
//
//        post.setTaxNo("500102010003643");
//        String dataJson = JSON.toJSONString(data);
//        System.out.println("data="+dataJson);
//        String sign = DigestUtils.md5Hex("data=" + dataJson + "&key=" + key);
//        System.out.println("sign="+sign);
//        post.setSign(sign);
//        post.setData(data);
//        String postJson=JSON.toJSONString(post);
//        System.out.println(postJson);
//
//        String url = "http://test.datarj.com/webService/kptService";
//        String result = HttpClientUtil.doPostJson(url, postJson);
//        System.out.println(result);
//        System.out.println(order.getOrderNo());
//    }
}
