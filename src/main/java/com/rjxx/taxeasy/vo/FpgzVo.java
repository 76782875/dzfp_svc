package com.rjxx.taxeasy.vo;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;

public class FpgzVo {

	protected Integer id;

/**
 * 专票限额
 */ 
@Column(name="zpxe")
	protected Double zpxe;

/**
 * 普票限额
 */ 
@Column(name="ppxe")
	protected Double ppxe;

/**
 * 电子票限额
 */ 
@Column(name="dzpxe")
	protected Double dzpxe;

/**
 * 专票行数
 */ 
@Column(name="zphs")
	protected Integer zphs;

/**
 * 普票行数
 */ 
@Column(name="pphs")
	protected Integer pphs;

/**
 * 电子票行数
 */ 
@Column(name="dzphs")
	protected Integer dzphs;

/**
 * 规则名称
 */ 
@Column(name="ggmc")
	protected String ggmc;

/**
 * 使用的xfid
 */ 
@Column(name="xfids")
	protected String xfids;

/**
 * 有效标志
 */ 
@Column(name="yxbz")
	protected String yxbz;


@Column(name="lrry")
protected Integer lrry;

@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
protected Date lrsj;

@Column(name="xgry")
protected Integer xgry;

public Integer getLrry() {
	return lrry;
}

public void setLrry(Integer lrry) {
	this.lrry = lrry;
}

public Date getLrsj() {
	return lrsj;
}

public void setLrsj(Date lrsj) {
	this.lrsj = lrsj;
}

public Integer getXgry() {
	return xgry;
}

public void setXgry(Integer xgry) {
	this.xgry = xgry;
}

public Date getXgsj() {
	return xgsj;
}

public void setXgsj(Date xgsj) {
	this.xgsj = xgsj;
}

@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
protected Date xgsj;

protected String yhmc;
@Column(name="gsdm")
protected String gsdm;



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

public Double getZpxe() {
	return zpxe;
}

public void setZpxe(Double zpxe) {
	this.zpxe = zpxe;
}

public Double getPpxe() {
	return ppxe;
}

public void setPpxe(Double ppxe) {
	this.ppxe = ppxe;
}

public Double getDzpxe() {
	return dzpxe;
}

public void setDzpxe(Double dzpxe) {
	this.dzpxe = dzpxe;
}

public Integer getZphs() {
	return zphs;
}

public void setZphs(Integer zphs) {
	this.zphs = zphs;
}

public Integer getPphs() {
	return pphs;
}

public void setPphs(Integer pphs) {
	this.pphs = pphs;
}

public Integer getDzphs() {
	return dzphs;
}

public void setDzphs(Integer dzphs) {
	this.dzphs = dzphs;
}

public String getGgmc() {
	return ggmc;
}

public void setGgmc(String ggmc) {
	this.ggmc = ggmc;
}

public String getXfids() {
	return xfids;
}

public void setXfids(String xfids) {
	this.xfids = xfids;
}

public String getYxbz() {
	return yxbz;
}

public void setYxbz(String yxbz) {
	this.yxbz = yxbz;
}

public String getYhmc() {
	return yhmc;
}

public void setYhmc(String yhmc) {
	this.yhmc = yhmc;
}


}
