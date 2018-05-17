package com.rjxx.utils.alipay;

import com.rjxx.taxeasy.dto.alipay.AlipayResult;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/5/15
 */
public class AlipayResultUtil {
    public static final String APPLY_SUCCESS = "APPLY_SUCCESS";
    public static final String INVOICE_SUCCESS = "INVOICE_SUCCESS";
    public static final String INVOICE_ERROR = "INVOICE_ERROR";
    public static final String INVOICE_IS_APPLIED = "INVOICE_IS_APPLIED";
    public static final String INVOICE_PARAM_ILLEGAL = "INVOICE_PARAM_ILLEGAL";
    public static final String SYSTEM_ERROR = "SYSTEM_ERROR";

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
        result.setEinvCode(null);
        result.setEinvNo(null);
        return result;
    }
}
