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
 * t_pldrjl 实体类
 * 由GenEntityMysql类自动生成
 * Thu Aug 03 10:18:06 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_pldrjl")
public class Pldrjl  implements Serializable {

/**
 * 序号
 */ 
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer xh;

/**
 * 导入批次号（交易流水号）
 */ 
@Column(name="jylsh")
	protected String jylsh;

/**
 * 导入批次号（交易流水号）
 */ 
@Column(name="drwjm")
	protected String drwjm;

/**
 * 导入时间（录入时间）
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 导入记录条数
 */ 
@Column(name="jlts")
	protected Integer jlts;

/**
 * 记录总价税合计
 */ 
@Column(name="jshj")
	protected Double jshj;

/**
 * 销方id
 */ 
@Column(name="xfid")
	protected Integer xfid;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

/**
 * 状态标志（0未处理，1已处理）
 */ 
@Column(name="ztbz")
	protected String ztbz;


	public Integer getXh(){
		return xh;
	}

	public void setXh(Integer xh){
		this.xh=xh;
	}

	public String getJylsh(){
		return jylsh;
	}

	public void setJylsh(String jylsh){
		this.jylsh=jylsh;
	}
	
	public String getDrwjm() {
		return drwjm;
	}

	public void setDrwjm(String drwjm) {
		this.drwjm = drwjm;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public Integer getJlts(){
		return jlts;
	}

	public void setJlts(Integer jlts){
		this.jlts=jlts;
	}

	public Double getJshj(){
		return jshj;
	}

	public void setJshj(Double jshj){
		this.jshj=jshj;
	}

	public Integer getXfid(){
		return xfid;
	}

	public void setXfid(Integer xfid){
		this.xfid=xfid;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public String getZtbz(){
		return ztbz;
	}

	public void setZtbz(String ztbz){
		this.ztbz=ztbz;
	}

}

