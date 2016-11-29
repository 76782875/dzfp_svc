package com.rjxx.taxeasy.domains;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * t_mkpz 实体类
 * 由GenEntityMysql类自动生成
 * Thu Oct 13 10:51:41 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_mkpz")
public class Mkpz  implements Serializable {

/**
 * 主键
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 区块内容对应的rul
 */ 
@Column(name="mkmc")
	protected String mkmc;

/**
 * 区块内容对应的rul
 */ 
@Column(name="url")
	protected String url;

/**
 * 区块内容名称
 */ 
@Column(name="urlmc")
	protected String urlmc;

/**
 * 默认标识，1：默认，0：非默认
 */ 
@Column(name="defaultflag")
	protected String defaultflag;

/**
 * 区块比例
 */ 
@Column(name="mkbl")
	protected String mkbl;

@Column(name="lrry")
	protected Integer lrry;

@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

@Column(name="xgry")
	protected Integer xgry;

@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

/**
 * 有效标志
 */ 
@Column(name="yxbz")
	protected String yxbz;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getMkmc() {
		return mkmc;
	}

	public void setMkmc(String mkmc) {
		this.mkmc = mkmc;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url=url;
	}

	public String getUrlmc(){
		return urlmc;
	}

	public void setUrlmc(String urlmc){
		this.urlmc=urlmc;
	}

	public String getDefaultflag(){
		return defaultflag;
	}

	public void setDefaultflag(String defaultflag){
		this.defaultflag=defaultflag;
	}

	public String getMkbl(){
		return mkbl;
	}

	public void setMkbl(String mkbl){
		this.mkbl=mkbl;
	}

	public Integer getLrry(){
		return lrry;
	}

	public void setLrry(Integer lrry){
		this.lrry=lrry;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public Integer getXgry(){
		return xgry;
	}

	public void setXgry(Integer xgry){
		this.xgry=xgry;
	}

	public Date getXgsj(){
		return xgsj;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
	}

}

