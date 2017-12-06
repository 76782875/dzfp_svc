package com.rjxx.taxeasy.domains;

import javax.persistence.*;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * t_dlclb 实体类
 * 队列处理表
 * 由GenEntityMysql类自动生成
 * Mon Oct 23 18:15:10 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_dlclb")
public class Dlclb  implements Serializable {

/**
 * id主键
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 队列业务类型
 */ 
@Column(name="dlywlx")
	protected String dlywlx;

/**
 * 队列名称
 */ 
@Column(name="dlmc")
	protected String dlmc;

@Column(name="clff")
    protected String clff;

/**
 * 业务描述
 */ 
@Column(name="ywms")
	protected String ywms;

/**
 * 业务描述
 */
@Column(name="ywkey")
	protected String ywkey;
/**
 * 处理次数
 */ 
@Column(name="clcs")
	protected Integer clcs;

/**
 * 有效标志（1有效，0无效）
 */ 
@Column(name="yxbz")
	protected String yxbz;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getDlywlx(){
		return dlywlx;
	}

	public void setDlywlx(String dlywlx){
		this.dlywlx=dlywlx;
	}

	public String getDlmc(){
		return dlmc;
	}

	public void setDlmc(String dlmc){
		this.dlmc=dlmc;
	}

	public String getYwms(){
		return ywms;
	}

	public void setYwms(String ywms){
		this.ywms=ywms;
	}

	public Integer getClcs(){
		return clcs;
	}

	public void setClcs(Integer clcs){
		this.clcs=clcs;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
	}

	public String getClff() {
		return clff;
	}

	public void setClff(String clff) {
		this.clff = clff;
	}

	public String getYwkey() {
		return ywkey;
	}

	public void setYwkey(String ywkey) {
		this.ywkey = ywkey;
	}
}

