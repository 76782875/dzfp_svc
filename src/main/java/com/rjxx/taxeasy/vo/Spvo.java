package com.rjxx.taxeasy.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * spvo 实体类
 * VIEW
 * 由GenEntityMysql类自动生成
 * Sun Oct 09 17:09:34 CST 2016
 *
 * @ZhangBing
 */
public class Spvo implements Serializable {

    /**
     * id
     */
    protected Integer id;

    /**
     * 商品代码
     */
    protected String spdm;

    /**
     * 商品名称
     */
    protected String spmc;

    /**
     * 税目代码
     */
    protected String smdm;

    /**
     * 商品分类代码
     */
    protected String spfldm;

    /**
     * 商品规格型号
     */
    protected String spggxh;

    /**
     * 商品单位
     */
    protected String spdw;

    /**
     * 商品单价
     */
    protected Double spdj;

    /**
     * 公司代码
     */
    protected String gsdm;

    /**
     * 有效标识(1、有效；0、禁用）
     */
    protected String yxbz;

    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date lrsj;

    /**
     * 录入人员
     */
    protected Integer lrry;

    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date xgsj;

    /**
     * 修改人员
     */
    protected Integer xgry;

    /**
     * 商品分类名称
     */
    protected String spflmc;

    /**
     * 税目名称
     */
    protected String smmc;

    /**
     * 税率
     */
    protected Double sl;

    /**
     * 商品编码
     */
    protected String spbm;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpdm() {
        return spdm;
    }

    public void setSpdm(String spdm) {
        this.spdm = spdm;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getSmdm() {
        return smdm;
    }

    public void setSmdm(String smdm) {
        this.smdm = smdm;
    }

    public String getSpfldm() {
        return spfldm;
    }

    public void setSpfldm(String spfldm) {
        this.spfldm = spfldm;
    }

    public String getSpggxh() {
        return spggxh;
    }

    public void setSpggxh(String spggxh) {
        this.spggxh = spggxh;
    }

    public String getSpdw() {
        return spdw;
    }

    public void setSpdw(String spdw) {
        this.spdw = spdw;
    }

    public Double getSpdj() {
        return spdj;
    }

    public void setSpdj(Double spdj) {
        this.spdj = spdj;
    }

    public String getGsdm() {
        return gsdm;
    }

    public void setGsdm(String gsdm) {
        this.gsdm = gsdm;
    }

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
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

    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public Integer getXgry() {
        return xgry;
    }

    public void setXgry(Integer xgry) {
        this.xgry = xgry;
    }

    public String getSpflmc() {
        return spflmc;
    }

    public void setSpflmc(String spflmc) {
        this.spflmc = spflmc;
    }

    public String getSmmc() {
        return smmc;
    }

    public void setSmmc(String smmc) {
        this.smmc = smmc;
    }

    public Double getSl() {
        return sl;
    }

    public void setSl(Double sl) {
        this.sl = sl;
    }

	public String getSpbm() {
		return spbm;
	}

	public void setSpbm(String spbm) {
		this.spbm = spbm;
	}

}

