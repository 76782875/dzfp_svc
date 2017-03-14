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
 * t_dy_wddy 实体类
 * 我的订阅
 * 由GenEntityMysql类自动生成
 * Thu Mar 09 14:04:23 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_dy_wddy")
public class DyWddy  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 用户id
 */ 
@Column(name="yhid")
	protected Integer yhid;

/**
 * 订阅种类代码，参考t_dy_dyzl
 */ 
@Column(name="dyzldm")
	protected String dyzldm;

@Column(name="dyfsdm")
protected String dyfsdm;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

@Column(name="yxbz")
	protected String yxbz;

@Column(name="lrry")
	protected Integer lrry;

@Column(name="xgry")
	protected Integer xgry;

@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getYhid(){
		return yhid;
	}

	public void setYhid(Integer yhid){
		this.yhid=yhid;
	}

	public String getDyzldm(){
		return dyzldm;
	}

	public void setDyzldm(String dyzldm){
		this.dyzldm=dyzldm;
	}

	public String getDyfsdm() {
		return dyfsdm;
	}

	public void setDyfsdm(String dyfsdm) {
		this.dyfsdm = dyfsdm;
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

}

