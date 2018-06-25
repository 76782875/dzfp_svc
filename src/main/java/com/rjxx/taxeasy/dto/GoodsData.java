package com.rjxx.taxeasy.dto;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author: zsq
 * @date: 2018/6/22 16:15
 * @describe:
 */
public class GoodsData {
    @JSONField(ordinal=1)
    private String appId;
    @JSONField(ordinal=2)
    private String sign;
    @JSONField(ordinal=3)
    private List<GoodsDetail> data;//商品list

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

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
