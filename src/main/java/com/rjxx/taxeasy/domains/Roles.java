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
 * roles 实体类
 * 由GenEntityMysql类自动生成
 * Thu Dec 01 11:14:57 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="roles")
public class Roles  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

@Column(name="name")
	protected String name;

@Column(name="description")
	protected String description;

@Column(name="privilegeids")
	protected String privilegeids;

@Column(name="sort")
	protected Integer sort;

/**
 * 状态标识
 */ 
@Column(name="yxbz")
	protected String yxbz;

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
@Column(name="lrry")
	protected String lrry;

/**
 * 修改时间
 */ 
@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

/**
 * 修改人员
 */ 
@Column(name="xgry")
	protected String xgry;
/**
 * 是否是系统默认角色
 */
@Column(name="sfsmr")
	protected String sfsmr;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description=description;
	}

	public String getPrivilegeids(){
		return privilegeids;
	}

	public void setPrivilegeids(String privilegeids){
		this.privilegeids=privilegeids;
	}

	public Integer getSort(){
		return sort;
	}

	public void setSort(Integer sort){
		this.sort=sort;
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

	public String getLrry(){
		return lrry;
	}

	public void setLrry(String lrry){
		this.lrry=lrry;
	}

	public Date getXgsj(){
		return xgsj;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}

	public String getXgry(){
		return xgry;
	}

	public void setXgry(String xgry){
		this.xgry=xgry;
	}

	public String getSfsmr() {
		return sfsmr;
	}

	public void setSfsmr(String sfsmr) {
		this.sfsmr = sfsmr;
	}

}

