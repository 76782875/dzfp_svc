package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * t_xsqd 实体类
 * 销售渠道表
 * 由GenEntityMysql类自动生成
 * Tue Jul 03 16:36:18 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_xsqd")
public class Xsqd  implements Serializable {

/**
 * id
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 订单类型：1、天猫； 2、京东； 3、KA（大客户）；4、员工内购 5、公司间交易 ；6、6折示范机销售； 7、1折示范机销售；8、官网；9、官网；10、维修订单；11、商场订单；12、SA个人；13、SA公司；14、其它
 */ 
@Column(name="ordertype")
	protected String ordertype;

/**
 * 订单类型名称
 */ 
@Column(name="ordertypename")
	protected String ordertypename;

/**
 * 分销渠道 01：电商 、 02：零售-商场 、Z1:内部公司间销售 、Z2:官网 、Z3:SA 、Z4:服务维修、 Z5:零售自营店
 */ 
@Column(name="channel")
	protected String channel;

/**
 * 分销渠道名称
 */ 
@Column(name="channelname")
	protected String channelname;

/**
 * 销售平台 1：天猫、2：京东、3：KA客户、4：Staff Sales、5：无、 6：Demo Sales、7：官网、8：商场、9：SA个人、10：SA公司
 */ 
@Column(name="platform")
	protected String platform;

/**
 * 销售平台名称
 */ 
@Column(name="platformname")
	protected String platformname;

/**
 * 是否回写 1：是、  0：否
 */ 
@Column(name="iscall")
	protected String iscall;

/**
 * 是否发送短信 1：是 、 0：否
 */ 
@Column(name="issend")
	protected String issend;

/**
 * 是否接收数据 1:是、0：否
 */ 
@Column(name="isreceivedata")
	protected String isreceivedata;

/**
 * 有效标志
 */ 
@Column(name="use_mark")
	protected String useMark;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getOrdertype(){
		return ordertype;
	}

	public void setOrdertype(String ordertype){
		this.ordertype=ordertype;
	}

	public String getOrdertypename(){
		return ordertypename;
	}

	public void setOrdertypename(String ordertypename){
		this.ordertypename=ordertypename;
	}

	public String getChannel(){
		return channel;
	}

	public void setChannel(String channel){
		this.channel=channel;
	}

	public String getChannelname(){
		return channelname;
	}

	public void setChannelname(String channelname){
		this.channelname=channelname;
	}

	public String getPlatform(){
		return platform;
	}

	public void setPlatform(String platform){
		this.platform=platform;
	}

	public String getPlatformname(){
		return platformname;
	}

	public void setPlatformname(String platformname){
		this.platformname=platformname;
	}

	public String getIscall(){
		return iscall;
	}

	public void setIscall(String iscall){
		this.iscall=iscall;
	}

	public String getIssend(){
		return issend;
	}

	public void setIssend(String issend){
		this.issend=issend;
	}

	public String getIsreceivedata(){
		return isreceivedata;
	}

	public void setIsreceivedata(String isreceivedata){
		this.isreceivedata=isreceivedata;
	}

	public String getUseMark(){
		return useMark;
	}

	public void setUseMark(String useMark){
		this.useMark=useMark;
	}

}

