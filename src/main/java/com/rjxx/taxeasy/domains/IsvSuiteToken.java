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
 * isv_suite_token 实体类
 * 套件的accesstoken表
 * 由GenEntityMysql类自动生成
 * Thu Apr 13 17:44:53 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="isv_suite_token")
public class IsvSuiteToken  implements Serializable {

/**
 * 主键
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;

/**
 * 创建时间
 */ 
@Column(name="gmt_create")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date gmtCreate;

/**
 * 修改时间
 */ 
@Column(name="gmt_modified")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date gmtModified;

/**
 * 套件key
 */ 
@Column(name="suite_key")
	protected String suiteKey;

/**
 * 套件token
 */ 
@Column(name="suite_token")
	protected String suiteToken;

/**
 * 过期时间
 */ 
@Column(name="expired_time")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date expiredTime;


	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id=id;
	}

	public Date getGmtCreate(){
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate){
		this.gmtCreate=gmtCreate;
	}

	public Date getGmtModified(){
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified){
		this.gmtModified=gmtModified;
	}

	public String getSuiteKey(){
		return suiteKey;
	}

	public void setSuiteKey(String suiteKey){
		this.suiteKey=suiteKey;
	}

	public String getSuiteToken(){
		return suiteToken;
	}

	public void setSuiteToken(String suiteToken){
		this.suiteToken=suiteToken;
	}

	public Date getExpiredTime(){
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime){
		this.expiredTime=expiredTime;
	}

}

