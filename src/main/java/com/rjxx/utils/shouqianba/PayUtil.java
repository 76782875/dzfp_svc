package com.rjxx.utils.shouqianba;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.taxeasy.dto.shouqianba.MerchantCreate;
import com.rjxx.taxeasy.dto.shouqianba.QueryResult;
import com.rjxx.utils.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.*;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/4
 */
public class PayUtil {

    private static Logger logger = LoggerFactory.getLogger(PayUtil.class);

    //测试信息
    private static String app_id = "2018060400000745";
    private static String vendor_sn = "91800407";
    private static String vendor_key = "8e8d34115a0162d42068d9d685f68258";
    private static String TEST_CODE = "37819981";
    private static String COMPANY_TEST_CODE = "44265221";

    private static String WEB_API_DOMAIN="https://api.shouqianba.com";
    private static String WAP_API_PRO_URL = "https://m.wosai.cn/qr/gateway";
    private static String VENDOR_API_URL = "https://api-sandbox.test.shouqianba.com";


    /**
     * 计算字符串的MD5值
     *
     * @param signStr:签名字符串
     * @return
     */
    public static String getSign(String signStr) {
        try {
            String md5 = DigestUtils.md5Hex(signStr.getBytes("UTF-8"));
            return md5;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 终端激活
     *
     * @param code:激活码
     * @param vendor_sn:服务商序列号
     * @param vendor_key:服务商密钥
     * @return {terminal_sn:"$终端号",terminal_key:"$终端密钥"}
     */
    public static JSONObject activate(String vendor_sn, String vendor_key, String appId,String code,String deviceId,String storeNo) {
        String url = WEB_API_DOMAIN + "/terminal/activate";
        JSONObject params = new JSONObject();
        try {
            params.put("app_id", appId);                                          //APPID
            params.put("code", code);                                          //激活码
//            params.put("type", "2");                                           //设备类型可以不提供。默认为"2"
//            params.put("os_info", "Mac OS");                              //当前系统信息
            params.put("device_id", deviceId);   //设备唯一身份ID
//            params.put("sdk_version", "Java SDK v1.0");     //SDK版本
            params.put("client_sn", storeNo);//第三方终端号

            String sign = getSign(params.toString() + vendor_key);
            String result = HttpUtil.httpPost(url, params.toString(), sign, vendor_sn);
            JSONObject retObj = JSON.parseObject(result);
            String resCode = retObj.get("result_code").toString();
            if (!resCode.equals("200"))
                return null;
            String responseStr = retObj.get("biz_response").toString();
            JSONObject terminal = JSON.parseObject(responseStr);
            if (terminal.get("terminal_sn") == null || terminal.get("terminal_key") == null)
                return null;
            return terminal;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 终端签到
     *
     * @param terminal_sn:终端号
     * @param terminal_key:终端密钥
     * @return {terminal_sn:"$终端号",terminal_key:"$终端密钥"}
     */
    public JSONObject checkin(String terminal_sn, String terminal_key) {
        String url = WEB_API_DOMAIN + "/terminal/checkin";
        JSONObject params = new JSONObject();
        try {
            params.put("terminal_sn", terminal_sn);                            //终端号
            params.put("device_id", "50a87771-ca8a-4952-a493-9504c39ab495");   //设备唯一身份ID
            params.put("os_info", "Mac OS");                              //当前系统信息
            params.put("sdk_version", "Java SDK v1.0");                      //SDK版本
            String sign = getSign(params.toString() + terminal_key);
            String result = HttpUtil.httpPost(url, params.toString(), sign, terminal_sn);
            JSONObject retObj = JSON.parseObject(result);
            String resCode = retObj.get("result_code").toString();
            if (!resCode.equals("200"))
                return null;
            String responseStr = retObj.get("biz_response").toString();
            JSONObject terminal = JSON.parseObject(responseStr);
            if (terminal.get("terminal_sn") == null || terminal.get("terminal_key") == null)
                return null;
            return terminal;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 查询
     *
     * @param terminal_sn:终端号
     * @param terminal_key:终端密钥
     * @param client_sn:商户系统订单号,必须在商户系统内唯一；且长度不超过64字节
     * @param sn:收钱吧系统内部唯一订单号
     * @return
     */
    public static QueryResult query(String terminal_sn, String terminal_key,String client_sn,String sn) {
        String url = WEB_API_DOMAIN + "/upay/v2/query";
        JSONObject params = new JSONObject();
        try {
            params.put("terminal_sn", terminal_sn);
            params.put("sn", sn);
            params.put("client_sn", client_sn);

            String sign = getSign(params.toString() + terminal_key);
            String result = HttpUtil.httpPost(url, params.toString(), sign, terminal_sn);
            return JSON.parseObject(result,QueryResult.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 支付（WAP_API_PRO）
     * @param terminal_sn   收钱吧终端ID
     * @param client_sn     订单号
     * @param total_amount  金额
     * @param subject       主题
     * @param operator       操作员
     * @param return_url
     */
    public static Map payIn(String terminal_sn, String terminal_key, String client_sn, String total_amount,
                                String subject, String operator, String return_url,String reflect) throws UnsupportedEncodingException {
        Map<String, String> param = new HashMap<>();
        param.put("terminal_sn", terminal_sn);
        param.put("client_sn", client_sn);
        param.put("total_amount", total_amount);
        param.put("subject", StringUtil.changeCharset(subject,"iso-8859-1"));
        param.put("operator", operator);
        param.put("return_url", return_url);
        param.put("reflect", reflect);
        String signatureContent = getSignatureContent(param);//排序
        logger.info("signatureContent={}", signatureContent);
        String sign = DigestUtils.md5Hex(signatureContent + "&key=" + terminal_key).toUpperCase();
        logger.info("sign={}", sign);
        String paramString = signatureContent + "&sign=" + sign;
        String redirectUrl = WAP_API_PRO_URL + "?" + paramString;
        logger.info("redirectUrl={}", redirectUrl);
        Map result = new HashMap();
        result.put("url", redirectUrl);
        result.put("sign", sign);
        return result;
    }

    private static String getSignatureContent(Map<String, String> params) {
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
     * 自动撤单
     * @param  terminal_sn:终端号
     * @param  terminal_key:终端密钥
     * @return
     */
    public static QueryResult cancel(String terminal_sn,String terminal_key,String sn,String client_sn){
        String url = WEB_API_DOMAIN + "/upay/v2/cancel";
        JSONObject params = new JSONObject();
        try{
            params.put("terminal_sn",terminal_sn);           //终端号
            params.put("sn",sn);             //收钱吧系统内部唯一订单号
            params.put("client_sn",client_sn);  //商户系统订单号,必须在商户系统内唯一；且长度不超过64字节

            String sign = getSign(params.toString() + terminal_key);
            String result = HttpUtil.httpPost(url, params.toString(),sign,terminal_sn);
            return  JSON.parseObject(result,QueryResult.class);
        }catch (Exception e){
            return null;
        }
    }

    public static void merchantCreate(String terminal_key,String terminal_sn,MerchantCreate merchantCreate){
        String url = VENDOR_API_URL;
        String param = JSON.toJSONString(merchantCreate);
        String sign = getSign(param + terminal_key);
        try {
            String result = HttpUtil.httpPost(url, param, sign, terminal_sn);
            logger.info("result={}",result);
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        JSONObject activate = activate(vendor_sn, vendor_key, app_id, COMPANY_TEST_CODE, "rjxx_test", null);
//        System.out.println(activate.toJSONString());

        int count = 2;
        String orderNo = "rjtest";
        String clientSn = orderNo+count;
        String terminal_sn = "100007450004133032";
        String terminal_key = "1e81ddd4878a98461b1b1ccd5b2d0628";
        String returnUrl = "http://kpt.datarj.com/";
        String price = "1";
        String subject = "测试";
        String oprator = "wyh";

        QueryResult query = query(terminal_sn, terminal_key, clientSn, null);
        System.out.println(query.toString());
//
//        Map map = payIn(terminal_sn, terminal_key, clientSn, price, subject, oprator, returnUrl, "wyh");
//        System.out.println((String) map.get("url"));


//        QueryResult cancel = cancel(terminal_sn, terminal_key, null, clientSn);
//        System.out.println(cancel.toString());


//        MerchantCreate merchantCreate = new MerchantCreate();
//        merchantCreate.setVendor_sn(vendor_sn);
//        merchantCreate.setVendor_app_id(app_id);
//        merchantCreate.setName("容津测试商户");
//        merchantCreate.setContact_name("容津");
//        merchantCreate.setContact_cellphone("021-33566700");
//        merchantCreate(terminal_sn,terminal_key,merchantCreate);
    }
}
