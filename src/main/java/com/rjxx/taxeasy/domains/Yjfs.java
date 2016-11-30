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
 * t_yjfs 实体类
 * 邮件发送
 * 由GenEntityMysql类自动生成
 * Wed Nov 30 10:37:49 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_yjfs")
public class Yjfs  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 单据号
 */ 
@Column(name="djh")
	protected Integer djh;

/**
 * 邮件发送结果，1-成功，2失败
 */ 
@Column(name="fsjg")
	protected Integer fsjg;

/**
 * 发送次数
 */ 
@Column(name="fscs")
	protected Integer fscs;

/**
 * 异常原因
 */ 
@Column(name="ycyy")
	protected String ycyy;

/**
 * 录入时间
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 修改时间
 */ 
@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

/**
 * 录入人员
 */ 
@Column(name="lrry")
	protected Integer lrry;

/**
 * 修改人员
 */ 
@Column(name="xgry")
	protected Integer xgry;

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

	public Integer getDjh(){
		return djh;
	}

	public void setDjh(Integer djh){
		this.djh=djh;
	}

	public Integer getFsjg(){
		return fsjg;
	}

	public void setFsjg(Integer fsjg){
		this.fsjg=fsjg;
	}

	public Integer getFscs(){
		return fscs;
	}

	public void setFscs(Integer fscs){
		this.fscs=fscs;
	}

	public String getYcyy(){
		return ycyy;
	}

	public void setYcyy(String ycyy){
		this.ycyy=ycyy;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public Date getXgsj(){
		return xgsj;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}

	public Integer getLrry(){
		return lrry;
	}

	public void setLrry(Integer lrry){
		this.lrry=lrry;
	}

	public Integer getXgry(){
		return xgry;
	}

	public void setXgry(Integer xgry){
		this.xgry=xgry;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

}

