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
 * isv_corp_suite_auth 实体类
 * 企业对套件的授权记录
 * 由GenEntityMysql类自动生成
 * Thu Apr 13 17:36:00 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="isv_corp_suite_auth")
public class IsvCorpSuiteAuth  implements Serializable {

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
 * 临时授权码或永久授权码value
 */ 
@Column(name="permanent_code")
	protected String permanentCode;

/**
 * 企业服务窗永久授权码
 */ 
@Column(name="ch_permanent_code")
	protected String chPermanentCode;


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

	public String getPermanentCode(){
		return permanentCode;
	}

	public void setPermanentCode(String permanentCode){
		this.permanentCode=permanentCode;
	}

	public String getChPermanentCode(){
		return chPermanentCode;
	}

	public void setChPermanentCode(String chPermanentCode){
		this.chPermanentCode=chPermanentCode;
	}

}

