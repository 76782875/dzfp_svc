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
 * t_gfxx 实体类
 * 购方信息表
 * 由GenEntityMysql类自动生成
 * Thu Feb 09 14:46:20 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_gfxx")
public class Gfxx  implements Serializable {

/**
 * 购方id
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 购方税号
 */ 
@Column(name="gfsh")
	protected String gfsh;

/**
 * 购方名称
 */ 
@Column(name="gfmc")
	protected String gfmc;

/**
 * 名称首字母缩写
 */ 
@Column(name="mcszmsx")
	protected String mcszmsx;

/**
 * 购方地址
 */ 
@Column(name="gfdz")
	protected String gfdz;

/**
 * 购方电话
 */ 
@Column(name="gfdh")
	protected String gfdh;

/**
 * 购方银行
 */ 
@Column(name="gfyh")
	protected String gfyh;

@Column(name="gfyhzh")
	protected String gfyhzh;

/**
 * 有效标志，1有效，0无效
 */ 
@Column(name="yxbz")
	protected String yxbz;

/**
 * 录入人员
 */ 
@Column(name="lrry")
	protected Integer lrry;

/**
 * 录入时间
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 修改人员
 */ 
@Column(name="xgry")
	protected Integer xgry;

/**
 * 修改时间
 */ 
@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getGfsh(){
		return gfsh;
	}

	public void setGfsh(String gfsh){
		this.gfsh=gfsh;
	}

	public String getGfmc(){
		return gfmc;
	}

	public void setGfmc(String gfmc){
		this.gfmc=gfmc;
	}

	public String getMcszmsx(){
		return mcszmsx;
	}

	public void setMcszmsx(String mcszmsx){
		this.mcszmsx=mcszmsx;
	}

	public String getGfdz(){
		return gfdz;
	}

	public void setGfdz(String gfdz){
		this.gfdz=gfdz;
	}

	public String getGfdh(){
		return gfdh;
	}

	public void setGfdh(String gfdh){
		this.gfdh=gfdh;
	}

	public String getGfyh(){
		return gfyh;
	}

	public void setGfyh(String gfyh){
		this.gfyh=gfyh;
	}

	public String getGfyhzh(){
		return gfyhzh;
	}

	public void setGfyhzh(String gfyhzh){
		this.gfyhzh=gfyhzh;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
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

}
