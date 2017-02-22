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
 * t_xt_yclog 实体类
 * 系统异常日志记录表
 * 由GenEntityMysql类自动生成
 * Wed Feb 15 15:43:23 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_xt_yclog")
public class XtYclog  implements Serializable {

/**
 * 主键
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 异常等级（1，发邮件短信，2只发邮件，3只发短信，4都不发）
 */ 
@Column(name="ycdj")
	protected String ycdj;

/**
 * 异常类名
 */ 
@Column(name="yclm")
	protected String yclm;

/**
 * 异常信息
 */ 
@Column(name="ycxx")
	protected String ycxx;

/**
 * 异常所在方法
 */ 
@Column(name="ycff")
	protected String ycff;

/**
 * 异常接收人手机号
 */ 
@Column(name="ycjsrsjh")
	protected String ycjsrsjh;

/**
 * 异常接收人邮箱
 */ 
@Column(name="ycjsryx")
	protected String ycjsryx;

/**
 * 发送标志（0未发送，1已发送）
 */ 
@Column(name="fsbz")
	protected String fsbz;

/**
 * 修改时间
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

	public String getYcdj(){
		return ycdj;
	}

	public void setYcdj(String ycdj){
		this.ycdj=ycdj;
	}

	public String getYcxx(){
		return ycxx;
	}

	public void setYcxx(String ycxx){
		this.ycxx=ycxx;
	}

	public String getYcff(){
		return ycff;
	}

	public void setYcff(String ycff){
		this.ycff=ycff;
	}

	public String getYcjsrsjh(){
		return ycjsrsjh;
	}

	public void setYcjsrsjh(String ycjsrsjh){
		this.ycjsrsjh=ycjsrsjh;
	}

	public String getYcjsryx(){
		return ycjsryx;
	}

	public void setYcjsryx(String ycjsryx){
		this.ycjsryx=ycjsryx;
	}

	public String getFsbz(){
		return fsbz;
	}

	public void setFsbz(String fsbz){
		this.fsbz=fsbz;
	}

	public Date getLrsj() {
		return lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public String getYclm() {
		return yclm;
	}

	public void setYclm(String yclm) {
		this.yclm = yclm;
	}


}

