package com.rjxx.taxeasy.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
public class AdapterData {
    @JSONField(ordinal=1)
    private String serialNumber;
    @JSONField(ordinal=2)
    private String drawer;
    @JSONField(ordinal=3)
    private String payee;
    @JSONField(ordinal=4)
    private String reviewer;
    @JSONField(ordinal=5)
    private AdapterDataSeller seller;
    @JSONField(ordinal=6)
    private AdapterDataOrder order;
    @JSONField(ordinal=7)
    private String invType;
    @JSONField(ordinal=8)
    private String version;
    @JSONField(ordinal=9)
    private String datasource;
    @JSONField(ordinal=10)
    private String openid;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public AdapterDataSeller getSeller() {
        return seller;
    }

    public void setSeller(AdapterDataSeller seller) {
        this.seller = seller;
    }

    public AdapterDataOrder getOrder() {
        return order;
    }

    public void setOrder(AdapterDataOrder order) {
        this.order = order;
    }
}
