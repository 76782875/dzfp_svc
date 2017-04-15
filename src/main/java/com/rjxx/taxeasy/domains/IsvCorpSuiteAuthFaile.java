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
 * isv_corp_suite_auth_faile 实体类
 * 企业对套件的授权失败记录
 * 由GenEntityMysql类自动生成
 * Thu Apr 13 17:37:00 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="isv_corp_suite_auth_faile")
public class IsvCorpSuiteAuthFaile  implements Serializable {

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
 * 企业id
 */ 
@Column(name="corp_id")
	protected String corpId;

/**
 * 失败信息
 */ 
@Column(name="faile_info")
	protected String faileInfo;

/**
 * 授权失败类型
 */ 
@Column(name="auth_faile_type")
	protected String authFaileType;

/**
 * 推送类型
 */ 
@Column(name="suite_push_type")
	protected String suitePushType;


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

	public String getFaileInfo(){
		return faileInfo;
	}

	public void setFaileInfo(String faileInfo){
		this.faileInfo=faileInfo;
	}

	public String getAuthFaileType(){
		return authFaileType;
	}

	public void setAuthFaileType(String authFaileType){
		this.authFaileType=authFaileType;
	}

	public String getSuitePushType(){
		return suitePushType;
	}

	public void setSuitePushType(String suitePushType){
		this.suitePushType=suitePushType;
	}

}

