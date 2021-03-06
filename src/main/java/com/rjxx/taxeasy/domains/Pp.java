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
 * t_pp 实体类
 * 由GenEntityMysql类自动生成
 * Mon Dec 12 17:15:03 GMT+08:00 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_pp")
public class Pp  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 品牌代码
 */ 
@Column(name="ppdm")
	protected String ppdm;

/**
 * 品牌名称
 */ 
@Column(name="ppmc")
	protected String ppmc;

/**
 * 品牌url
 */ 
@Column(name="ppurl")
	protected String ppurl;

/**
 * 有效标志：0，无效；1，有效
 */ 
@Column(name="yxbz")
	protected String yxbz;

/**
 * 录入人员
 */ 
@Column(name="lrry")
	protected Integer lrry;

/**
 * 录入时间
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 修改人员
 */ 
@Column(name="xgry")
	protected Integer xgry;

/**
 * 修改时间
 */ 
@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getPpdm(){
		return ppdm;
	}

	public void setPpdm(String ppdm){
		this.ppdm=ppdm;
	}

	public String getPpmc(){
		return ppmc;
	}

	public void setPpmc(String ppmc){
		this.ppmc=ppmc;
	}

	public String getPpurl(){
		return ppurl;
	}

	public void setPpurl(String ppurl){
		this.ppurl=ppurl;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
	}

	public Integer getLrry(){
		return lrry;
	}

	public void setLrry(Integer lrry){
		this.lrry=lrry;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public Integer getXgry(){
		return xgry;
	}

	public void setXgry(Integer xgry){
		this.xgry=xgry;
	}

	public Date getXgsj(){
		return xgsj;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}

}

