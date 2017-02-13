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
 * t_spz 实体类
 * 由GenEntityMysql类自动生成
 * Tue Jan 17 14:59:50 GMT+08:00 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_spz")
public class Spz  implements Serializable {

/**
 * 主键id
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 商品组名称
 */ 
@Column(name="spzmc")
	protected String spzmc;

/**
 * 商品组说明
 */ 
@Column(name="bz")
	protected String bz;

/**
 * 有效标志，1有效，2删除
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

/**
 * 组标志：1，公共组；2，私有组
 */ 
@Column(name="zbz")
	protected String zbz;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getSpzmc(){
		return spzmc;
	}

	public void setSpzmc(String spzmc){
		this.spzmc=spzmc;
	}

	public String getBz(){
		return bz;
	}

	public void setBz(String bz){
		this.bz=bz;
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

	public String getZbz(){
		return zbz;
	}

	public void setZbz(String zbz){
		this.zbz=zbz;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

}

