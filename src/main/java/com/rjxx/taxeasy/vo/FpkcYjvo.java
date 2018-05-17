package com.rjxx.taxeasy.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class FpkcYjvo implements Serializable{
	protected Integer id;
	protected Integer xfid;
	protected Integer skpid;
	protected String gsdm;
	protected String kpdmc;
	protected String kplx;
	protected String fpzldm;
	protected String fpzlmc;
	protected String xfmc;
	protected String xfsh;
	protected Integer fpkcl;
	protected Integer yjyz;
	/**
	 * 修改时间
	 */
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getGsdm() {
		return gsdm;
	}
	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}
	public String getKpdmc() {
		return kpdmc;
	}
	public void setKpdmc(String kpdmc) {
		this.kpdmc = kpdmc;
	}
	public String getKplx() {
		return kplx;
	}
	public void setKplx(String kplx) {
		this.kplx = kplx;
	}
	public String getFpzldm() {
		return fpzldm;
	}
	public void setFpzldm(String fpzldm) {
		this.fpzldm = fpzldm;
	}
	public String getFpzlmc() {
		return fpzlmc;
	}
	public void setFpzlmc(String fpzlmc) {
		this.fpzlmc = fpzlmc;
	}
	public String getXfmc() {
		return xfmc;
	}
	public void setXfmc(String xfmc) {
		this.xfmc = xfmc;
	}
	public String getXfsh() {
		return xfsh;
	}
	public void setXfsh(String xfsh) {
		this.xfsh = xfsh;
	}
	public Integer getFpkcl() {
		return fpkcl;
	}
	public void setFpkcl(Integer fpkcl) {
		this.fpkcl = fpkcl;
	}
	public Integer getYjyz() {
		return yjyz;
	}
	public void setYjyz(Integer yjyz) {
		this.yjyz = yjyz;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}
}
