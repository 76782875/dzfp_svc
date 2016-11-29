package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * t_drmb 实体类
 * 由GenEntityMysql类自动生成
 * Wed Oct 19 09:03:07 GMT+08:00 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_drmb")
public class Drmb  implements Serializable {

/**
 * 主键id
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 模板名称
 */ 
@Column(name="mbmc")
	protected String mbmc;

/**
 * 用户id
 */ 
@Column(name="yhid")
	protected Integer yhid;

/**
 * 销方税号
 */ 
@Column(name="xfsh")
	protected String xfsh;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

/**
 * 共享标志
 */ 
@Column(name="gxbz")
	protected String gxbz;

/**
 * 录入时间
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 录入人员
 */ 
@Column(name="xgry")
	protected Integer xgry;

/**
 * 修改人员
 */ 
@Column(name="lrry")
	protected Integer lrry;

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

	public String getMbmc(){
		return mbmc;
	}

	public void setMbmc(String mbmc){
		this.mbmc=mbmc;
	}

	public Integer getYhid(){
		return yhid;
	}

	public void setYhid(Integer yhid){
		this.yhid=yhid;
	}

	public String getXfsh(){
		return xfsh;
	}

	public void setXfsh(String xfsh){
		this.xfsh=xfsh;
	}

	public String getGsdm() {
		return gsdm;
	}

	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}

	public String getGxbz() {
		return gxbz;
	}

	public void setGxbz(String gxbz) {
		this.gxbz = gxbz;
	}

	public Date getLrsj() {
		return lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public Integer getXgry() {
		return xgry;
	}

	public void setXgry(Integer xgry) {
		this.xgry = xgry;
	}

	public Integer getLrry() {
		return lrry;
	}

	public void setLrry(Integer lrry) {
		this.lrry = lrry;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

}

