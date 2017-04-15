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
 * isv_corp_suite_callback 实体类
 * 企业注册回调事件
 * 由GenEntityMysql类自动生成
 * Thu Apr 13 17:40:12 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="isv_corp_suite_callback")
public class IsvCorpSuiteCallback  implements Serializable {

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
 * 企业corpid
 */ 
@Column(name="corp_id")
	protected String corpId;

/**
 * 套件key
 */ 
@Column(name="suite_key")
	protected String suiteKey;

/**
 * 注册事件tag,json结构存储
 */ 
@Column(name="callback_tag")
	protected String callbackTag;


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

	public String getCorpId(){
		return corpId;
	}

	public void setCorpId(String corpId){
		this.corpId=corpId;
	}

	public String getSuiteKey(){
		return suiteKey;
	}

	public void setSuiteKey(String suiteKey){
		this.suiteKey=suiteKey;
	}

	public String getCallbackTag(){
		return callbackTag;
	}

	public void setCallbackTag(String callbackTag){
		this.callbackTag=callbackTag;
	}

}

