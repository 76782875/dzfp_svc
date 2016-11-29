package com.rjxx.taxeasy.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;

public class Fptqvo implements Serializable{
	
	protected String ddh;
	protected String gfmc;
	protected Double jshj;
	protected String tqsb;
	protected String jlly;
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date tqsj;
	public String getDdh() {
		return ddh;
	}
	public void setDdh(String ddh) {
		this.ddh = ddh;
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
	public String getTqsb() {
		return tqsb;
	}
	public void setTqsb(String tqsb) {
		this.tqsb = tqsb;
	}
	public String getJlly() {
		return jlly;
	}
	public void setJlly(String jlly) {
		this.jlly = jlly;
	}
	public Date getTqsj() {
		return tqsj;
	}
	public void setTqsj(Date tqsj) {
		this.tqsj = tqsj;
	}
	

}
