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
 * t_fpyjdy 实体类
 * 由GenEntityMysql类自动生成
 * Tue Oct 25 10:18:40 CST 2016
 * @Wangyong
 */ 
@Entity
@Table(name="t_fpyjdy")
public class Fpyjdy  implements Serializable {

/**
 * 主键
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 用户id
 */ 
@Column(name="yhid")
	protected Integer yhid;

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
 * 税控盘id
 */ 
@Column(name="skpid")
	protected Integer skpid;

/**
 * 是否首页订阅
 */ 
@Column(name="sfsy")
	protected String sfsy;

/**
 * 是否email订阅
 */ 
@Column(name="sfemail")
	protected String sfemail;

/**
 * 有效标志
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
 * 库存预警阈值
 */ 
@Column(name="yjkcl")
	protected Integer yjkcl;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getYhid(){
		return yhid;
	}

	public void setYhid(Integer yhid){
		this.yhid=yhid;
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

	public Integer getSkpid(){
		return skpid;
	}

	public void setSkpid(Integer skpid){
		this.skpid=skpid;
	}

	public String getSfsy(){
		return sfsy;
	}

	public void setSfsy(String sfsy){
		this.sfsy=sfsy;
	}

	public String getSfemail(){
		return sfemail;
	}

	public void setSfemail(String sfemail){
		this.sfemail=sfemail;
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

	public Integer getYjkcl(){
		return yjkcl;
	}

	public void setYjkcl(Integer yjkcl){
		this.yjkcl=yjkcl;
	}

}

