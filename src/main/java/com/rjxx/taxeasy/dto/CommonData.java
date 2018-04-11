package com.rjxx.taxeasy.dto;

/**
 * @author kzx
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
public class CommonData {
    private String appId;
    private String sign;
    private SellerData seller;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public SellerData getSeller() {
        return seller;
    }

    public void setSeller(SellerData seller) {
        this.seller = seller;
    }
}
