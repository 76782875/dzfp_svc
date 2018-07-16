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
 * t_short_link 实体类
 * 短链接对照表
 * 由GenEntityMysql类自动生成
 * Wed Jun 27 09:39:01 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_short_link")
public class ShortLink  implements Serializable {

/**
 * 主键id
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 短链接
 */ 
@Column(name="short_link")
	protected String shortLink;

/**
 * 正常链接
 */ 
@Column(name="normal_link")
	protected String normalLink;

/**
 * 类型01开票、
 */ 
@Column(name="type")
	protected String type;

/**
 * 录入人员
 */ 
@Column(name="creator")
	protected String creator;

/**
 * 录入日期
 */ 
@Column(name="create_date")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date createDate;

/**
 * 修改人员
 */ 
@Column(name="modifier")
	protected String modifier;

/**
 * 修改日期
 */ 
@Column(name="modify_date")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date modifyDate;

/**
 * 有效标志
 */ 
@Column(name="use_mark")
	protected String useMark;


	@Column(name="count")
	protected Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getShortLink(){
		return shortLink;
	}

	public void setShortLink(String shortLink){
		this.shortLink=shortLink;
	}

	public String getNormalLink(){
		return normalLink;
	}

	public void setNormalLink(String normalLink){
		this.normalLink=normalLink;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type=type;
	}

	public String getCreator(){
		return creator;
	}

	public void setCreator(String creator){
		this.creator=creator;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate=createDate;
	}

	public String getModifier(){
		return modifier;
	}

	public void setModifier(String modifier){
		this.modifier=modifier;
	}

	public Date getModifyDate(){
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate){
		this.modifyDate=modifyDate;
	}

	public String getUseMark(){
		return useMark;
	}

	public void setUseMark(String useMark){
		this.useMark=useMark;
	}

}

