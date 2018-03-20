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
 * t_jkpzb 实体类
 * 由GenEntityMysql类自动生成
 * Tue Mar 20 16:05:55 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_jkpzb")
public class Jkpzb  implements Serializable {

/**
 * 主键id
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 配置参数名
 */ 
@Column(name="pzcsm")
	protected String pzcsm;

/**
 * 配置参数中文名称
 */ 
@Column(name="pzcsmc")
	protected String pzcsmc;

/**
 * 参数处理默认方法
 */ 
@Column(name="mrff")
	protected String mrff;

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

/**
 * 有效标志（1有效，0无效）
 */ 
@Column(name="yxbz")
	protected String yxbz;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getPzcsm(){
		return pzcsm;
	}

	public void setPzcsm(String pzcsm){
		this.pzcsm=pzcsm;
	}

	public String getPzcsmc(){
		return pzcsmc;
	}

	public void setPzcsmc(String pzcsmc){
		this.pzcsmc=pzcsmc;
	}

	public String getMrff(){
		return mrff;
	}

	public void setMrff(String mrff){
		this.mrff=mrff;
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

