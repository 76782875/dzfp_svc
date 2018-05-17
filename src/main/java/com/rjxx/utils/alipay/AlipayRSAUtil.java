package com.rjxx.utils.alipay;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/5/9
 */
public class AlipayRSAUtil {

    private static Logger logger = LoggerFactory.getLogger(AlipayRSAUtil.class);
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
        return new String(Base64.encodeBase64(signature.sign()));
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
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
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
        encodedKey = Base64.decodeBase64(encodedKey);
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

    /**
     * 获得需要签名的数据，按照参数名字母升序的顺序将所有参数用&连接起来
     *
     * @param params 待签名参数集
     * @return 排好序的待签名字符串
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

    public static String toSign(Map<String, String> params,String privateKey) {
        try {
            PrivateKey prikey = getPrivateKey(privateKey);
            //获取原始加签内容
            String signatureContent = getSignatureContent(params);
            //获取签名,得到签名之后,将该签名字符串放于返回字段sign,返回给支付宝
            String sign = AlipayRSAUtil.sign(signatureContent, prikey);
            return signatureContent + "&sign=" + sign;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean toVerify(String params, String publicKey,String sign) throws Exception {
        PublicKey pubkey = getPublickey(publicKey);
        String[] result = params.split("&sign=");
        String resultSign = result[1];
        logger.info("signEquals=",resultSign==sign);
        String signatureContent = result[0];
        return AlipayRSAUtil.verify(signatureContent, pubkey, sign);
    }



    public static void main(String[] args) throws Exception {
        KeyPair keyPair = AlipayRSAUtil.getKeyPair();
        String params = signTest(keyPair.getPrivate());
        System.out.println(params);
        verifyTest(params, keyPair.getPublic());
    }

    public static void verifyTest(String params, PublicKey publicKey) throws Exception {
        String[] result = params.split("&sign=");
        String sign = result[1];
        String signatureContent = result[0];
        System.out.println(signatureContent);
        System.out.println(AlipayRSAUtil.verify(signatureContent, publicKey, sign));
    }

    /**
     * 加签demo方法
     */
    public static String signTest(PrivateKey privateKey) {
            try {
                //获取待验签原始数据,以下数据均从支付宝的请求内容中获取
                Map<String, String> params = new HashMap<String, String>();
                params.put("invoiceAmount", "开票金额");
                params.put("orderNo", "订单号");
                params.put("mShortName", "商户的品牌名称简称");
                params.put("subShortName", "商户门店简称");
                params.put("customParam1", "自定义参数1");
                params.put("customParam2", "自定义参数2");
                //获取原始加签内容
                String signatureContent = getSignatureContent(params);
                //获取签名,得到签名之后,将该签名字符串放于返回字段sign,返回给支付宝
                String sign = AlipayRSAUtil.sign(signatureContent, privateKey);
                return signatureContent + "&sign=" + sign;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
    }
}
