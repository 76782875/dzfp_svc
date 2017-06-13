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
 * t_fphxwsjl 实体类
 * 发票回写接口记录表
 * 由GenEntityMysql类自动生成
 * Tue Jun 13 13:19:10 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_fphxwsjl")
public class Fphxwsjl  implements Serializable {

/**
 * 发票回写接口记录id
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

/**
 * 签名
 */ 
@Column(name="sign")
	protected String sign;

/**
 * 回写内容
 */ 
@Column(name="returncontent")
	protected String returncontent;

/**
 * 开始时间
 */ 
@Column(name="startdate")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date startdate;

/**
 * 结束时间
 */ 
@Column(name="enddate")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date enddate;

/**
 * 密钥
 */ 
@Column(name="secret_key")
	protected String secretKey;

/**
 * 接口地址
 */ 
@Column(name="wsurl")
	protected String wsurl;

/**
 * 返回代码
 */ 
@Column(name="returncode")
	protected String returncode;

/**
 * 录入人员
 */ 
@Column(name="returnmessage")
	protected String returnmessage;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public String getSign(){
		return sign;
	}

	public void setSign(String sign){
		this.sign=sign;
	}

	public String getReturncontent(){
		return returncontent;
	}

	public void setReturncontent(String returncontent){
		this.returncontent=returncontent;
	}

	public Date getStartdate(){
		return startdate;
	}

	public void setStartdate(Date startdate){
		this.startdate=startdate;
	}

	public Date getEnddate(){
		return enddate;
	}

	public void setEnddate(Date enddate){
		this.enddate=enddate;
	}

	public String getSecretKey(){
		return secretKey;
	}

	public void setSecretKey(String secretKey){
		this.secretKey=secretKey;
	}

	public String getWsurl(){
		return wsurl;
	}

	public void setWsurl(String wsurl){
		this.wsurl=wsurl;
	}

	public String getReturncode(){
		return returncode;
	}

	public void setReturncode(String returncode){
		this.returncode=returncode;
	}

	public String getReturnmessage(){
		return returnmessage;
	}

	public void setReturnmessage(String returnmessage){
		this.returnmessage=returnmessage;
	}

}

