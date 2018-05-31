package com.rjxx.utils.alipay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayEbppInvoiceApplyResultSyncRequest;
import com.alipay.api.request.AlipayEbppInvoiceSycnRequest;
import com.alipay.api.response.AlipayEbppInvoiceApplyResultSyncResponse;
import com.alipay.api.response.AlipayEbppInvoiceSycnResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjxx.taxeasy.dao.WxfpxxJpaDao;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.domains.WxFpxx;
import com.rjxx.utils.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.DecimalFormat;
import java.util.*;

@Component
public class AlipaySignUtil {

    @Autowired
    private WxfpxxJpaDao wxfpxxJpaDao;

    private static Logger logger = LoggerFactory.getLogger(AlipaySignUtil.class);

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

    public static String SYNC_PRIKEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKK0PXoLKnBkgtOl" +
            "0kvyc9X2tUUdh/lRZr9RE1frjr2ZtAulZ+Moz9VJZFew1UZIzeK0478obY/DjHmD3GMfqJoTguVqJ2MEg+mJ8hJKWelvKL" +
            "gfFBNliAw+/9O6Jah9Q3mRzCD8pABDEHY7BM54W7aLcuGpIIOa/qShO8dbXn+FAgMBAAECgYA8+nQ380taiDEIBZPFZv7G6AmT" +
            "97doV3u8pDQttVjv8lUqMDm5RyhtdW4n91xXVR3ko4rfr9UwFkflmufUNp9HU9bHIVQS+HWLsPv9GypdTSNNp+nDn4JExUtAakJx" +
            "ZmGhCu/WjHIUzCoBCn6viernVC2L37NL1N4zrR73lSCk2QJBAPb/UOmtSx+PnA/mimqnFMMP3SX6cQmnynz9+63JlLjXD8rowRD2Z0" +
            "3U41Qfy+RED3yANZXCrE1V6vghYVmASYsCQQCoomZpeNxAKuUJZp+VaWi4WQeMW1KCK3aljaKLMZ57yb5Bsu+P3odyBk1AvYIPvd" +
            "ajAJiiikRdIDmi58dqfN0vAkEAjFX8LwjbCg+aaB5gvsA3t6ynxhBJcWb4UZQtD0zdRzhKLMuaBn05rKssjnuSaRuSgPaHe5O" +
            "kOjx6yIiOuz98iQJAXIDpSMYhm5lsFiITPDScWzOLLnUR55HL/biaB1zqoODj2so7G2JoTiYiznamF9h9GuFC2TablbINq80" +
            "U2NcxxQJBAMhw06Ha/U7qTjtAmr2qAuWSWvHU4ANu2h0RxYlKTpmWgO0f47jCOQhdC3T/RK7f38c7q8uPyi35eZ7S1e/PznY=";

    public static String SYNC_PUBKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3" +
            "mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6" +
            "FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";

    public static void main(String[] args) throws UnsupportedEncodingException {
        Map sendParam = new HashMap();
        sendParam.put("invoiceAmount", "10");
        sendParam.put("orderNo", "test1");
        sendParam.put("mShortName", "STANDARD_INVOICE");
        sendParam.put("subShortName", "STANDARD_INVOICE");
        sendParam.put("resultUrl", URLEncoder.encode("http://fpjtest.datarj.com/web/template/#/succes/?t=+" + System.currentTimeMillis() + "&ppdm=rjxx", "utf-8"));
        String params = null;
        try {
            params = AlipaySignUtil.getSignContent(sendParam, AlipaySignUtil.getPrivateKey(AlipaySignUtil.PRIKEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = URLEncoder.encode("/www/route.htm?scene=STANDARD_INVOICE&invoiceParams=" + URLEncoder.encode(params, "utf-8"), "utf-8");
        String redirectUrl = "alipays://platformapi/startapp?" +
                "appId=20000920&startMultApp=YES&appClearTop=false&url=" + url;
        System.out.println(redirectUrl);
    }

    /**
     * 验签
     */
    public static boolean getVerify(Map params, PublicKey publicKey) throws Exception {
        String p = getSignatureContent(params);
        //解析参数
        Map<String, String> map = restoreMap(p, "&");
        //urldecode 签名
        String sign = URLDecoder.decode(map.get("sign"), "utf-8");
        //去除签名参数
        map.remove("sign");
        //生成待验签字符串
        String signatureContent = getSignatureContent(map);
        logger.info("signatureContent={}",signatureContent);
        logger.info("result={}",verify(signatureContent, publicKey, sign));
        return verify(signatureContent, publicKey, sign);
    }

    /**
     * 加签
     */
    public static String getSignContent(Map params,PrivateKey privateKey) {
        try {
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
     * 获取sign
     */
    public static String getSign(Map params,PrivateKey privateKey) {
        try {
            String signatureContent = getSignatureContent(params);
            //获取签名,得到签名之后进行urlencode
            String sign = URLEncoder.encode(sign(signatureContent, privateKey), "utf-8");
            return sign;
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
    private static String sign(String data, PrivateKey privateKey) throws Exception {
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
    private static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(publicK);
        signature.update(srcData.getBytes("GBK"));
        return signature.verify(org.apache.commons.codec.binary.Base64.decodeBase64(sign.getBytes()));
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

    /**
     * 同步发票到支付宝发票管家，成功返回支付宝的url，失败则返回null
     *
     * @param kpls
     * @param kpspmxList
     * @param mShortName
     * @param subMShortName
     * @return
     */
    public static String syncInvoiceAlipay(String applyId, Kpls kpls, List<Kpspmx> kpspmxList, String mShortName, String subMShortName) throws Exception {
        String serverUrl = "http://openapi.stable.dl.alipaydev.com/gateway.do";
        String appId = "2014060600164699";
        String privateKey = AlipaySignUtil.SYNC_PRIKEY;
        String format = AlipayConstant.FORMAT;
        String charset = AlipayConstant.CHARSET;
        String alipayPulicKey = AlipaySignUtil.SYNC_PUBKEY;
        String signType = "RSA";
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl,
                appId, privateKey,
                format, charset, alipayPulicKey,
                signType);
        AlipayEbppInvoiceSycnRequest alipayEbppInvoiceSycnRequest = new AlipayEbppInvoiceSycnRequest();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        AlipayBizObject alipayBizObject = new AlipayBizObject();
        alipayBizObject.setM_short_name(mShortName);
        alipayBizObject.setSub_m_short_name(subMShortName);
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        List<InvoiceInfo> invoiceInfoList = new ArrayList<>();
        invoiceInfoList.add(invoiceInfo);
        alipayBizObject.setInvoice_info(invoiceInfoList);

//        invoiceInfo.setUser_id(userId);
        invoiceInfo.setApply_id(applyId);
        invoiceInfo.setInvoice_code(kpls.getFpdm());
        invoiceInfo.setInvoice_no(kpls.getFphm());
        invoiceInfo.setRegister_no(kpls.getXfsh());
        invoiceInfo.setInvoice_amount(decimalFormat.format(kpls.getJshj()));
        invoiceInfo.setInvoice_date(DateFormatUtils.format(kpls.getKprq(), "yyyy-MM-dd"));
        List<InvoiceContent> invoiceContentList = new ArrayList<>();
        invoiceInfo.setInvoice_content(invoiceContentList);

        for (Kpspmx kpspmx : kpspmxList) {
            InvoiceContent invoiceContent = new InvoiceContent();
            invoiceContentList.add(invoiceContent);
            invoiceContent.setItem_name(kpspmx.getSpmc());
            invoiceContent.setItem_no(kpspmx.getSpdm());
            if (kpspmx.getSpdj() != null) {
                invoiceContent.setItem_price(decimalFormat.format(kpspmx.getSpdj()));
            }
            if (kpspmx.getSps() != null) {
                invoiceContent.setItem_quantity(kpspmx.getSps());
            }
            invoiceContent.setRow_type(Integer.valueOf(kpspmx.getFphxz()));
            invoiceContent.setItem_sum_price(decimalFormat.format(kpspmx.getSpje()));
            invoiceContent.setItem_tax_price(decimalFormat.format(kpspmx.getSpse()));
            invoiceContent.setItem_tax_rate(decimalFormat.format(kpspmx.getSpsl()));
            invoiceContent.setItem_unit(kpspmx.getSpdw());
            invoiceContent.setItem_amount(decimalFormat.format((kpspmx.getSpje() + kpspmx.getSpse())));
        }
        invoiceInfo.setOut_biz_no(kpls.getFpdm() + kpls.getFphm());
        invoiceInfo.setInvoice_type("blue");
        String pdfUrl = kpls.getPdfurl();
        String imgUrl = pdfUrl.replace(".pdf", ".jpg");
        invoiceInfo.setInvoice_img_url(imgUrl);
        InvoiceTitle invoiceTitle = new InvoiceTitle();
        invoiceInfo.setInvoice_title(invoiceTitle);
//        invoiceTitle.setUser_id(userId);
        invoiceTitle.setTitle_name(kpls.getGfmc());
        if (StringUtils.isNotBlank(kpls.getGfsh())) {
            invoiceTitle.setTitle_type("CORPORATION");
        } else {
            invoiceTitle.setTitle_type("PERSONAL");
        }
        invoiceTitle.setUser_mobile(kpls.getGfdh());
        invoiceTitle.setLogon_id("");
        invoiceTitle.setUser_email("");
        invoiceTitle.setIs_default(false);
        invoiceTitle.setTax_register_no(kpls.getGfsh());
        invoiceTitle.setUser_address(kpls.getGfdz());
        invoiceTitle.setOpen_bank_name(kpls.getGfyh());
        invoiceTitle.setOpen_bank_account(kpls.getGfyhzh());
        invoiceInfo.setInvoice_file_data("");
        invoiceInfo.setInvoice_fake_code(kpls.getJym());
        invoiceInfo.setOut_invoice_id(kpls.getFpdm() + kpls.getFphm());
        invoiceInfo.setFile_download_type("pdf");
        invoiceInfo.setOriginal_blue_invoice_code("");
        invoiceInfo.setOriginal_blue_invoice_no("");
        invoiceInfo.setRegister_name(kpls.getXfmc());
        invoiceInfo.setRegister_phone_no(kpls.getXfdh());
        invoiceInfo.setRegister_address(kpls.getXfdz());
        invoiceInfo.setExtend_fields("");
        invoiceInfo.setInvoice_operator(kpls.getKpr());
        invoiceInfo.setFile_download_url(pdfUrl);
        invoiceInfo.setTax_amount(decimalFormat.format(kpls.getHjse()));
        invoiceInfo.setSum_amount(decimalFormat.format(kpls.getJshj()));
        if ("12".equals(kpls.getFpzldm())) {
            invoiceInfo.setTax_type("PLAIN");
        } else if ("01".equals(kpls.getFpzldm())) {
            invoiceInfo.setTax_type("SPECIAL");
        } else if ("02".equals(kpls.getFpzldm())) {
            invoiceInfo.setTax_type("PLAIN_INVOICE");
        }
        invoiceInfo.setRegister_bank_name(kpls.getXfyh());
        invoiceInfo.setRegister_bank_account(kpls.getXfyhzh());
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(alipayBizObject);
        result = result.replace("_default", "is_default");
        alipayEbppInvoiceSycnRequest.setBizContent(result);
        AlipayEbppInvoiceSycnResponse response = alipayClient.execute(alipayEbppInvoiceSycnRequest);
        if (response.isSuccess()) {
            return response.getUrl();
        } else {
            return null;
        }
    }

    /**
     * 拒绝开票
     *
     * @param applyId
     * @param reason
     */
    public void refuse(String orderNo, String applyId, String reason) {
        String serverUrl = "http://openapi.stable.dl.alipaydev.com/gateway.do";
        String appId = "2014060600164699";
        String privateKey = AlipaySignUtil.SYNC_PRIKEY;
        String format = AlipayConstant.FORMAT;
        String charset = AlipayConstant.CHARSET;
        String alipayPulicKey = AlipaySignUtil.SYNC_PUBKEY;
        String signType = "RSA";

        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey,
                format, charset, alipayPulicKey, signType);
        AlipayEbppInvoiceApplyResultSyncRequest request = new AlipayEbppInvoiceApplyResultSyncRequest();

        Map param = new HashMap();
        param.put("apply_id", applyId);
        param.put("result", "失败");
        param.put("result_code", "FAIL");
        param.put("result_msg", reason);

        String bizContent = JSON.toJSONString(param);
        request.setBizContent(bizContent);
        AlipayEbppInvoiceApplyResultSyncResponse response = null;
        try {
            response = alipayClient.execute(request);
            logger.info("result={}",JSON.toJSONString(response));
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            logger.info("------支付宝拒绝开票成功------");
            WxFpxx wxFpxx = wxfpxxJpaDao.selectByWeiXinOrderNo(orderNo);
            if(wxFpxx!=null){
                int coun = wxFpxx.getCount() + 1;
                logger.info("拒绝开票----更新计数" + coun);
                wxFpxx.setCount(coun);
                wxfpxxJpaDao.save(wxFpxx);
            }
        } else {
            logger.info("------支付宝拒绝开票失败-------");
            logger.info(response.getCode() + "--------" + response.getMsg());
            logger.info(response.getSubCode() + "--------" + response.getSubMsg());
        }
    }


}