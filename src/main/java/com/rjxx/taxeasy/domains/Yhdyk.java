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
 * t_yhdyk 实体类
 * 由GenEntityMysql类自动生成
 * Fri Feb 24 16:07:00 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_yhdyk")
public class Yhdyk  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 订阅标题主键
 */ 
@Column(name="dybtid")
	protected Integer dybtid;

@Column(name="yhid")
	protected Integer yhid;

@Column(name="lrry")
	protected Integer lrry;

@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

@Column(name="xgry")
	protected Integer xgry;

@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

@Column(name="yxbz")
	protected String yxbz;

@Column(name="dyfs")
	protected String dyfs;

@Column(name="sjhm")
	protected String sjhm;

@Column(name="email")
	protected String email;

@Column(name="openid")
	protected String openid;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getDybtid(){
		return dybtid;
	}

	public void setDybtid(Integer dybtid){
		this.dybtid=dybtid;
	}

	public Integer getYhid(){
		return yhid;
	}

	public void setYhid(Integer yhid){
		this.yhid=yhid;
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

	public String getDyfs(){
		return dyfs;
	}

	public void setDyfs(String dyfs){
		this.dyfs=dyfs;
	}

	public String getSjhm(){
		return sjhm;
	}

	public void setSjhm(String sjhm){
		this.sjhm=sjhm;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getOpenid(){
		return openid;
	}

	public void setOpenid(String openid){
		this.openid=openid;
	}

}

