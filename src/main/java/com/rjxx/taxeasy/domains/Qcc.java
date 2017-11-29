package com.rjxx.taxeasy.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017-11-29.
 */
@Entity
@Table(name="t_qcc")
public class Qcc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;


    @Column(name="gsmc")
    protected String gsmc;

    @Column(name="frmc")
    protected String frmc;

    @Column(name="nsrsbh")
    protected String nsrsbh;

    @Column(name="qylx")
    protected String qylx;

    @Column(name="qyzt")
    protected String qyzt;

    @Column(name="dz")
    protected String dz;

    @Column(name="dh")
    protected String dh;

    @Column(name="khyh")
    protected String khyh;

    @Column(name="yhzh")
    protected String yhzh;

    @Column(name = "clrq")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date clrq;

    @Column(name="qyzch")
    protected String qyzch;

    @Column(name = "lrsj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date lrsj;

    @Column(name="lrry")
    protected String lrry;

    @Column(name = "xgsj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date xgsj;

    @Column(name="xgry")
    protected String xgry;

    @Override
    public String toString() {
        return "Qcc{" +
                "id=" + id +
                ", gsdm='" + gsmc + '\'' +
                ", frmc='" + frmc + '\'' +
                ", nsrsbh='" + nsrsbh + '\'' +
                ", qylx='" + qylx + '\'' +
                ", qyzt='" + qyzt + '\'' +
                ", dz='" + dz + '\'' +
                ", dh='" + dh + '\'' +
                ", khyh='" + khyh + '\'' +
                ", yhzh='" + yhzh + '\'' +
                ", clrq=" + clrq +
                ", qyzch='" + qyzch + '\'' +
                ", lrsj=" + lrsj +
                ", lrry='" + lrry + '\'' +
                ", xgsj=" + xgsj +
                ", xgry='" + xgry + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGsmc() {
        return gsmc;
    }

    public void setGsmc(String gsmc) {
        this.gsmc = gsmc;
    }

    public String getFrmc() {
        return frmc;
    }

    public void setFrmc(String frmc) {
        this.frmc = frmc;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getQylx() {
        return qylx;
    }

    public void setQylx(String qylx) {
        this.qylx = qylx;
    }

    public String getQyzt() {
        return qyzt;
    }

    public void setQyzt(String qyzt) {
        this.qyzt = qyzt;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getKhyh() {
        return khyh;
    }

    public void setKhyh(String khyh) {
        this.khyh = khyh;
    }

    public String getYhzh() {
        return yhzh;
    }

    public void setYhzh(String yhzh) {
        this.yhzh = yhzh;
    }

    public Date getClrq() {
        return clrq;
    }

    public void setClrq(Date clrq) {
        this.clrq = clrq;
    }

    public String getQyzch() {
        return qyzch;
    }

    public void setQyzch(String qyzch) {
        this.qyzch = qyzch;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getLrry() {
        return lrry;
    }

    public void setLrry(String lrry) {
        this.lrry = lrry;
    }

    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public String getXgry() {
        return xgry;
    }

    public void setXgry(String xgry) {
        this.xgry = xgry;
    }
}
