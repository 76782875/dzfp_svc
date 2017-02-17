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
 * t_dm_fpbc 实体类
 * 发票版次代码表
 * 由GenEntityMysql类自动生成
 * Fri Feb 17 14:30:32 GMT+08:00 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_dm_fpbc")
public class DmFpbc  implements Serializable {

/**
 * 发票版次ID
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 发票版次名称
 */ 
@Column(name="fpbcmc")
	protected String fpbcmc;

/**
 * 最大开票限额
 */ 
@Column(name="zdkpxe")
	protected Double zdkpxe;

/**
 * 排序
 */ 
@Column(name="px")
	protected Integer px;

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
 * 有效标志：1、有效；0、无效；
 */ 
@Column(name="yxbz")
	protected Integer yxbz;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getFpbcmc(){
		return fpbcmc;
	}

	public void setFpbcmc(String fpbcmc){
		this.fpbcmc=fpbcmc;
	}

	public Double getZdkpxe(){
		return zdkpxe;
	}

	public void setZdkpxe(Double zdkpxe){
		this.zdkpxe=zdkpxe;
	}

	public Integer getPx(){
		return px;
	}

	public void setPx(Integer px){
		this.px=px;
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

	public Integer getYxbz(){
		return yxbz;
	}

	public void setYxbz(Integer yxbz){
		this.yxbz=yxbz;
	}

}

