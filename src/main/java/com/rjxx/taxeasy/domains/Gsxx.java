package com.rjxx.taxeasy.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * t_gsxx 实体类
 * 由GenEntityMysql类自动生成
 * Thu Nov 03 17:18:34 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_gsxx")
public class Gsxx  implements Serializable {

/**
 * 公司代码
 */ 
	@Id
	protected String gsdm;

/**
 * 公司名称
 */ 
@Column(name="gsmc")
	protected String gsmc;

/**
 * 公司简称
 */ 
@Column(name="gsjc")
	protected String gsjc;

/**
 * 秘钥
 */ 
@Column(name="secret_key")
	protected String secretKey;

/**
 * 身份认证
 */ 
@Column(name="appkey")
	protected String appKey;

/**
 * 调用税控服务器的url
 */ 
@Column(name="ws_url")
	protected String wsUrl;
/**
 * 微信appid
 */ 
@Column(name="wxappid")
	protected String wxappid;
/**
 * 微信secret
 */ 
@Column(name="wxsecret")
	protected String wxsecret;
@Column(name="xfnum")
protected Integer xfnum;
@Column(name="kpdnum")
protected Integer kpdnum;
@Column(name="yhnum")
protected Integer yhnum;
@Column(name="kpnum")
protected Integer kpnum;
@Column(name="yxqsrq")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date yxqsrq;
@Column(name="yxjzrq")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date yxjzrq;

	/**
	 * 钉钉企业id
	 */
	@Column(name="dingcorpid")
	protected String dingcorpid;

	/**
	 * 回调接口地址
	 */
	@Column(name="callbackurl")
	protected String callbackurl;

	public String getAppKey() {
	return appKey;
}

    public void setAppKey(String appKey) {
	this.appKey = appKey;
}

	@Column(name="yjmb_dm")
	protected Integer yjmbDm;

	@Column(name="messageurl")
	protected String messageurl;

	@Column(name="sapcallbackurl")
	protected String sapcallbackurl;

	public String getWsUrl() {
	return wsUrl;
}

public void setWsUrl(String wsUrl) {
	this.wsUrl = wsUrl;
}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public String getGsmc(){
		return gsmc;
	}

	public void setGsmc(String gsmc){
		this.gsmc=gsmc;
	}

	public String getGsjc(){
		return gsjc;
	}

	public void setGsjc(String gsjc){
		this.gsjc=gsjc;
	}

	public String getSecretKey(){
		return secretKey;
	}

	public void setSecretKey(String secretKey){
		this.secretKey=secretKey;
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public String getWxsecret() {
		return wxsecret;
	}

	public void setWxsecret(String wxsecret) {
		this.wxsecret = wxsecret;
	}

	public Integer getXfnum() {
		return xfnum;
	}

	public void setXfnum(Integer xfnum) {
		this.xfnum = xfnum;
	}

	public Integer getKpdnum() {
		return kpdnum;
	}

	public void setKpdnum(Integer kpdnum) {
		this.kpdnum = kpdnum;
	}

	public Integer getYhnum() {
		return yhnum;
	}

	public void setYhnum(Integer yhnum) {
		this.yhnum = yhnum;
	}

	public Integer getKpnum() {
		return kpnum;
	}

	public void setKpnum(Integer kpnum) {
		this.kpnum = kpnum;
	}

	public Date getYxqsrq() {
		return yxqsrq;
	}
	
	public String getQsrq(){
		if (yxqsrq != null) {
			return new SimpleDateFormat("yyyy年MM月dd日").format(yxqsrq);
		}
		
		return null;
	}

	public void setYxqsrq(Date yxqsrq) {
		this.yxqsrq = yxqsrq;
	}

	public Date getYxjzrq() {
		return yxjzrq;
	}
	
	public String getJzrq(){
		if (yxjzrq != null) {
			return new SimpleDateFormat("yyyy年MM月dd日").format(yxjzrq);
		}
		
		return null;
	}

	public void setYxjzrq(Date yxjzrq) {
		this.yxjzrq = yxjzrq;
	}

	public String getDingcorpid(){
		return dingcorpid;
	}

	public void setDingcorpid(String dingcorpid){
		this.dingcorpid=dingcorpid;
	}

	public Integer getYjmbDm() {
		return yjmbDm;
	}

	public void setYjmbDm(Integer yjmbDm) {
		this.yjmbDm = yjmbDm;
	}

	public String getCallbackurl() {
		return callbackurl;
	}

	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}

	public String getMessageurl() {
		return messageurl;
	}

	public void setMessageurl(String messageurl) {
		this.messageurl = messageurl;
	}

	public String getSapcallbackurl() {
		return sapcallbackurl;
	}

	public void setSapcallbackurl(String sapcallbackurl) {
		this.sapcallbackurl = sapcallbackurl;
	}
}

