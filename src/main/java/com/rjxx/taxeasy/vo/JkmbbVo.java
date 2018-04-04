package com.rjxx.taxeasy.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: zsq
 * @date: 2018/3/28 15:17
 * @describe:
 */
public class JkmbbVo {
    private String mbmc;
    private String mbms;
    private String gsmc;
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date lrsj;
    private Integer lrry;
    private String gsdm;
    private Integer id;
    private String mbid;

    public String getMbmc() {
        return mbmc;
    }

    public void setMbmc(String mbmc) {
        this.mbmc = mbmc;
    }

    public String getMbms() {
        return mbms;
    }

    public void setMbms(String mbms) {
        this.mbms = mbms;
    }

    public String getGsmc() {
        return gsmc;
    }

    public void setGsmc(String gsmc) {
        this.gsmc = gsmc;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public Integer getLrry() {
        return lrry;
    }

    public void setLrry(Integer lrry) {
        this.lrry = lrry;
    }

    public String getGsdm() {
        return gsdm;
    }

    public void setGsdm(String gsdm) {
        this.gsdm = gsdm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }
}
