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
 * t_jyzfmx 实体类
 * 交易信息对应的支付信息表
 * 由GenEntityMysql类自动生成
 * Wed May 31 15:25:40 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_jyzfmx")
public class Jyzfmx  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 申请流水号
 */ 
@Column(name="sqlsh")
	protected Integer sqlsh;

/**
 * 订单号
 */ 
@Column(name="ddh")
	protected String ddh;


/**
 * 支付方式代码
 */ 
@Column(name="zffs_dm")
	protected String zffsDm;

/**
 * 对应支付方式所支付金额
 */ 
@Column(name="zfje")
	protected Double zfje;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

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

	/**
	 * 支付序列号
	 */
@Column(name="paynumber")
	protected String paynumber;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getSqlsh(){
		return sqlsh;
	}

	public void setSqlsh(Integer sqlsh){
		this.sqlsh=sqlsh;
	}

	
	public String getDdh() {
		return ddh;
	}

	public void setDdh(String ddh) {
		this.ddh = ddh;
	}

	public String getZffsDm(){
		return zffsDm;
	}

	public void setZffsDm(String zffsDm){
		this.zffsDm=zffsDm;
	}

	public Double getZfje(){
		return zfje;
	}

	public void setZfje(Double zfje){
		this.zfje=zfje;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
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

	public String getPaynumber() {
		return paynumber;
	}

	public void setPaynumber(String paynumber) {
		this.paynumber = paynumber;
	}
}

