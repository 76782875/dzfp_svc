package com.rjxx.taxeasy.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;

public class SkpVo {
	/**
	 * 税控盘号
	 */
	protected String skph;

	/**
	 * 税控盘密码
	 */
	protected String skpmm;

	/**
	 * 证书密码
	 */
	protected String zsmm;

	protected String zcm;

	/**
	 * 发票库存预警阈值
	 */
	protected Integer fpyz;
	
	protected String ppdm;
	
	protected String ppmc;

	/**
	 * 备注
	 */
	protected String bz;

	protected Date lrsj;

	/**
	 * 录入人员
	 */
	protected Integer lrry;

	protected Date xgsj;

	/**
	 * 修改人员
	 */
	protected Integer xgry;

	/**
	 * 销方id
	 */
	protected Integer xfid;

	/**
	 * 销方id
	 */
	protected Integer pid;

	/**
	 * 公司代码
	 */
	protected String gsdm;

	/**
	 * 开票点名称
	 */
	protected String kpdmc;

	/**
	 * 有效标志：0，无效；1，有效
	 */
	protected String yxbz;

	/**
	 * 电子发票最大开票限额
	 */
	protected Double dpmax;

	/**
	 * 地址发票开票阈值
	 */
	protected Double fpfz;

	/**
	 * 普票开票最大限额
	 */
	protected Double ppmax;

	/**
	 * 普票开票阈值
	 */
	protected Double ppfz;

	protected Integer id;

	/**
	 * 专票最大开票限额
	 */
	protected Double zpmax;

	/**
	 * 专票阈值
	 */
	protected Double zpfz;

	/**
	 * 开票点ip地址
	 */
	protected String kpdip;
	protected String kpddm;
	protected String xfmc;
	protected String sbcs;

	protected String lxdz;

	protected String lxdh;

	protected String khyh;

	protected String yhzh;

	protected String skr;

	protected String fhr;

	protected String kpr;

	public String getSkph() {
		return skph;
	}

	public void setSkph(String skph) {
		this.skph = skph;
	}

	public String getSkpmm() {
		return skpmm;
	}

	public void setSkpmm(String skpmm) {
		this.skpmm = skpmm;
	}

	public String getZsmm() {
		return zsmm;
	}

	public void setZsmm(String zsmm) {
		this.zsmm = zsmm;
	}

	public String getZcm() {
		return zcm;
	}

	public void setZcm(String zcm) {
		this.zcm = zcm;
	}

	public Integer getFpyz() {
		return fpyz;
	}

	public void setFpyz(Integer fpyz) {
		this.fpyz = fpyz;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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

	public Integer getXfid() {
		return xfid;
	}

	public void setXfid(Integer xfid) {
		this.xfid = xfid;
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

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public Double getDpmax() {
		return dpmax;
	}

	public void setDpmax(Double dpmax) {
		this.dpmax = dpmax;
	}

	public Double getFpfz() {
		return fpfz;
	}

	public void setFpfz(Double fpfz) {
		this.fpfz = fpfz;
	}

	public Double getPpmax() {
		return ppmax;
	}

	public void setPpmax(Double ppmax) {
		this.ppmax = ppmax;
	}

	public Double getPpfz() {
		return ppfz;
	}

	public void setPpfz(Double ppfz) {
		this.ppfz = ppfz;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getZpmax() {
		return zpmax;
	}

	public void setZpmax(Double zpmax) {
		this.zpmax = zpmax;
	}

	public Double getZpfz() {
		return zpfz;
	}

	public void setZpfz(Double zpfz) {
		this.zpfz = zpfz;
	}

	public String getKpdip() {
		return kpdip;
	}

	public void setKpdip(String kpdip) {
		this.kpdip = kpdip;
	}

	public String getXfmc() {
		return xfmc;
	}

	public void setXfmc(String xfmc) {
		this.xfmc = xfmc;
	}

	public String getKpddm() {
		return kpddm;
	}

	public void setKpddm(String kpddm) {
		this.kpddm = kpddm;
	}

	public String getPpdm() {
		return ppdm;
	}

	public void setPpdm(String ppdm) {
		this.ppdm = ppdm;
	}

	public String getPpmc() {
		return ppmc;
	}

	public void setPpmc(String ppmc) {
		this.ppmc = ppmc;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getSbcs() {
		return sbcs;
	}

	public void setSbcs(String sbcs) {
		this.sbcs = sbcs;
	}

	public String getLxdz() {
		return lxdz;
	}

	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getKhyh() {
		return khyh;
	}

	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}

	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
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
}
