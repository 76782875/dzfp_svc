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
 * t_jyxxsq_hb 实体类
 * 发票合并处理
 * 由GenEntityMysql类自动生成
 * Tue Apr 10 17:02:13 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_jyxxsq_hb")
public class JyxxsqHb  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 合并前申请流水号
 */ 
@Column(name="oldsqlsh")
	protected Integer oldsqlsh;

/**
 * 合并后申请流水号
 */ 
@Column(name="newsqlsh")
	protected Integer newsqlsh;

/**
 * 有效标志 1、有效；0、无效
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


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getOldsqlsh(){
		return oldsqlsh;
	}

	public void setOldsqlsh(Integer oldsqlsh){
		this.oldsqlsh=oldsqlsh;
	}

	public Integer getNewsqlsh(){
		return newsqlsh;
	}

	public void setNewsqlsh(Integer newsqlsh){
		this.newsqlsh=newsqlsh;
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

}

