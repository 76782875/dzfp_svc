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
 * t_xx_xtxx 实体类
 * 系统消息表
 * 由GenEntityMysql类自动生成
 * Thu Mar 23 14:22:30 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_xx_xtxx")
public class XxXtxx  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 标题
 */ 
@Column(name="title")
	protected String title;

/**
 * 正文
 */ 
@Column(name="content")
	protected String content;

/**
 * 消息类型
 */ 
@Column(name="xxlx")
	protected String xxlx;

/**
 * 已读标志，0-未读，1-已读
 */ 
@Column(name="ydbz")
	protected String ydbz;

/**
 * 目标用户id
 */ 
@Column(name="yhid")
	protected Integer yhid;

/**
 * 有效标志
 */ 
@Column(name="yxbz")
	protected String yxbz;

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


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getXxlx(){
		return xxlx;
	}

	public void setXxlx(String xxlx){
		this.xxlx=xxlx;
	}

	public String getYdbz(){
		return ydbz;
	}

	public void setYdbz(String ydbz){
		this.ydbz=ydbz;
	}

	public Integer getYhid(){
		return yhid;
	}

	public void setYhid(Integer yhid){
		this.yhid=yhid;
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

}

