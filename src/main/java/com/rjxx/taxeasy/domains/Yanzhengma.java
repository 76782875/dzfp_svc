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
 * t_yanzhengma 实体类
 * 由GenEntityMysql类自动生成
 * Thu Apr 26 14:22:51 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_yanzhengma")
public class Yanzhengma  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 手机号
 */ 
@Column(name="phone")
	protected String phone;

/**
 * 验证码
 */ 
@Column(name="code")
	protected String code;

/**
 * 录入时间
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 短信请求ID
 */ 
@Column(name="requestid")
	protected String requestid;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code=code;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public String getRequestid(){
		return requestid;
	}

	public void setRequestid(String requestid){
		this.requestid=requestid;
	}

}

