package com.rjxx.taxeasy.vo;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * t_xt_log 实体类
 * 系统日志表
 * 由GenEntityMysql类自动生成
 * Mon Jan 16 09:14:10 CST 2017
 * @ZhangBing
 */ 
public class XtLogVo  implements Serializable {

/**
 * 主键id
 */ 
	protected Integer id;

/**
 * 操作对象（即节点名称）
 */ 

	protected String anctionobj;

/**
 * 操作类型
 */ 

	protected String description;

/**
 * 详细数据
 */ 

	protected String details;

/**
 * 操作对应的java方法
 */ 

	protected String method;

/**
 * 请求ip
 */ 

	protected String requestip;

/**
 * 操作人员id(即用户id)
 */ 

	protected String operator;


protected String gsdm;


/**
 * 操作时间
 */ 
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date time;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getAnctionobj(){
		return anctionobj;
	}

	public void setAnctionobj(String anctionobj){
		this.anctionobj=anctionobj;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description=description;
	}

	public String getDetails(){
		return details;
	}

	public void setDetails(String details){
		this.details=details;
	}

	public String getMethod(){
		return method;
	}

	public void setMethod(String method){
		this.method=method;
	}

	public String getRequestip(){
		return requestip;
	}

	public void setRequestip(String requestip){
		this.requestip=requestip;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time=time;
	}

	public String getGsdm() {
		return gsdm;
	}

	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}

}

