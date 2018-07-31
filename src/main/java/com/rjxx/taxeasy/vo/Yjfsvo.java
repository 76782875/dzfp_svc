package com.rjxx.taxeasy.vo;


import java.io.Serializable;

public class Yjfsvo implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String jshj;
	private String jylssj;
	private String lrsj;
    private String ddh;
    private String gfmc;
    private String gfemail;
    private String serialorder;
    private String sfkp;
    private String gfsjh;
	public String getGfsjh() {
		return gfsjh;
	}
	public void setGfsjh(String gfsjh) {
		this.gfsjh = gfsjh;
	}
	public String getJshj() {
		return jshj;
	}
	public void setJshj(String jshj) {
		this.jshj = jshj;
	}
	public String getJylssj() {
		return jylssj;
	}
	public void setJylssj(String jylssj) {
		this.jylssj = jylssj;
	}
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
	public String getGfemail() {
		return gfemail;
	}
	public void setGfemail(String gfemail) {
		this.gfemail = gfemail;
	}
	public String getSerialorder() {
		return serialorder;
	}
	public void setSerialorder(String serialorder) {
		this.serialorder = serialorder;
	}
	public String getSfkp() {
		return sfkp;
	}
	public void setSfkp(String sfkp) {
		this.sfkp = sfkp;
	}

	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
