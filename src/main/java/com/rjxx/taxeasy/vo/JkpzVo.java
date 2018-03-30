package com.rjxx.taxeasy.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: zsq
 * @date: 2018/3/21 10:15
 * @describe:
 */
public class JkpzVo {
    private Integer id;

    private Integer mbid;

    private String cszff;

    private Integer cszffid;

    private Integer pzbid;

    private String lrry;

    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lrsj;

    private String xgry;

    private Date xgsj;

    private String pzcsm;

    private String pzcsmc;

    private String csm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMbid() {
        return mbid;
    }

    public void setMbid(Integer mbid) {
        this.mbid = mbid;
    }

    public String getCszff() {
        return cszff;
    }

    public void setCszff(String cszff) {
        this.cszff = cszff;
    }

    public String getLrry() {
        return lrry;
    }

    public void setLrry(String lrry) {
        this.lrry = lrry;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getXgry() {
        return xgry;
    }

    public void setXgry(String xgry) {
        this.xgry = xgry;
    }

    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public String getPzcsm() {
        return pzcsm;
    }

    public void setPzcsm(String pzcsm) {
        this.pzcsm = pzcsm;
    }

    public String getPzcsmc() {
        return pzcsmc;
    }

    public void setPzcsmc(String pzcsmc) {
        this.pzcsmc = pzcsmc;
    }

    public String getCsm() {
        return csm;
    }

    public void setCsm(String csm) {
        this.csm = csm;
    }

    public Integer getCszffid() {
        return cszffid;
    }

    public void setCszffid(Integer cszffid) {
        this.cszffid = cszffid;
    }

    public Integer getPzbid() {
        return pzbid;
    }

    public void setPzbid(Integer pzbid) {
        this.pzbid = pzbid;
    }
}
