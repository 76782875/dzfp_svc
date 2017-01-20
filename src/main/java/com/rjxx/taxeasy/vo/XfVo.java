package com.rjxx.taxeasy.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;

public class XfVo {

/**
 * 销方ID
 */ 
	protected Integer id;

	protected String jgbm;

/**
 * 上级机构编码
 */ 
	protected String sjjgbm;

/**
 * 销方税号
 */ 
	protected String xfsh;

/**
 * 销方名称
 */ 
	protected String xfmc;

	protected String xfyh;

/**
 * 销方银行账号
 */ 
	protected String xfyhzh;

/**
 * 销方联系人
 */ 
	protected String xflxr;

/**
 * 销方地址
 */ 
	protected String xfdz;

/**
 * 销方电话
 */ 
	protected String xfdh;

/**
 * 销方邮编
 */ 
	protected String xfyb;

/**
 * 授权用户数
 */ 
	protected Integer sqyhs;

/**
 * 收款人
 */ 
	protected String skr;

/**
 * 复核人
 */ 
	protected String fhr;

/**
 * 开票人
 */ 
	protected String kpr;

/**
 * 作废人
 */ 
	protected String zfr;

/**
 * 状态标识 0：待复核；1：已生效；2：已失效
 */ 
	protected String yxbz;

@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 录入人员
 */ 
	protected Integer lrry;

@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

/**
 * 修改人员
 */ 
	protected Integer xgry;

/**
 * 开票最大金额
 */ 
	protected Double kpzdje;

/**
 * 公司代码
 */ 
	protected String gsdm;

/**
 * 电子票最大金额
 */ 
	protected Double dzpzdje;

/**
 * 电子票分票金额
 */ 
	protected Double dzpfpje;

/**
 * 专票最大金额
 */ 
	protected Double zpfpje;

/**
 * 专票分票金额
 */ 
	protected Double zpzdje;

/**
 * 普票分票金额
 */ 
	protected Double ppfpje;

/**
 * 普票最大金额
 */ 
	protected Double ppzdje;
	
	protected String sjxfmc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJgbm() {
		return jgbm;
	}

	public void setJgbm(String jgbm) {
		this.jgbm = jgbm;
	}

	public String getSjjgbm() {
		return sjjgbm;
	}

	public void setSjjgbm(String sjjgbm) {
		this.sjjgbm = sjjgbm;
	}

	public String getXfsh() {
		return xfsh;
	}

	public void setXfsh(String xfsh) {
		this.xfsh = xfsh;
	}

	public String getXfmc() {
		return xfmc;
	}

	public void setXfmc(String xfmc) {
		this.xfmc = xfmc;
	}

	public String getXfyh() {
		return xfyh;
	}

	public void setXfyh(String xfyh) {
		this.xfyh = xfyh;
	}

	public String getXfyhzh() {
		return xfyhzh;
	}

	public void setXfyhzh(String xfyhzh) {
		this.xfyhzh = xfyhzh;
	}

	public String getXflxr() {
		return xflxr;
	}

	public void setXflxr(String xflxr) {
		this.xflxr = xflxr;
	}

	public String getXfdz() {
		return xfdz;
	}

	public void setXfdz(String xfdz) {
		this.xfdz = xfdz;
	}

	public String getXfdh() {
		return xfdh;
	}

	public void setXfdh(String xfdh) {
		this.xfdh = xfdh;
	}

	public String getXfyb() {
		return xfyb;
	}

	public void setXfyb(String xfyb) {
		this.xfyb = xfyb;
	}

	public Integer getSqyhs() {
		return sqyhs;
	}

	public void setSqyhs(Integer sqyhs) {
		this.sqyhs = sqyhs;
	}

	public String getSkr() {
		return skr;
	}

	public void setSkr(String skr) {
		this.skr = skr;
	}

	public String getFhr() {
		return fhr;
	}

	public void setFhr(String fhr) {
		this.fhr = fhr;
	}

	public String getKpr() {
		return kpr;
	}

	public void setKpr(String kpr) {
		this.kpr = kpr;
	}

	public String getZfr() {
		return zfr;
	}

	public void setZfr(String zfr) {
		this.zfr = zfr;
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

	public Double getKpzdje() {
		return kpzdje;
	}

	public void setKpzdje(Double kpzdje) {
		this.kpzdje = kpzdje;
	}

	public String getGsdm() {
		return gsdm;
	}

	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}

	public Double getDzpzdje() {
		return dzpzdje;
	}

	public void setDzpzdje(Double dzpzdje) {
		this.dzpzdje = dzpzdje;
	}

	public Double getDzpfpje() {
		return dzpfpje;
	}

	public void setDzpfpje(Double dzpfpje) {
		this.dzpfpje = dzpfpje;
	}

	public Double getZpfpje() {
		return zpfpje;
	}

	public void setZpfpje(Double zpfpje) {
		this.zpfpje = zpfpje;
	}

	public Double getZpzdje() {
		return zpzdje;
	}

	public void setZpzdje(Double zpzdje) {
		this.zpzdje = zpzdje;
	}

	public Double getPpfpje() {
		return ppfpje;
	}

	public void setPpfpje(Double ppfpje) {
		this.ppfpje = ppfpje;
	}

	public Double getPpzdje() {
		return ppzdje;
	}

	public void setPpzdje(Double ppzdje) {
		this.ppzdje = ppzdje;
	}

	public String getSjxfmc() {
		return sjxfmc;
	}

	public void setSjxfmc(String sjxfmc) {
		this.sjxfmc = sjxfmc;
	}
	
	

}
