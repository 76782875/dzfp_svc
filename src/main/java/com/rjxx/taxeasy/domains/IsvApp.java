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
 * isv_app 实体类
 * isv创建的app
 * 由GenEntityMysql类自动生成
 * Thu Apr 13 17:27:47 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="isv_app")
public class IsvApp  implements Serializable {

/**
 * pl
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
 * 微应用套件key
 */ 
@Column(name="suite_key")
	protected String suiteKey;

/**
 * appid,此id来自于开发者中心
 */ 
@Column(name="app_id")
	protected Long appId;


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

	public Long getAppId(){
		return appId;
	}

	public void setAppId(Long appId){
		this.appId=appId;
	}

}

