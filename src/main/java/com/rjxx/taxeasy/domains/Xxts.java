package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * t_xxts 实体类
 * 由GenEntityMysql类自动生成
 * Tue Dec 27 09:18:59 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_xxts")
public class Xxts  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

@Column(name="xm")
	protected String xm;

@Column(name="yx")
	protected String yx;

@Column(name="yxbz")
	protected String yxbz;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getXm(){
		return xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getYx(){
		return yx;
	}

	public void setYx(String yx){
		this.yx=yx;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
	}

}

