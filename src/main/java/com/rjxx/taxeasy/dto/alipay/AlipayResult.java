package com.rjxx.taxeasy.dto.alipay;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/5/15
 */
public class AlipayResult {
    private String resultMsg;//业务结果说明
    private String resultCode;//业务结果码
    private String einvCode;//发票代码（开票成功时不能为空）
    private String einvNo;//发票号码（开票成功时不能为空）

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getEinvCode() {
        return einvCode;
    }

    public void setEinvCode(String einvCode) {
        this.einvCode = einvCode;
    }

    public String getEinvNo() {
        return einvNo;
    }

    public void setEinvNo(String einvNo) {
        this.einvNo = einvNo;
    }
}
