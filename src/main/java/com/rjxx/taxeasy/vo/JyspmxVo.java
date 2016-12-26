package com.rjxx.taxeasy.vo;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;

public class JyspmxVo {
	 /**
     * 单据号
     */
    protected Integer djh;

    /**
     * 商品明细序号
     */
    protected Integer spmxxh;

    /**
     * 0、正常行；1、折扣行；2、被折扣行
     */
    protected String fphxz;

    /**
     * 商品代码
     */
    protected String spdm;

    /**
     * 商品名称
     */
    protected String spmc;

    /**
     * 商品规格型号
     */
    protected String spggxh;

    /**
     * 商品单位
     */
    protected String spdw;

    /**
     * 商品数量
     */
    protected Double sps;

    /**
     * 商品单价
     */
    protected Double spdj;

    /**
     * 商品金额
     */
    protected Double spje;

    /**
     * 商品税率
     */
    protected Double spsl;

    /**
     * 商品税额
     */
    protected Double spse;

    /**
     * 价税合计
     */
    protected Double jshj;

    /**
     * 已开票价税合计 已开票价税合计
     */
    protected Double ykphj;

    /**
     * 汇总开票序号
     */
    protected Integer hzkpxh;

    /**
     * 录入时间
     */
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date lrsj;

    /**
     * 录入人员
     */
    protected Integer lrry;

    /**
     * 修改时间
     */
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date xgsj;

    /**
     * 修改人员
     */
    protected Integer xgry;

    /**
     * 公司代码
     */
    protected String gsdm;
 
    /**
     * 销方id
     */
    protected Integer xfid;
    
    /**
     * 税控盘id
     */
    protected Integer skpid;
    
    /**
     * 税控盘id
     */
    protected String ddh;

	public Integer getDjh() {
		return djh;
	}

	public void setDjh(Integer djh) {
		this.djh = djh;
	}

	public Integer getSpmxxh() {
		return spmxxh;
	}

	public void setSpmxxh(Integer spmxxh) {
		this.spmxxh = spmxxh;
	}

	public String getFphxz() {
		return fphxz;
	}

	public void setFphxz(String fphxz) {
		this.fphxz = fphxz;
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

	public Double getSps() {
		return sps;
	}

	public void setSps(Double sps) {
		this.sps = sps;
	}

	public Double getSpdj() {
		return spdj;
	}

	public void setSpdj(Double spdj) {
		this.spdj = spdj;
	}

	public Double getSpje() {
		return spje;
	}

	public void setSpje(Double spje) {
		this.spje = spje;
	}

	public Double getSpsl() {
		return spsl;
	}

	public void setSpsl(Double spsl) {
		this.spsl = spsl;
	}

	public Double getSpse() {
		return spse;
	}

	public void setSpse(Double spse) {
		this.spse = spse;
	}

	public Double getJshj() {
		return jshj;
	}

	public void setJshj(Double jshj) {
		this.jshj = jshj;
	}

	public Double getYkphj() {
		return ykphj;
	}

	public void setYkphj(Double ykphj) {
		this.ykphj = ykphj;
	}

	public Integer getHzkpxh() {
		return hzkpxh;
	}

	public void setHzkpxh(Integer hzkpxh) {
		this.hzkpxh = hzkpxh;
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

	public String getGsdm() {
		return gsdm;
	}

	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}

	public Integer getXfid() {
		return xfid;
	}

	public void setXfid(Integer xfid) {
		this.xfid = xfid;
	}

	public Integer getSkpid() {
		return skpid;
	}

	public void setSkpid(Integer skpid) {
		this.skpid = skpid;
	}

	public String getDdh() {
		return ddh;
	}

	public void setDdh(String ddh) {
		this.ddh = ddh;
	}
    
    
}
