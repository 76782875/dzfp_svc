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
 * t_yjjl 实体类
 * 由GenEntityMysql类自动生成
 * Tue Dec 20 12:03:57 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_yjjl")
public class Yjjl  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 收件人邮箱
 */ 
@Column(name="sjryx")
	protected String sjryx;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

/**
 * 邮件类型
 */ 
@Column(name="type")
	protected String type;

@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 引用id(djh)
 */ 
@Column(name="ref_id")
	protected String refId;

/**
 * 接口返回
 */ 
@Column(name="returnid")
	protected String returnid;

/**
 * 邮件内容
 */ 
@Column(name="yjnr")
	protected String yjnr;

/**
 * 邮件标题
 */ 
@Column(name="yjbt")
	protected String yjbt;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getSjryx(){
		return sjryx;
	}

	public void setSjryx(String sjryx){
		this.sjryx=sjryx;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type=type;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public String getRefId(){
		return refId;
	}

	public void setRefId(String refId){
		this.refId=refId;
	}

	public String getReturnid(){
		return returnid;
	}

	public void setReturnid(String returnid){
		this.returnid=returnid;
	}

	public String getYjnr(){
		return yjnr;
	}

	public void setYjnr(String yjnr){
		this.yjnr=yjnr;
	}

	public String getYjbt(){
		return yjbt;
	}

	public void setYjbt(String yjbt){
		this.yjbt=yjbt;
	}

}

