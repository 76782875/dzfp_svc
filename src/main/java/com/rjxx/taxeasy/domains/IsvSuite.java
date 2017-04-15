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
 * isv_suite 实体类
 * 套件信息表
 * 由GenEntityMysql类自动生成
 * Thu Apr 13 17:43:05 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="isv_suite")
public class IsvSuite  implements Serializable {

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
 * 套件名字
 */ 
@Column(name="suite_name")
	protected String suiteName;

/**
 * suite 的唯一key
 */ 
@Column(name="suite_key")
	protected String suiteKey;

/**
 * suite的唯一secrect，与key对应
 */ 
@Column(name="suite_secret")
	protected String suiteSecret;

/**
 * 回调信息加解密参数
 */ 
@Column(name="encoding_aes_key")
	protected String encodingAesKey;

/**
 * 已填写用于生成签名和校验毁掉请求的合法性
 */ 
@Column(name="token")
	protected String token;

/**
 * 回调地址
 */ 
@Column(name="event_receive_url")
	protected String eventReceiveUrl;


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

	public String getSuiteName(){
		return suiteName;
	}

	public void setSuiteName(String suiteName){
		this.suiteName=suiteName;
	}

	public String getSuiteKey(){
		return suiteKey;
	}

	public void setSuiteKey(String suiteKey){
		this.suiteKey=suiteKey;
	}

	public String getSuiteSecret(){
		return suiteSecret;
	}

	public void setSuiteSecret(String suiteSecret){
		this.suiteSecret=suiteSecret;
	}

	public String getEncodingAesKey(){
		return encodingAesKey;
	}

	public void setEncodingAesKey(String encodingAesKey){
		this.encodingAesKey=encodingAesKey;
	}

	public String getToken(){
		return token;
	}

	public void setToken(String token){
		this.token=token;
	}

	public String getEventReceiveUrl(){
		return eventReceiveUrl;
	}

	public void setEventReceiveUrl(String eventReceiveUrl){
		this.eventReceiveUrl=eventReceiveUrl;
	}

}

