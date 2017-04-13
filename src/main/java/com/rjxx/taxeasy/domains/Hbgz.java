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
 * t_hbgz 实体类
 * 由GenEntityMysql类自动生成
 * Wed Apr 12 17:42:36 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_hbgz")
public class Hbgz  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 规则名称
 */ 
@Column(name="gzmc")
	protected String gzmc;

/**
 * 规则说明
 */ 
@Column(name="bz")
	protected String bz;

/**
 * 有效标志，0：无效，1：有效，2：删除
 */ 
@Column(name="yxbz")
	protected String yxbz;

/**
 * 默认标志，0：否，1：是
 */ 
@Column(name="mrbz")
	protected String mrbz;

/**
 * 客户号标志，0：否，1：是
 */ 
@Column(name="khhbz")
	protected String khhbz;

/**
 * 运至号标志，0：否，1:是
 */ 
@Column(name="yzhbz")
	protected String yzhbz;

/**
 * 品项标志，0：否，1：是
 */ 
@Column(name="pxbz")
	protected String pxbz;

/**
 * 单价标志，0：否，1：是
 */ 
@Column(name="djbz")
	protected String djbz;

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

@Column(name="spmcbz")
	protected String spmcbz;

@Column(name="spbhb")
	protected String spbhb;

@Column(name="gsdm")
protected String gsdm;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getGzmc(){
		return gzmc;
	}

	public void setGzmc(String gzmc){
		this.gzmc=gzmc;
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

	public String getMrbz(){
		return mrbz;
	}

	public void setMrbz(String mrbz){
		this.mrbz=mrbz;
	}

	public String getKhhbz(){
		return khhbz;
	}

	public void setKhhbz(String khhbz){
		this.khhbz=khhbz;
	}

	public String getYzhbz(){
		return yzhbz;
	}

	public void setYzhbz(String yzhbz){
		this.yzhbz=yzhbz;
	}

	public String getPxbz(){
		return pxbz;
	}

	public void setPxbz(String pxbz){
		this.pxbz=pxbz;
	}

	public String getDjbz(){
		return djbz;
	}

	public void setDjbz(String djbz){
		this.djbz=djbz;
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

	public String getSpmcbz(){
		return spmcbz;
	}

	public void setSpmcbz(String spmcbz){
		this.spmcbz=spmcbz;
	}

	public String getSpbhb(){
		return spbhb;
	}

	public void setSpbhb(String spbhb){
		this.spbhb=spbhb;
	}

	public String getGsdm() {
		return gsdm;
	}

	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}

}

