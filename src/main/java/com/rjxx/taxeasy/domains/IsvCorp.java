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
 * isv_corp 实体类
 * 企业信息表
 * 由GenEntityMysql类自动生成
 * Thu Apr 13 17:33:02 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="isv_corp")
public class IsvCorp  implements Serializable {

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
 * 钉钉企业ID
 */ 
@Column(name="corp_id")
	protected String corpId;

/**
 * 企业邀请码
 */ 
@Column(name="invite_code")
	protected String inviteCode;

/**
 * 企业所属行业
 */ 
@Column(name="industry")
	protected String industry;

/**
 * 企业名称
 */ 
@Column(name="corp_name")
	protected String corpName;

/**
 * 企业邀请链接
 */ 
@Column(name="invite_url")
	protected String inviteUrl;

/**
 * 企业logo链接
 */ 
@Column(name="corp_logo_url")
	protected String corpLogoUrl;


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

	public String getInviteCode(){
		return inviteCode;
	}

	public void setInviteCode(String inviteCode){
		this.inviteCode=inviteCode;
	}

	public String getIndustry(){
		return industry;
	}

	public void setIndustry(String industry){
		this.industry=industry;
	}

	public String getCorpName(){
		return corpName;
	}

	public void setCorpName(String corpName){
		this.corpName=corpName;
	}

	public String getInviteUrl(){
		return inviteUrl;
	}

	public void setInviteUrl(String inviteUrl){
		this.inviteUrl=inviteUrl;
	}

	public String getCorpLogoUrl(){
		return corpLogoUrl;
	}

	public void setCorpLogoUrl(String corpLogoUrl){
		this.corpLogoUrl=corpLogoUrl;
	}

}

