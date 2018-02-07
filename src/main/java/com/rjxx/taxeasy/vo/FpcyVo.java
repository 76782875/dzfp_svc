package com.rjxx.taxeasy.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018-01-29.
 */
public class FpcyVo implements Serializable {

    protected Integer id;

    protected String fpdm;

    protected String fphm;

    protected String xfmc;

    protected String bxry;

    //@JsonSerialize(using = JsonDatetimeFormat.class)
    //@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected String kprq;

    protected String fpzldm;

    protected Integer cycs;

    protected Integer cycsTotal;

    protected String sjly;

    protected String fpzt;

    protected String cyrq;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFpdm() {
        return fpdm;
    }

    public void setFpdm(String fpdm) {
        this.fpdm = fpdm;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public String getXfmc() {
        return xfmc;
    }

    public void setXfmc(String xfmc) {
        this.xfmc = xfmc;
    }

    public String getBxry() {
        return bxry;
    }

    public void setBxry(String bxry) {
        this.bxry = bxry;
    }

    public String getKprq() {
        return kprq;
    }

    public void setKprq(String kprq) {
        this.kprq = kprq;
    }

    public String getFpzldm() {
        return fpzldm;
    }

    public void setFpzldm(String fpzldm) {
        this.fpzldm = fpzldm;
    }

    public Integer getCycs() {
        return cycs;
    }

    public void setCycs(Integer cycs) {
        this.cycs = cycs;
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public String getFpzt() {
        return fpzt;
    }

    public void setFpzt(String fpzt) {
        this.fpzt = fpzt;
    }

    public Integer getCycsTotal() {
        return cycsTotal;
    }

    public void setCycsTotal(Integer cycsTotal) {
        this.cycsTotal = cycsTotal;
    }

    public String getCyrq() {
        return cyrq;
    }

    public void setCyrq(String cyrq) {
        this.cyrq = cyrq;
    }
}
