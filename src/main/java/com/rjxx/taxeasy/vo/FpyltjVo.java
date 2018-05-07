package com.rjxx.taxeasy.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: zsq
 * @date: 2018/5/3 15:47
 * @describe:
 */
public class FpyltjVo implements Serializable {
    private Double sumhjje;
    private Integer fpsl;
    private String gsdm;
    private Integer skpid;
    private Integer xfid;
    private String fpzldm;
    private String fpztdm;
    private Date kprq;

    public Double getSumhjje() {
        return sumhjje;
    }

    public void setSumhjje(Double sumhjje) {
        this.sumhjje = sumhjje;
    }

    public Integer getFpsl() {
        return fpsl;
    }

    public void setFpsl(Integer fpsl) {
        this.fpsl = fpsl;
    }

    public String getGsdm() {
        return gsdm;
    }

    public void setGsdm(String gsdm) {
        this.gsdm = gsdm;
    }

    public Integer getSkpid() {
        return skpid;
    }

    public void setSkpid(Integer skpid) {
        this.skpid = skpid;
    }

    public String getFpzldm() {
        return fpzldm;
    }

    public void setFpzldm(String fpzldm) {
        this.fpzldm = fpzldm;
    }

    public String getFpztdm() {
        return fpztdm;
    }

    public void setFpztdm(String fpztdm) {
        this.fpztdm = fpztdm;
    }

    public Date getKprq() {
        return kprq;
    }

    public void setKprq(Date kprq) {
        this.kprq = kprq;
    }

    public Integer getXfid() {
        return xfid;
    }

    public void setXfid(Integer xfid) {
        this.xfid = xfid;
    }
}
