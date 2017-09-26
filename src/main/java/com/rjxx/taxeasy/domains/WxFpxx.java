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

    @Column(name = "wxtype")
    private String wxtype;

    @Column(name = "kplsh")
    private String kplsh;

    @Column(name = "weixin_orderno")
    private String weixinOderno;

    @Column(name = "count")
    private int count;

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

    public String getKplsh() {
        return kplsh;
    }

    public void setKplsh(String kplsh) {
        this.kplsh = kplsh;
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
                ", wxtype='" + wxtype + '\'' +
                ", kplsh='" + kplsh + '\'' +
                ", weixinOderno='" + weixinOderno + '\'' +
                ", count=" + count +
                '}';
    }

    public String getWxtype() {
        return wxtype;
    }

    public void setWxtype(String wxtype) {
        this.wxtype = wxtype;
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

    public String getWeixinOderno() {
        return weixinOderno;
    }

    public void setWeixinOderno(String weixinOderno) {
        this.weixinOderno = weixinOderno;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
