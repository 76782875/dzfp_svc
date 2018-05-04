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
 * t_fpyltj 实体类
 * 发票用量统计表
 * 由GenEntityMysql类自动生成
 * Thu May 03 16:45:51 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_fpyltj")
public class Fpyltj  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 总合计金额
 */ 
@Column(name="zjshj")
	protected Double zjshj;

/**
 * 发票数量
 */ 
@Column(name="fpsl")
	protected Integer fpsl;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

/**
 * 税控盘id
 */ 
@Column(name="skpid")
	protected Integer skpid;

/**
 * 销方id
 */ 
@Column(name="xfid")
	protected Integer xfid;

/**
 * 发票种类代码01、专票 02、普票 12、电票
 */ 
@Column(name="fpzldm")
	protected String fpzldm;

/**
 * 发票状态代码
 */ 
@Column(name="fpztdm")
	protected String fpztdm;

/**
 * 开票日期
 */ 
@Column(name="kprq")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd")
	protected Date kprq;

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

	public Double getZjshj(){
		return zjshj;
	}

	public void setZjshj(Double zjshj){
		this.zjshj=zjshj;
	}

	public Integer getFpsl(){
		return fpsl;
	}

	public void setFpsl(Integer fpsl){
		this.fpsl=fpsl;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public Integer getSkpid(){
		return skpid;
	}

	public void setSkpid(Integer skpid){
		this.skpid=skpid;
	}

	public Integer getXfid(){
		return xfid;
	}

	public void setXfid(Integer xfid){
		this.xfid=xfid;
	}

	public String getFpzldm(){
		return fpzldm;
	}

	public void setFpzldm(String fpzldm){
		this.fpzldm=fpzldm;
	}

	public String getFpztdm(){
		return fpztdm;
	}

	public void setFpztdm(String fpztdm){
		this.fpztdm=fpztdm;
	}

	public Date getKprq(){
		return kprq;
	}

	public void setKprq(Date kprq){
		this.kprq=kprq;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

}

