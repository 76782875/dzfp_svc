package com.rjxx.taxeasy.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
public class AdapterPost {
    @JSONField(ordinal=1)
    private String appId;
    @JSONField(ordinal=2)
    private String taxNo;
    @JSONField(ordinal=3)
    private String clientNo;
    @JSONField(ordinal=4)
    private String sign;
    @JSONField(ordinal=5)
    private String viewId;
    @JSONField(ordinal=6)
    private String reqType;
    @JSONField(ordinal=7)
    private AdapterData data;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public AdapterData getData() {
        return data;
    }

    public void setData(AdapterData data) {
        this.data = data;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }
}
