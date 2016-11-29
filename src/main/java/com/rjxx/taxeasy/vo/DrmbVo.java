package com.rjxx.taxeasy.vo;

public class DrmbVo {

/**
 * 主键id
 */ 
	protected Integer id;

/**
 * 模板名称
 */ 
	protected String mbmc;

/**
 * 销方税号
 */ 
	protected String xfsh;

/**
 * 公司代码
 */ 
	protected String gsdm;

/**
 * 共享标志
 */ 
	protected String gxbz;

/**
 * 销方名称
 */ 
	protected String xfmc;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getMbmc() {
	return mbmc;
}

public void setMbmc(String mbmc) {
	this.mbmc = mbmc;
}

public String getXfsh() {
	return xfsh;
}

public void setXfsh(String xfsh) {
	this.xfsh = xfsh;
}

public String getGsdm() {
	return gsdm;
}

public void setGsdm(String gsdm) {
	this.gsdm = gsdm;
}

public String getGxbz() {
	return gxbz;
}

public void setGxbz(String gxbz) {
	this.gxbz = gxbz;
}

public String getXfmc() {
	return xfmc;
}

public void setXfmc(String xfmc) {
	this.xfmc = xfmc;
}
}
