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
 * t_csb 实体类
 * 由GenEntityMysql类自动生成
 * Wed Nov 02 11:55:38 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_csb")
public class Csb  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 参数名
 */ 
@Column(name="csm")
	protected String csm;

/**
 * 中文名称
 */ 
@Column(name="csmc")
	protected String csmc;

/**
 * 参数级别
 */ 
@Column(name="csjb")
	protected String csjb;

/**
 * 系统参数或者用户参数
 */ 
@Column(name="cslx")
	protected String cslx;

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

@Column(name="yxbz")
	protected String yxbz;

/**
 * 参数值类型
 */ 
@Column(name="cszlx")
	protected String cszlx;

@Column(name="mrz")
protected String mrz;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getCsm(){
		return csm;
	}

	public void setCsm(String csm){
		this.csm=csm;
	}

	public String getCsmc(){
		return csmc;
	}

	public void setCsmc(String csmc){
		this.csmc=csmc;
	}

	public String getCsjb(){
		return csjb;
	}

	public void setCsjb(String csjb){
		this.csjb=csjb;
	}

	public String getCslx(){
		return cslx;
	}

	public void setCslx(String cslx){
		this.cslx=cslx;
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

	public String getCszlx(){
		return cszlx;
	}

	public void setCszlx(String cszlx){
		this.cszlx=cszlx;
	}

	public String getMrz() {
		return mrz;
	}

	public void setMrz(String mrz) {
		this.mrz = mrz;
	}

}

