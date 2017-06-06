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
 * t_zffs 实体类
 * 支付方式业务表
 * 由GenEntityMysql类自动生成
 * Thu Jun 01 13:45:44 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_zffs")
public class Zffs  implements Serializable {

/**
 * 主键id
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 支付方式代码
 */ 
@Column(name="zffs_dm")
	protected String zffsDm;

/**
 * 开票方式（01开票，02折扣）
 */ 
@Column(name="kpfs_dm")
	protected String kpfsDm;

/**
 * 备注处理方式（01显示折扣金额、02...）
 */ 
@Column(name="bzclfs_dm")
	protected String bzclfsDm;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

/**
 * 有效标志（1有效，0无效）
 */ 
@Column(name="yxbz")
	protected String yxbz;

/**
 * 录入时间
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 录入人员
 */ 
@Column(name="lrry")
	protected Integer lrry;

/**
 * 修改时间
 */ 
@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

/**
 * 修改人员
 */ 
@Column(name="xgry")
	protected Integer xgry;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getZffsDm(){
		return zffsDm;
	}

	public void setZffsDm(String zffsDm){
		this.zffsDm=zffsDm;
	}

	public String getKpfsDm(){
		return kpfsDm;
	}

	public void setKpfsDm(String kpfsDm){
		this.kpfsDm=kpfsDm;
	}

	public String getBzclfsDm(){
		return bzclfsDm;
	}

	public void setBzclfsDm(String bzclfsDm){
		this.bzclfsDm=bzclfsDm;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public Integer getLrry(){
		return lrry;
	}

	public void setLrry(Integer lrry){
		this.lrry=lrry;
	}

	public Date getXgsj(){
		return xgsj;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}

	public Integer getXgry(){
		return xgry;
	}

	public void setXgry(Integer xgry){
		this.xgry=xgry;
	}

}

