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
 * t_gszc 实体类
 * 由GenEntityMysql类自动生成
 * Tue Dec 20 15:51:01 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_gszc")
public class Gszc  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 公司名称
 */ 
@Column(name="gsmc")
	protected String gsmc;

/**
 * 联系人
 */ 
@Column(name="lxr")
	protected String lxr;

/**
 * 职位
 */ 
@Column(name="zw")
	protected String zw;

/**
 * 联系电话
 */ 
@Column(name="lxdh")
	protected String lxdh;

/**
 * 所属城市
 */ 
@Column(name="sscs")
	protected String sscs;

/**
 * 录入时间
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getGsmc(){
		return gsmc;
	}

	public void setGsmc(String gsmc){
		this.gsmc=gsmc;
	}

	public String getLxr(){
		return lxr;
	}

	public void setLxr(String lxr){
		this.lxr=lxr;
	}

	public String getZw(){
		return zw;
	}

	public void setZw(String zw){
		this.zw=zw;
	}

	public String getLxdh(){
		return lxdh;
	}

	public void setLxdh(String lxdh){
		this.lxdh=lxdh;
	}

	public String getSscs(){
		return sscs;
	}

	public void setSscs(String sscs){
		this.sscs=sscs;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

}

