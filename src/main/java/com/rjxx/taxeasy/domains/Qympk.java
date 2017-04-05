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
 * t_qympk 实体类
 * 企业名片库表
 * 由GenEntityMysql类自动生成
 * Sat Apr 01 17:29:38 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_qympk")
public class Qympk  implements Serializable {

/**
 * 企业名片库号
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
 * 单位名称
 */ 
@Column(name="dwmc")
	protected String dwmc;

/**
 * 纳税人识别号
 */ 
@Column(name="nsrsbh")
	protected String nsrsbh;

/**
 * 注册地址
 */ 
@Column(name="zcdz")
	protected String zcdz;

/**
 * 注册电话
 */ 
@Column(name="zcdh")
	protected String zcdh;

/**
 * 开户银行
 */ 
@Column(name="khyh")
	protected String khyh;

/**
 * 银行账号
 */ 
@Column(name="yhzh")
	protected String yhzh;

/**
 * 有效标志
 */ 
@Column(name="yxbz")
	protected String yxbz;

/**
 * 录入人员
 */ 
@Column(name="lrry")
	protected Integer lrry;

/**
 * 修改人员
 */ 
@Column(name="xgry")
	protected Integer xgry;

/**
 * 录入时间
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 修改时间
 */ 
@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

/**
 * 销方id
 */ 
@Column(name="xfid")
	protected Integer xfid;

/**
 * 购方信息id
 */ 
@Column(name="gfxxid")
	protected Integer gfxxid;

/**
 * 联系人
 */ 
@Column(name="lxr")
	protected String lxr;

/**
 * 联系电话
 */ 
@Column(name="lxdh")
	protected String lxdh;

/**
 * 邮寄地址
 */ 
@Column(name="yjdz")
	protected String yjdz;

/**
 * email地址
 */ 
@Column(name="email")
	protected String email;


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

	public String getDwmc(){
		return dwmc;
	}

	public void setDwmc(String dwmc){
		this.dwmc=dwmc;
	}

	public String getNsrsbh(){
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh){
		this.nsrsbh=nsrsbh;
	}

	public String getZcdz(){
		return zcdz;
	}

	public void setZcdz(String zcdz){
		this.zcdz=zcdz;
	}

	public String getZcdh(){
		return zcdh;
	}

	public void setZcdh(String zcdh){
		this.zcdh=zcdh;
	}

	public String getKhyh(){
		return khyh;
	}

	public void setKhyh(String khyh){
		this.khyh=khyh;
	}

	public String getYhzh(){
		return yhzh;
	}

	public void setYhzh(String yhzh){
		this.yhzh=yhzh;
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

	public Integer getXgry(){
		return xgry;
	}

	public void setXgry(Integer xgry){
		this.xgry=xgry;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public Date getXgsj(){
		return xgsj;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}

	public Integer getXfid(){
		return xfid;
	}

	public void setXfid(Integer xfid){
		this.xfid=xfid;
	}

	public Integer getGfxxid(){
		return gfxxid;
	}

	public void setGfxxid(Integer gfxxid){
		this.gfxxid=gfxxid;
	}

	public String getLxr(){
		return lxr;
	}

	public void setLxr(String lxr){
		this.lxr=lxr;
	}

	public String getLxdh(){
		return lxdh;
	}

	public void setLxdh(String lxdh){
		this.lxdh=lxdh;
	}

	public String getYjdz(){
		return yjdz;
	}

	public void setYjdz(String yjdz){
		this.yjdz=yjdz;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

}

