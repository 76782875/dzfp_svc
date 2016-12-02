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
 * roleprivs 实体类
 * 由GenEntityMysql类自动生成
 * Thu Dec 01 15:36:06 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="roleprivs")
public class Roleprivs  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;
/**
 * 角色id
 */ 
@Column(name="roleid")
	protected Integer roleid;

/**
 * 菜单id
 */ 
@Column(name="privid")
	protected Integer privid;

@Column(name="buttonprivs")
	protected String buttonprivs;

/**
 * 状态标识
 */ 
@Column(name="yxbz")
	protected String yxbz;

/**
 * 描述
 */ 
@Column(name="description")
	protected String description;

/**
 * 排序
 */ 
@Column(name="sort")
	protected Integer sort;

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
	protected Integer lrry;

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
	protected Integer xgry;


	public Integer getRoleid(){
		return roleid;
	}

	public void setRoleid(Integer roleid){
		this.roleid=roleid;
	}

	public Integer getPrivid(){
		return privid;
	}

	public void setPrivid(Integer privid){
		this.privid=privid;
	}

	public String getButtonprivs(){
		return buttonprivs;
	}

	public void setButtonprivs(String buttonprivs){
		this.buttonprivs=buttonprivs;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description=description;
	}

	public Integer getSort(){
		return sort;
	}

	public void setSort(Integer sort){
		this.sort=sort;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}

