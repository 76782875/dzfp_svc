package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * t_zclog 实体类
 * 由GenEntityMysql类自动生成
 * Thu Apr 26 17:24:00 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_zclog")
public class Zclog  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 手机号
 */ 
@Column(name="phone")
	protected String phone;

/**
 * 注册次数
 */ 
@Column(name="zcnum")
	protected Integer zcnum;

/**
 * 体验次数
 */ 
@Column(name="tynum")
	protected Integer tynum;

/**
 * 是否购买成功
 */ 
@Column(name="success")
	protected String success;


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

	public Integer getZcnum(){
		return zcnum;
	}

	public void setZcnum(Integer zcnum){
		this.zcnum=zcnum;
	}

	public Integer getTynum(){
		return tynum;
	}

	public void setTynum(Integer tynum){
		this.tynum=tynum;
	}

	public String getSuccess(){
		return success;
	}

	public void setSuccess(String success){
		this.success=success;
	}

}

