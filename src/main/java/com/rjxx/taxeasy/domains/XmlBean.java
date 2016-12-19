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
 * t_xml 实体类
 * 电商平台传入参数表
 * 由GenEntityMysql类自动生成
 * Mon Dec 19 17:54:30 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_xml")
public class XmlBean  implements Serializable {

/**
 * ID
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 销方税号
 */ 
@Column(name="xfsh")
	protected String xfsh;

/**
 * 交易流水号
 */ 
@Column(name="jylsh")
	protected String jylsh;

/**
 * xml参数
 */ 
@Column(name="xml_file")
	protected String xmlFile;

/**
 * 接收时间
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getXfsh(){
		return xfsh;
	}

	public void setXfsh(String xfsh){
		this.xfsh=xfsh;
	}

	public String getJylsh(){
		return jylsh;
	}

	public void setJylsh(String jylsh){
		this.jylsh=jylsh;
	}

	public String getXmlFile(){
		return xmlFile;
	}

	public void setXmlFile(String xmlFile){
		this.xmlFile=xmlFile;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

}

