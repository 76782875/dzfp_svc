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
 * t_gdjl 实体类
 * 归档记录
 * 由GenEntityMysql类自动生成
 * Wed Nov 30 14:05:47 CST 2016
 * @WangYong
 */ 
@Entity
@Table(name="t_gdjl")
public class Gdjl  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 起始日期
 */ 
@Column(name="qsrq")
	protected String qsrq;

/**
 * 终止日期
 */ 
@Column(name="zzrq")
	protected String zzrq;

/**
 * 状态，0-正在运行中，1-已归档
 */ 
@Column(name="zt")
	protected String zt;

/**
 * 下载链接
 */ 
@Column(name="xzlj")
	protected String xzlj;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

/**
 * 销方id
 */ 
@Column(name="xfid")
	protected Integer xfid;

/**
 * 有效标志，1-有效，0-无效
 */ 
@Column(name="yxbz")
	protected String yxbz;

@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

@Column(name="lrry")
	protected Integer lrry;

@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

@Column(name="xgry")
	protected Integer xgry;

@Column(name="wjsl")
protected Integer wjsl;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getQsrq(){
		return qsrq;
	}

	public void setQsrq(String qsrq){
		this.qsrq=qsrq;
	}

	public String getZzrq(){
		return zzrq;
	}

	public void setZzrq(String zzrq){
		this.zzrq=zzrq;
	}

	public String getZt(){
		return zt;
	}

	public void setZt(String zt){
		this.zt=zt;
	}

	public String getXzlj(){
		return xzlj;
	}

	public void setXzlj(String xzlj){
		this.xzlj=xzlj;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public Integer getXfid(){
		return xfid;
	}

	public void setXfid(Integer xfid){
		this.xfid=xfid;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public Integer getLrry(){
		return lrry;
	}

	public void setLrry(Integer lrry){
		this.lrry=lrry;
	}

	public Date getXgsj(){
		return xgsj;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}

	public Integer getXgry(){
		return xgry;
	}

	public void setXgry(Integer xgry){
		this.xgry=xgry;
	}

	public Integer getWjsl() {
		return wjsl;
	}

	public void setWjsl(Integer wjsl) {
		this.wjsl = wjsl;
	}

}

