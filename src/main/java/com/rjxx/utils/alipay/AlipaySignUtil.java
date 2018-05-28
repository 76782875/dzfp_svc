package com.rjxx.utils.alipay;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;

public class AlipaySignUtil {



    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("invoiceAmount", "10");
        map.put("orderNo", "Test01");
        map.put("mShortName", "chamate");
        map.put("subShortName", "chamate");
        map.put("resultUrl", "http://fpjtest.datarj.com/web/template/#/succes/?t=+"+System.currentTimeMillis()+"&ppdm=rjxx");
        KeyPair keyPair = AlipayRSAUtil.getKeyPair();
        String params = sign(map,keyPair.getPrivate());
        System.out.println(params);
        verify(params, keyPair.getPublic());
    }

    public static boolean verify(String params, PublicKey publicKey) throws Exception {
        //解析参数
        Map<String, String> map = restoreMap(params, "&");
        //urldecode 签名
        String sign = URLDecoder.decode(map.get("sign"), "gbk");
        //去除签名参数
        map.remove("sign");
        //生成待验签字符串
        String signatureContent = getSignatureContent(map);
        System.out.println(signatureContent);
        System.out.println(AlipayRSAUtil.verify(signatureContent, publicKey, sign));
        return AlipayRSAUtil.verify(signatureContent, publicKey, sign);
    }

    /**
     * 加签demo方法
     */
    public static String sign(Map params,PrivateKey privateKey) {
        try {
            //获取待验签原始数据
//            Map<String, String> params = new HashMap<String, String>();
//            params.put("invoiceAmount", "10");
//            params.put("orderNo", "Test01");
//            params.put("mShortName", "chamate");
//            params.put("subShortName", "chamate");
//            params.put("resultUrl", "www.baidu.com");
//            params.put("customParam2", "自定义参数2");

            //获取原始加签内容
            String signatureContent = getSignatureContent(params);

            //获取签名,得到签名之后进行urlencode
            String sign = URLEncoder.encode(AlipayRSAUtil.sign(signatureContent, privateKey), "gbk");
            params.put("sign", sign);

            return getSignatureContent(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获得需要签名的数据，按照参数名字母升序的顺序将所有参数用&连接起来
     * @param params 待签名参数集
     * @return 排好序的待签名字符串
     */
    public static String getSignatureContent(Map<String, String> params) {
        if (params == null) {
            return null;
        }
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }

        return content.toString();
    }

    /**
     * 把数据库里边String型的扩展字段转换成map型
     * @param extension 需要转换的String
     * @param spiltorKey 指定的字符串
     * @return      转换后的扩展MAP
     */
    public static Map<String, String> restoreMap(String extension, String spiltorKey) {
        Map<String, String> extensionMap = new HashMap<String, String>();
        String[] fieldList = extension.split(spiltorKey);
        if (null == fieldList) {
            return extensionMap;
        }
        for (String field : fieldList) {
            String[] entry = field.split("=");
            if (entry != null && entry.length >= 2) {
                if (entry.length > 2) {
                    //如果包含多个“=”取第一个后边的为value，如url后边的
                    for (int i = 2; i < entry.length; i++) {
                        entry[1] += ("=" + entry[i]);
                    }
                }
                extensionMap.put(entry[0], entry[1]);
            }

        }
        return extensionMap;
    }
}