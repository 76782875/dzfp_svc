package com.rjxx.utils.alipay;

import com.rjxx.taxeasy.dto.alipay.AlipayResult;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/5/15
 */
public class AlipayResultUtil {
//    public static AlipayResult result(String code,String msg,String fpdm,String fphm){
//        AlipayResult result = new AlipayResult();
//        result.setResultCode(code);
//        result.setResultMsg(msg);
//        result.setEinvCode(fpdm);
//        result.setEinvNo(fphm);
//        return result;
//    }

    public static AlipayResult result(String code,String msg){
        Map map = new HashMap();
        map.put("resultCode", code);
        map.put("resultMsg", msg);
        //获取原始加签内容
        String signatureContent = AlipaySignUtil.getSignatureContent(map);

        String sign = "";
        //获取签名,得到签名之后进行urlencode
        try {
            sign = URLEncoder.encode(AlipaySignUtil.sign(signatureContent, AlipaySignUtil.getPrivateKey(AlipaySignUtil.PRIKEY)), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        AlipayResult result = new AlipayResult();
        result.setResultCode(code);
        result.setResultMsg(msg);
        result.setSign(sign);
        return result;
    }
}
