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
 * isv_corp_suite_jsapi_ticket 实体类
 * 企业使用jsapi的js ticket表
 * 由GenEntityMysql类自动生成
 * Thu Apr 20 13:05:21 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="isv_corp_suite_jsapi_ticket")
public class IsvCorpSuiteJsapiTicket  implements Serializable {

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
 * 钉钉企业id
 */ 
@Column(name="corp_id")
	protected String corpId;

/**
 * 套件accesstoken
 */ 
@Column(name="corpaccesstoken")
	protected String corpaccesstoken;

/**
 * 企业js_ticket
 */ 
@Column(name="corp_jsapi_ticket")
	protected String corpJsapiTicket;

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

	public String getCorpId(){
		return corpId;
	}

	public void setCorpId(String corpId){
		this.corpId=corpId;
	}

	public String getCorpaccesstoken(){
		return corpaccesstoken;
	}

	public void setCorpaccesstoken(String corpaccesstoken){
		this.corpaccesstoken=corpaccesstoken;
	}

	public String getCorpJsapiTicket(){
		return corpJsapiTicket;
	}

	public void setCorpJsapiTicket(String corpJsapiTicket){
		this.corpJsapiTicket=corpJsapiTicket;
	}

	public Date getExpiredTime(){
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime){
		this.expiredTime=expiredTime;
	}

}

