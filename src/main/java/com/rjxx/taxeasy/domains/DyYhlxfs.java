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
 * t_dy_yhlxfs 实体类
 * 用户订阅的联系方式
 * 由GenEntityMysql类自动生成
 * Thu Mar 09 14:04:53 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_dy_yhlxfs")
public class DyYhlxfs  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 用户id
 */ 
@Column(name="yhid")
	protected Integer yhid;

/**
 * 姓名
 */ 
@Column(name="xm")
	protected String xm;

/**
 * 手机号码
 */ 
@Column(name="sjhm")
	protected String sjhm;

/**
 * 邮箱
 */ 
@Column(name="yx")
	protected String yx;

/**
 * 微信公众号
 */ 
@Column(name="wx_openid")
	protected String wxOpenid;

/**
 * 有效标志，1-有效，0-无效
 */ 
@Column(name="yxbz")
	protected String yxbz;

@Column(name="lrry")
	protected Integer lrry;

@Column(name="xgry")
	protected Integer xgry;

@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

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

	public Integer getYhid(){
		return yhid;
	}

	public void setYhid(Integer yhid){
		this.yhid=yhid;
	}

	public String getXm(){
		return xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getSjhm(){
		return sjhm;
	}

	public void setSjhm(String sjhm){
		this.sjhm=sjhm;
	}

	public String getYx(){
		return yx;
	}

	public void setYx(String yx){
		this.yx=yx;
	}

	public String getWxOpenid(){
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid){
		this.wxOpenid=wxOpenid;
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

	public Integer getXgry(){
		return xgry;
	}

	public void setXgry(Integer xgry){
		this.xgry=xgry;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public Date getXgsj(){
		return xgsj;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}

}

