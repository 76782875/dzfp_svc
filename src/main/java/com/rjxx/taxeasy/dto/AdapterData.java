package com.rjxx.taxeasy.dto;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
public class AdapterData {

    private String serialNumber;
    private String invType;
    private String version;
    private String drawer;
    private String payee;
    private String reviewer;
    private String datasource;
    private String openid;
    private AdapterDataSeller seller;
    private AdapterDataOrder order;

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

    @Override
    public String toString() {
        return "AdapterData{" +
                "serialNumber='" + serialNumber + '\'' +
                ", invType='" + invType + '\'' +
                ", version='" + version + '\'' +
                ", drawer='" + drawer + '\'' +
                ", payee='" + payee + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", datasource='" + datasource + '\'' +
                ", openid='" + openid + '\'' +
                ", seller=" + seller +
                ", order=" + order +
                '}';
    }
}
