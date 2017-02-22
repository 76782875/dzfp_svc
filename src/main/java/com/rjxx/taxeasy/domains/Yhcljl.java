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
 * t_yhcljl 实体类
 * 由GenEntityMysql类自动生成
 * Wed Feb 22 13:57:57 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_yhcljl")
public class Yhcljl  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

@Column(name="yhid")
	protected Integer yhid;

/**
 * 处理的用例名称
 */ 
@Column(name="ylmc")
	protected String ylmc;

/**
 * 处理日期
 */ 
@Column(name="clrq")
@JsonSerialize(using = JsonDateFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd")
	protected Date clrq;

@Column(name="lrry")
	protected Integer lrry;

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

	public Integer getYhid(){
		return yhid;
	}

	public void setYhid(Integer yhid){
		this.yhid=yhid;
	}

	public String getYlmc(){
		return ylmc;
	}

	public void setYlmc(String ylmc){
		this.ylmc=ylmc;
	}

	public Date getClrq(){
		return clrq;
	}

	public void setClrq(Date clrq){
		this.clrq=clrq;
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

}

