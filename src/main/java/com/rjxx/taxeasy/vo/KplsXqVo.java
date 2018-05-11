package com.rjxx.taxeasy.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;

public class KplsXqVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String pdfurl;
	private String fpdm;
	private String fphm;
	private String xfmc;
	private String jshj;
	private String ddh;
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date kprq;
	
	
	public String getPdfurl() {
		return pdfurl;
	}
	public void setPdfurl(String pdfurl) {
		this.pdfurl = pdfurl;
	}
	public String getFpdm() {
		return fpdm;
	}
	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}
	public String getFphm() {
		return fphm;
	}
	public void setFphm(String fphm) {
		this.fphm = fphm;
	}
	public String getXfmc() {
		return xfmc;
	}
	public void setXfmc(String xfmc) {
		this.xfmc = xfmc;
	}
	public String getJshj() {
		return jshj;
	}
	public void setJshj(String jshj) {
		this.jshj = jshj;
	}
	public String getDdh() {
		return ddh;
	}
	public void setDdh(String ddh) {
		this.ddh = ddh;
	}
	public Date getKprq() {
		return kprq;
	}
	public void setKprq(Date kprq) {
		this.kprq = kprq;
	}
	

}
