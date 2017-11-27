package com.rjxx.taxeasy.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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

    @Column(name = "weixinorderno")
    private String weixinOderno;

    @Column(name = "count")
    private int count;

    /**
     * 录入时间 系统时间
     */
    @Column(name="lrsj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date lrsj;

    /**
     * 修改时间
     */
    @Column(name="xgsj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date xgsj;

    /**
     * 微信插卡时间
     */
    @Column(name="wxcksj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date wxcksj;

    /**
     * 发票管家插卡时间
     */
    @Column(name="fpgjcksj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date fpgjcksj;

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
                ", lrsj=" + lrsj +
                ", xgsj=" + xgsj +
                ", wxcksj=" + wxcksj +
                ", fpgjcksj=" + fpgjcksj +
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

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public Date getWxcksj() {
        return wxcksj;
    }

    public void setWxcksj(Date wxcksj) {
        this.wxcksj = wxcksj;
    }

    public Date getFpgjcksj() {
        return fpgjcksj;
    }

    public void setFpgjcksj(Date fpgjcksj) {
        this.fpgjcksj = fpgjcksj;
    }
}
