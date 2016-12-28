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
 * t_wxfs 实体类
 * 微信模板信息发送记录
 * 由GenEntityMysql类自动生成
 * Wed Dec 28 17:48:12 GMT+08:00 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_wxfs")
public class Wxfs  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

/**
 * 单据号
 */ 
@Column(name="djh")
	protected Integer djh;

/**
 * 微信openid
 */ 
@Column(name="openid")
	protected String openid;

/**
 * 是否发送成功
 */ 
@Column(name="issuccess")
	protected String issuccess;

/**
 * 返回信息
 */ 
@Column(name="returnmsg")
	protected String returnmsg;

/**
 * 录入时间
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public Integer getDjh(){
		return djh;
	}

	public void setDjh(Integer djh){
		this.djh=djh;
	}

	public String getOpenid(){
		return openid;
	}

	public void setOpenid(String openid){
		this.openid=openid;
	}

	public String getIssuccess(){
		return issuccess;
	}

	public void setIssuccess(String issuccess){
		this.issuccess=issuccess;
	}

	public String getReturnmsg(){
		return returnmsg;
	}

	public void setReturnmsg(String returnmsg){
		this.returnmsg=returnmsg;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

}

