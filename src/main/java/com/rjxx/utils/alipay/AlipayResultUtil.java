package com.rjxx.utils.alipay;

import com.rjxx.taxeasy.dto.alipay.AlipayResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/5/15
 */
public class AlipayResultUtil {
    public static AlipayResult result(String code,String msg){
        Map map = new HashMap();
        map.put("resultCode", code);
        map.put("resultMsg", msg);

        String sign = "";
        try {
            sign= AlipaySignUtil.getSign(map,AlipaySignUtil.getPrivateKey(AlipaySignUtil.PRIKEY));
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
