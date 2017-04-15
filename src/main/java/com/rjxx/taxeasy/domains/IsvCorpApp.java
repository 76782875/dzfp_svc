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
 * isv_corp_app 实体类
 * 企业微应用信息表
 * 由GenEntityMysql类自动生成
 * Thu Apr 13 17:34:45 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="isv_corp_app")
public class IsvCorpApp  implements Serializable {

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
 * 钉钉企业使用的微应用ID
 */ 
@Column(name="agent_id")
	protected Long agentId;

/**
 * 钉钉企业使用的微应用名称
 */ 
@Column(name="agent_name")
	protected String agentName;

/**
 * 钉钉企业使用的微应用图标
 */ 
@Column(name="logo_url")
	protected String logoUrl;

/**
 * 钉钉企业使用的微应用原始ID
 */ 
@Column(name="app_id")
	protected Long appId;

/**
 * 使用微应用的企业ID
 */ 
@Column(name="corp_id")
	protected String corpId;


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

	public Long getAgentId(){
		return agentId;
	}

	public void setAgentId(Long agentId){
		this.agentId=agentId;
	}

	public String getAgentName(){
		return agentName;
	}

	public void setAgentName(String agentName){
		this.agentName=agentName;
	}

	public String getLogoUrl(){
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl){
		this.logoUrl=logoUrl;
	}

	public Long getAppId(){
		return appId;
	}

	public void setAppId(Long appId){
		this.appId=appId;
	}

	public String getCorpId(){
		return corpId;
	}

	public void setCorpId(String corpId){
		this.corpId=corpId;
	}

}

