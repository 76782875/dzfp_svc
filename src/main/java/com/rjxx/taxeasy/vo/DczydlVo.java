package com.rjxx.taxeasy.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;

public class DczydlVo {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Column(name = "yhid")
	protected Integer yhid;

	/**
	 * 对应的功能
	 */
	@Column(name = "dygn")
	protected String dygn;

	/**
	 * 字段代码
	 */
	@Column(name = "zddm")
	protected String zddm;

	/**
	 * 1-有效，0-无效
	 */
	@Column(name = "yxbz")
	protected String yxbz;

	/**
	 * 录入时间
	 */
	@Column(name = "lrsj")
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

	/**
	 * 修改时间
	 */
	@Column(name = "xgsj")
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

	/**
	 * 录入人员
	 */
	@Column(name = "lrry")
	protected Integer lrry;

	/**
	 * 修改人员
	 */
	@Column(name = "xgry")
	protected Integer xgry;

	protected String zdzwm;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYhid() {
		return yhid;
	}

	public void setYhid(Integer yhid) {
		this.yhid = yhid;
	}

	public String getDygn() {
		return dygn;
	}

	public void setDygn(String dygn) {
		this.dygn = dygn;
	}

	public String getZddm() {
		return zddm;
	}

	public void setZddm(String zddm) {
		this.zddm = zddm;
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

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public Integer getLrry() {
		return lrry;
	}

	public void setLrry(Integer lrry) {
		this.lrry = lrry;
	}

	public Integer getXgry() {
		return xgry;
	}

	public void setXgry(Integer xgry) {
		this.xgry = xgry;
	}

	public String getZdzwm() {
		return zdzwm;
	}

	public void setZdzwm(String zdzwm) {
		this.zdzwm = zdzwm;
	}

}
