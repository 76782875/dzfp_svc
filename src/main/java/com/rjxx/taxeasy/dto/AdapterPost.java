package com.rjxx.taxeasy.dto;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
public class AdapterPost {
    private String appId;
    private String taxNo;
    private String clientNo;
    private String sign;
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

    @Override
    public String toString() {
        return "AdapterPost{" +
                "appId='" + appId + '\'' +
                ", taxNo='" + taxNo + '\'' +
                ", clientNo='" + clientNo + '\'' +
                ", sign='" + sign + '\'' +
                ", data=" + data +
                '}';
    }
}
