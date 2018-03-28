package com.rjxx.taxeasy.dto;

/**
 * @author: zsq
 * @date: 2018/3/27 16:56
 * @describe: 发票红冲对象
 */
public class AdapterPostRedData {

    private String appId;

    private String reqType;//请求类型

    private String sign;

    private AdapterRedData data;//待红冲数据

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public AdapterRedData getData() {
        return data;
    }

    public void setData(AdapterRedData data) {
        this.data = data;
    }
}
