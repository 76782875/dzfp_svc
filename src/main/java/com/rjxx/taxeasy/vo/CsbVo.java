package com.rjxx.taxeasy.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;

public class CsbVo {

	private Integer id;
	private Integer csid;
	private String gsdm;
	private Integer xfid;
	private Integer kpdid;
	private Integer lrry;
	private Integer xgry;
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;
	private String csz;
	private String csm;
	private String csmc;
	private String xfsh;
	private String xfmc;
	private String kpdmc;
	private String skph;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
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
	public Integer getKpdid() {
		return kpdid;
	}
	public void setKpdid(Integer kpdid) {
		this.kpdid = kpdid;
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
	public String getCsz() {
		return csz;
	}
	public void setCsz(String csz) {
		this.csz = csz;
	}
	public String getCsm() {
		return csm;
	}
	public void setCsm(String csm) {
		this.csm = csm;
	}
	public String getCsmc() {
		return csmc;
	}
	public void setCsmc(String csmc) {
		this.csmc = csmc;
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
	public String getKpdmc() {
		return kpdmc;
	}
	public void setKpdmc(String kpdmc) {
		this.kpdmc = kpdmc;
	}
	public String getSkph() {
		return skph;
	}
	public void setSkph(String skph) {
		this.skph = skph;
	}
	
}
