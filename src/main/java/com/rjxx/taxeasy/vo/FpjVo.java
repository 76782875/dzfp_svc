package com.rjxx.taxeasy.vo;

import java.io.Serializable;
import java.util.Date;

public class FpjVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Integer djh;
	
	protected Date jylssj;
	
	protected String gfmc;
	
	protected Double jshj;
	
	protected String ztbz;
	
	protected String kprq;
	
	protected String ckbtgyy;
	
	protected String gsdm;
	protected String Logo;
	protected String jshj1;
	public Integer getDjh() {
		return djh;
	}

	public void setDjh(Integer djh) {
		this.djh = djh;
	}

	public String getGfmc() {
		return gfmc;
	}

	public void setGfmc(String gfmc) {
		this.gfmc = gfmc;
	}

	public Double getJshj() {
		return jshj;
	}

	public void setJshj(Double jshj) {
		this.jshj = jshj;
	}

	public String getZtbz() {
		return ztbz;
	}

	public void setZtbz(String ztbz) {
		this.ztbz = ztbz;
	}

	public Date getJylssj() {
		return jylssj;
	}

	public void setJylssj(Date jylssj) {
		this.jylssj = jylssj;
	}

	public String getKprq() {
		if (kprq != null && kprq.length() > 10) {
			kprq = kprq.substring(0, 11);
		}
		return kprq;
	}

	public void setKprq(String kprq) {
		this.kprq = kprq;
	}

	public String getCkbtgyy() {
		return ckbtgyy;
	}

	public void setCkbtgyy(String ckbtgyy) {
		this.ckbtgyy = ckbtgyy;
	}

	public String getGsdm() {
		return gsdm;
	}

	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}

	public String getLogo() {
		return Logo;
	}

	public void setLogo(String logo) {
		Logo = logo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getJshj1() {
		return jshj1;
	}

	public void setJshj1(String jshj1) {
		this.jshj1 = jshj1;
	}
	
}
