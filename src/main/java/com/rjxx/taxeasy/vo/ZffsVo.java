package com.rjxx.taxeasy.vo;

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

public class ZffsVo  implements Serializable {

/**
 * 主键id
 */ 
	protected Integer id;

/**
 * 支付方式代码
 */ 
	protected String zffsDm;

/**
 * 支付方式代码
 */ 
	protected String zffsMc;

/**
 * 开票方式（01开票，02折扣）
 */ 
	protected String kpfsDm;

/**
 * 备注处理方式（01显示折扣金额、02...）
 */ 
	protected String bzclfsDm;

/**
 * 公司代码
 */ 
	protected String gsdm;

/**
 * 有效标志（1有效，0无效）
 */ 
	protected String yxbz;

/**
 * 录入时间
 */ 
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 录入人员
 */ 
	protected Integer lrry;

/**
 * 修改时间
 */ 
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

/**
 * 修改人员
 */ 
	protected Integer xgry;

	/**
	 * 支付金額
	 */ 
	protected String zfje;
	
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

	public String getZffsMc() {
		return zffsMc;
	}

	public void setZffsMc(String zffsMc) {
		this.zffsMc = zffsMc;
	}

	public String getZfje() {
		return zfje;
	}

	public void setZfje(String zfje) {
		this.zfje = zfje;
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

