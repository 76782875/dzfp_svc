package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * t_kppmxx 实体类
 * 开票票面信息
 * 由GenEntityMysql类自动生成
 * Wed Nov 30 10:13:05 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_kppmxx")
public class Kppmxx  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 销方税号
 */ 
@Column(name="xfsh")
	protected String xfsh;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

@Column(name="skr")
	protected String skr;

@Column(name="fhr")
	protected String fhr;

@Column(name="kpr")
	protected String kpr;

/**
 * 销方银行
 */ 
@Column(name="xfyh")
	protected String xfyh;

/**
 * 销方银行账号
 */ 
@Column(name="xfyhzh")
	protected String xfyhzh;

/**
 * 销方地址
 */ 
@Column(name="xfdz")
	protected String xfdz;

/**
 * 销方电话
 */ 
@Column(name="xfdh")
	protected String xfdh;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getXfsh(){
		return xfsh;
	}

	public void setXfsh(String xfsh){
		this.xfsh=xfsh;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public String getSkr(){
		return skr;
	}

	public void setSkr(String skr){
		this.skr=skr;
	}

	public String getFhr(){
		return fhr;
	}

	public void setFhr(String fhr){
		this.fhr=fhr;
	}

	public String getKpr(){
		return kpr;
	}

	public void setKpr(String kpr){
		this.kpr=kpr;
	}

	public String getXfyh(){
		return xfyh;
	}

	public void setXfyh(String xfyh){
		this.xfyh=xfyh;
	}

	public String getXfyhzh(){
		return xfyhzh;
	}

	public void setXfyhzh(String xfyhzh){
		this.xfyhzh=xfyhzh;
	}

	public String getXfdz(){
		return xfdz;
	}

	public void setXfdz(String xfdz){
		this.xfdz=xfdz;
	}

	public String getXfdh(){
		return xfdh;
	}

	public void setXfdh(String xfdh){
		this.xfdh=xfdh;
	}

}

