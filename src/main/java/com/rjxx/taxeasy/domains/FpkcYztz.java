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
 * t_fpkc_yztz 实体类
 * 发票库存预警通知表
 * 由GenEntityMysql类自动生成
 * Fri Apr 27 13:27:44 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_fpkc_yztz")
public class FpkcYztz  implements Serializable {

/**
 * 主键id
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * t_fpkc_yzsz表id
 */ 
@Column(name="yjszid")
	protected Integer yjszid;

/**
 * 通知用户方式（01：邮件+短信，02：邮件，03：短信）
 */ 
@Column(name="tzfs")
	protected String tzfs;
/**
 * 公司代码
 */
@Column(name="gsdm")
	protected String gsdm;

/**
 * 通知用户id
 */ 
@Column(name="tzyhid")
	protected Integer tzyhid;

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
 * 有效标志，默认有效
 */ 
@Column(name="yxbz")
	protected String yxbz;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getYjszid(){
		return yjszid;
	}

	public void setYjszid(Integer yjszid){
		this.yjszid=yjszid;
	}

	public String getTzfs(){
		return tzfs;
	}

	public void setTzfs(String tzfs){
		this.tzfs=tzfs;
	}

	public Integer getTzyhid(){
		return tzyhid;
	}

	public void setTzyhid(Integer tzyhid){
		this.tzyhid=tzyhid;
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

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
	}

	public String getGsdm() {
		return gsdm;
	}

	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}
}

