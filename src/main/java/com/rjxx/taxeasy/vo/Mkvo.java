package com.rjxx.taxeasy.vo;

import java.io.Serializable;

public class Mkvo implements Serializable {
	
    protected Integer id;
    protected Integer yhid;
    protected Integer pzid;
    protected String mkmc;
    protected String mkbl;
    protected Integer sort;
    protected String yxbz;
    protected String url;
    protected String urlmc;
    protected String defaultflag;
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
	public Integer getPzid() {
		return pzid;
	}
	public void setPzid(Integer pzid) {
		this.pzid = pzid;
	}
	public String getMkmc() {
		return mkmc;
	}
	public void setMkmc(String mkmc) {
		this.mkmc = mkmc;
	}
	public String getMkbl() {
		return mkbl;
	}
	public void setMkbl(String mkbl) {
		this.mkbl = mkbl;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getYxbz() {
		return yxbz;
	}
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlmc() {
		return urlmc;
	}
	public void setUrlmc(String urlmc) {
		this.urlmc = urlmc;
	}
	public String getDefaultflag() {
		return defaultflag;
	}
	public void setDefaultflag(String defaultflag) {
		this.defaultflag = defaultflag;
	}
}
