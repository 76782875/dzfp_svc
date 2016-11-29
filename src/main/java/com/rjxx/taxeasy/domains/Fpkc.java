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
 * t_fpkc 实体类
 * 由GenEntityMysql类自动生成
 * Mon Oct 24 16:47:55 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_fpkc")
public class Fpkc  implements Serializable {

/**
 * 主键
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 销方id
 */ 
@Column(name="xfid")
	protected Integer xfid;

/**
 * 税控盘id
 */ 
@Column(name="skpid")
	protected Integer skpid;

/**
 * 发票代码
 */ 
@Column(name="fpdm")
	protected String fpdm;

/**
 * 起始发票号码
 */ 
@Column(name="fphms")
	protected String fphms;

/**
 * 终止发票号码
 */ 
@Column(name="fphmz")
	protected String fphmz;

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
 * 有效标志  1：有效，0：失效
 */ 
@Column(name="yxbz")
	protected String yxbz;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

@Column(name="fpkcl")
	protected Integer fpkcl;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getXfid(){
		return xfid;
	}

	public void setXfid(Integer xfid){
		this.xfid=xfid;
	}

	public Integer getSkpid(){
		return skpid;
	}

	public void setSkpid(Integer skpid){
		this.skpid=skpid;
	}

	public String getFpdm(){
		return fpdm;
	}

	public void setFpdm(String fpdm){
		this.fpdm=fpdm;
	}

	public String getFphms(){
		return fphms;
	}

	public void setFphms(String fphms){
		this.fphms=fphms;
	}

	public String getFphmz(){
		return fphmz;
	}

	public void setFphmz(String fphmz){
		this.fphmz=fphmz;
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

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public Integer getFpkcl(){
		return fpkcl;
	}

	public void setFpkcl(Integer fpkcl){
		this.fpkcl=fpkcl;
	}

}

