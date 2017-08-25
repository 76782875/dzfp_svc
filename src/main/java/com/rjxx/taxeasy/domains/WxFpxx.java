package com.rjxx.taxeasy.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wangyahui on 2017/8/17 0017
 */
@Entity
@Table(name="wx_fpxx")
public class WxFpxx {
    @Id
    @Column(name = "orderno")
    private String orderNo;

    @Column(name = "openid")
    private String openId;

    @Column(name = "userid")
    private String userid;

    @Column(name = "q")
    private String q;

    @Column(name = "tqm")
    private String tqm;

    @Column(name = "gsdm")
    private String gsdm;

    @Column(name = "code")
    private String code;
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getTqm() {
        return tqm;
    }

    public void setTqm(String tqm) {
        this.tqm = tqm;
    }

    public String getGsdm() {
        return gsdm;
    }

    public void setGsdm(String gsdm) {
        this.gsdm = gsdm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "WxFpxx{" +
                "orderNo='" + orderNo + '\'' +
                ", openId='" + openId + '\'' +
                ", userid='" + userid + '\'' +
                ", q='" + q + '\'' +
                ", tqm='" + tqm + '\'' +
                ", gsdm='" + gsdm + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
