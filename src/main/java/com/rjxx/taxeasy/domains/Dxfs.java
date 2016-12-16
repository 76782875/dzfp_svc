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
 * t_dxfs 实体类
 * 由GenEntityMysql类自动生成
 * Wed Dec 14 18:22:03 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_dxfs")
public class Dxfs  implements Serializable {

/**
 * 主键
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

@Column(name="djh")
	protected Integer djh;

/**
 * 手机号码
 */ 
@Column(name="sjhm")
	protected String sjhm;

/**
 * 短信内容
 */ 
@Column(name="dxnr")
	protected String dxnr;

/**
 * 模板代码
 */ 
@Column(name="mbdm")
	protected String mbdm;

@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 短信返回id
 */ 
@Column(name="returnid")
	protected String returnid;


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

	public Integer getDjh(){
		return djh;
	}

	public void setDjh(Integer djh){
		this.djh=djh;
	}

	public String getSjhm(){
		return sjhm;
	}

	public void setSjhm(String sjhm){
		this.sjhm=sjhm;
	}

	public String getDxnr(){
		return dxnr;
	}

	public void setDxnr(String dxnr){
		this.dxnr=dxnr;
	}

	public String getMbdm(){
		return mbdm;
	}

	public void setMbdm(String mbdm){
		this.mbdm=mbdm;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public String getReturnid(){
		return returnid;
	}

	public void setReturnid(String returnid){
		this.returnid=returnid;
	}

}

