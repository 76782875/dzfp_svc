package com.rjxx.utils.alipay;

import com.rjxx.taxeasy.dto.alipay.AlipayResult;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/5/15
 */
public class AlipayResultUtil {
    public static AlipayResult result(String code,String msg,String fpdm,String fphm){
        AlipayResult result = new AlipayResult();
        result.setResultCode(code);
        result.setResultMsg(msg);
        result.setEinvCode(fpdm);
        result.setEinvNo(fphm);
        return result;
    }

    public static AlipayResult result(String code,String msg){
        AlipayResult result = new AlipayResult();
        result.setResultCode(code);
        result.setResultMsg(msg);
        return result;
    }
}
