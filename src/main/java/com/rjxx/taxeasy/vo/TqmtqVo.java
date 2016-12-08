package com.rjxx.taxeasy.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDateFormat;

public class TqmtqVo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	/**
	 * 订单号 也是提取码
	 */
	@Column(name = "ddh")
	protected String ddh;

	/**
	 * 录入时间
	 */
	@Column(name = "lrsj")
	@JsonSerialize(using = JsonDateFormat.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date lrsj;

	/**
	 * 金额
	 */
	@Column(name = "zje")
	protected Double zje;

	/**
	 * 购方名称
	 */
	@Column(name = "gfmc")
	protected String gfmc;

	/**
	 * 纳税人识别号
	 */
	@Column(name = "nsrsbh")
	protected String nsrsbh;

	/**
	 * 地址
	 */
	@Column(name = "dz")
	protected String dz;

	/**
	 * 电话
	 */
	@Column(name = "dh")
	protected String dh;

	/**
	 * 开户行
	 */
	@Column(name = "khh")
	protected String khh;

	/**
	 * 开户行账号
	 */
	@Column(name = "khhzh")
	protected String khhzh;

	/**
	 * 状态
	 */
	@Column(name = "fpzt")
	protected String fpzt;

	/**
	 * 有效标志
	 */
	@Column(name = "yxbz")
	protected String yxbz;

	/**
	 * 公司代码
	 */
	@Column(name = "gsdm")
	protected String gsdm;

	protected String kpddm;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDdh() {
		return ddh;
	}

	public void setDdh(String ddh) {
		this.ddh = ddh;
	}

	public Date getLrsj() {
		return lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public Double getZje() {
		return zje;
	}

	public void setZje(Double zje) {
		this.zje = zje;
	}

	public String getGfmc() {
		return gfmc;
	}

	public void setGfmc(String gfmc) {
		this.gfmc = gfmc;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getKhh() {
		return khh;
	}

	public void setKhh(String khh) {
		this.khh = khh;
	}

	public String getKhhzh() {
		return khhzh;
	}

	public void setKhhzh(String khhzh) {
		this.khhzh = khhzh;
	}

	public String getFpzt() {
		return fpzt;
	}

	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public String getGsdm() {
		return gsdm;
	}

	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}

	public String getKpddm() {
		return kpddm;
	}

	public void setKpddm(String kpddm) {
		this.kpddm = kpddm;
	}

}
