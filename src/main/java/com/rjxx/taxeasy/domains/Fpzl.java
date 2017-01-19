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
 * t_fpzl 实体类
 * 发票种类表
 * 由GenEntityMysql类自动生成
 * Tue Jan 17 11:37:05 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_fpzl")
public class Fpzl  implements Serializable {

/**
 * 主键
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 发票种类代码
 */ 
@Column(name="fpzldm")
	protected String fpzldm;

/**
 * 发票种类名称
 */ 
@Column(name="fpzlmc")
	protected String fpzlmc;

@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 录入人员
 */ 
@Column(name="lrry")
	protected Integer lrry;

@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

/**
 * 修改人员
 */ 
@Column(name="xgry")
	protected Integer xgry;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getFpzldm(){
		return fpzldm;
	}

	public void setFpzldm(String fpzldm){
		this.fpzldm=fpzldm;
	}

	public String getFpzlmc(){
		return fpzlmc;
	}

	public void setFpzlmc(String fpzlmc){
		this.fpzlmc=fpzlmc;
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

