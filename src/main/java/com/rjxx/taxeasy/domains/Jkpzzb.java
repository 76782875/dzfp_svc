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
 * t_jkpzzb 实体类
 * 由GenEntityMysql类自动生成
 * Tue Mar 20 16:08:46 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_jkpzzb")
public class Jkpzzb  implements Serializable {

/**
 * 主键id
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 模板id
 */ 
@Column(name="mbid")
	protected Integer mbid;

/**
 * t_jkpzb表id
 */ 
@Column(name="pzbid")
	protected Integer pzbid;

/**
 * 获取接口参数方法（字典表）
 */ 
@Column(name="cszffid")
	protected Integer cszffid;

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


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getMbid(){
		return mbid;
	}

	public void setMbid(Integer mbid){
		this.mbid=mbid;
	}

	public Integer getPzbid(){
		return pzbid;
	}

	public void setPzbid(Integer pzbid){
		this.pzbid=pzbid;
	}

	public Integer getCszffid() {
		return cszffid;
	}

	public void setCszffid(Integer cszffid) {
		this.cszffid = cszffid;
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

}

