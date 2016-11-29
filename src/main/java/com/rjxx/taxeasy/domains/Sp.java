package com.rjxx.taxeasy.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * t_sp 实体类
 * 商品信息表
 * 由GenEntityMysql类自动生成
 * Mon Oct 10 08:20:49 CST 2016
 *
 * @ZhangBing
 */
@Entity
@Table(name = "t_sp")
public class Sp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * 商品代码
     */
    @Column(name = "spdm")
    protected String spdm;

    /**
     * 商品名称
     */
    @Column(name = "spmc")
    protected String spmc;

    /**
     * 税目代码
     */
    @Column(name = "smid")
    protected Integer smid;

    /**
     * 商品分类代码
     */
    @Column(name = "spfldm")
    protected String spfldm;

    /**
     * 商品规格型号
     */
    @Column(name = "spggxh")
    protected String spggxh;

    /**
     * 商品单位
     */
    @Column(name = "spdw")
    protected String spdw;

    /**
     * 商品单价
     */
    @Column(name = "spdj")
    protected Double spdj;

    /**
     * 公司代码
     */
    @Column(name = "gsdm")
    protected String gsdm;

    /**
     * 有效标识(1、有效；0、禁用）
     */
    @Column(name = "yxbz")
    protected String yxbz;

    @Column(name = "lrsj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date lrsj;

    /**
     * 录入人员
     */
    @Column(name = "lrry")
    protected Integer lrry;

    @Column(name = "xgsj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date xgsj;

    /**
     * 修改人员
     */
    @Column(name = "xgry")
    protected Integer xgry;


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

    public Integer getSmid() {
		return smid;
	}

	public void setSmid(Integer smid) {
		this.smid = smid;
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

}

