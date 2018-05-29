package com.rjxx.utils.alipay;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

public class AlipaySignUtil {

    public static String PRIKEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIgp2pq98axr1IcLrsW" +
            "5MPUqeggdeGPAyh8+2aHObn4ER6KmpTgo+pi2SScFoNgesWx72kY2PEEGsUwcTXKkSYaeZVs7z7Z/ujQvP0kbPGouG7Q6MN5" +
            "+jud0sM2Gkmu60fGJXQ8IfeFi6oQCKZpMviXg00KV7ct6JmHlgUpP0jR5AgMBAAECgYBlxAo6/t1iBVFZATVFV4ysn2uHJyd0Po" +
            "GR6rJTSWqxSleTy8LN/2qTuiFgRceZ3w6xyrsvIJfV7b+S59BGb1z3ZdO09gOeviP5W3pC23ClItBlsyNf4njXylQus9Nl4ZnKcV/U" +
            "EDvjChGIma8ZZChkxHNpLls9WGWFkQkFk88TkQJBALuOBCgCVyI1Lm2r0mPlC7RbuPiKzmT5kOs0+1696WulbRMODPGXsz8ivMJSBQy+e" +
            "hU6xV//nd5GOAfjt5PqRw0CQQC52rJZdqPGxHhESw8eI5ZBe9Mz7b6T2UKuPJch6xwpiMPlQB7p0hk5Nhnyic01fppG3gpP6MEtHwiTOT" +
            "MwXsgdAkAqzpkoQJB+oEC+i07zud1YBu9K2vOMnGF1LZyJ3TKffRxOExDlO0iQCm+msm2woPDgU4+k/4SarNAxDMpjmj8pAkA6/1WGWM" +
            "b8nfmflEQkSR+1gd01qs7ImDs2nD1Noxi5hpTI/WXSy8L+ClKKT3w48wt+W5Xib/yCmktakNnTDQNxAkBbXimHdGVY3GrZszuAN1n3c" +
            "yafBSTqpFUdqhfRZ/QLj7wJcmJ+PrLpyB6KMZVsnjzOCS9/tWtIZi14ynhOvM2l";
    public static String PUBKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCb0HCH1F/TwCq7P52xqVq3XI7n8KvS" +
            "erHK0SdxcOtbCUBksEoL94rrC21nJK2AJUTTLXUXQHsResGZ3cllJiA1KjBM6ckvavdoaXrByaIP3GuzJpgF7ojSeCn" +
            "BNk1+osZexNsc9QmUey02h4o3antAi6U1zb/nGdXzXovtKYigxQIDAQAB";

    private static Logger logger = LoggerFactory.getLogger(AlipaySignUtil.class);



    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("invoiceAmount", "10");
        map.put("orderNo", "Test01");
        map.put("mShortName", "chamate");
        map.put("subShortName", "chamate");
        map.put("resultUrl", "http://fpjtest.datarj.com/web/template/#/succes/?t=+"+System.currentTimeMillis()+"&ppdm=rjxx");
        KeyPair keyPair = getKeyPair();
        String params = sign(map,keyPair.getPrivate());
        System.out.println(params);
        verify(params, keyPair.getPublic());
    }

    public static boolean verify(String params, PublicKey publicKey) throws Exception {
        //解析参数
        Map<String, String> map = restoreMap(params, "&");
        //urldecode 签名
        String sign = URLDecoder.decode(map.get("sign"), "utf-8");
        //去除签名参数
        map.remove("sign");
        //生成待验签字符串
        String signatureContent = getSignatureContent(map);
        System.out.println(signatureContent);
        System.out.println(verify(signatureContent, publicKey, sign));
        return verify(signatureContent, publicKey, sign);
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
            String sign = URLEncoder.encode(sign(signatureContent, privateKey), "utf-8");
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

    /**
     * 加签
     *
     * @param data       待加签数据
     * @param privateKey 私钥
     * @return 签名
     * @throws Exception 异常
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateK);
        signature.update(data.getBytes("GBK"));
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     *
     * @param publicKey 公钥
     * @param srcData   原始字符串
     * @param sign      签名
     * @return 是否验签通过
     * @throws Exception 异常
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(publicK);
        signature.update(srcData.getBytes("GBK"));
        return signature.verify(org.apache.commons.codec.binary.Base64.decodeBase64(sign.getBytes()));
//        return signature.verify(sign.getBytes());
    }

    /**
     * 获取密钥对
     *
     * @return 密钥对
     * @throws Exception 异常
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        KeyPair pair = generator.generateKeyPair();
        return pair;
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return 私钥对象
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] encodedKey = privateKey.getBytes();
        encodedKey = org.apache.commons.codec.binary.Base64.decodeBase64(encodedKey);
        EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return 公钥对象
     * @throws Exception 异常
     */
    public static PublicKey getPublickey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey
                .getBytes()));
        return keyFactory.generatePublic(bobPubKeySpec);
    }
}