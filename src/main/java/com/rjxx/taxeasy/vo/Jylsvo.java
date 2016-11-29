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
 * jylsvo 实体类
 * VIEW
 * 由GenEntityMysql类自动生成
 * Mon Oct 17 13:26:49 GMT+08:00 2016
 * @ZhangBing
 */ 
public class Jylsvo  implements Serializable {

/**
 * 请求单据号 TaxEasy系统自动生成，每次请求唯一。
 */ 
	protected Integer djh;

/**
 * 交易流水号 业务系统或电商平台生成，包括所有需要开票和不需要开票的全部交易流水号。
 */ 
	protected String jylsh;

/**
 * 交易流水时间 业务系统或电商平台生成。
 */ 
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date jylssj;

/**
 * 订单号 业务系统或电商平台生成。
 */ 
	protected String ddh;

/**
 * 发票种类代码 业务系统或电商平台生成，或提供业务规则由TaxEasy生成。代码见t_fpzl。
 */ 
	protected String fpzldm;

/**
 * 发票操作类型代码 业务系统或电商平台生成，或提供业务规则由TaxEasy生成。代码见t_fpczlx。
 */ 
	protected String fpczlxdm;

/**
 * 销方ID
 */ 
	protected Integer xfid;

/**
 * 销方税号
 */ 
	protected String xfsh;

/**
 * 销方名称
 */ 
	protected String xfmc;

/**
 * 销方银行
 */ 
	protected String xfyh;

/**
 * 销方银行账号
 */ 
	protected String xfyhzh;

/**
 * 销方联系人
 */ 
	protected String xflxr;

/**
 * 销方地址
 */ 
	protected String xfdz;

/**
 * 销方电话
 */ 
	protected String xfdh;

/**
 * 销方邮编
 */ 
	protected String xfyb;

/**
 * 购方ID
 */ 
	protected Integer gfid;

/**
 * 购方税号
 */ 
	protected String gfsh;

/**
 * 购方名称
 */ 
	protected String gfmc;

/**
 * 购方银行
 */ 
	protected String gfyh;

/**
 * 购方银行账号
 */ 
	protected String gfyhzh;

/**
 * 购方联系人
 */ 
	protected String gflxr;

/**
 * 购方地址
 */ 
	protected String gfdz;

/**
 * 购方电话
 */ 
	protected String gfdh;

/**
 * 购方邮编
 */ 
	protected String gfyb;

/**
 * 邮箱地址
 */ 
	protected String gfemail;

/**
 * 开具类型(0、纸质开具；1、电子开具；2、部分退货暂不开具；3、部分折扣暂不开具
 */ 
	protected String sffsyj;

/**
 * 电子发票处理状态代码
 */ 
	protected String clztdm;

/**
 * 备注
 */ 
	protected String bz;

/**
 * 收款人
 */ 
	protected String skr;

/**
 * 开票(红冲、作废)人
 */ 
	protected String kpr;

/**
 * 复核人
 */ 
	protected String fhr;

/**
 * 所属月份
 */ 
	protected String ssyf;

/**
 * 专票红字通知单号
 */ 
	protected String hztzdh;

/**
 * 红冲(作废)对应原发票代码
 */ 
	protected String yfpdm;

/**
 * 红冲(作废)对应原发票号码
 */ 
	protected String yfphm;

/**
 * 含税标志 0、不含税；1、含税
 */ 
	protected String hsbz;

/**
 * 价税合计 价税合计
 */ 
	protected Double jshj;

/**
 * 已开票价税合计 已开票价税合计
 */ 
	protected Double ykpjshj;

/**
 * 有效标志 1、有效；0、无效
 */ 
	protected String yxbz;

/**
 * 录入时间 系统时间
 */ 
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 录入人员
 */ 
	protected Integer lrry;

/**
 * 修改时间
 */ 
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

/**
 * 修改人员
 */ 
	protected Integer xgry;

/**
 * 购方收件人
 */ 
	protected String gfsjr;

/**
 * 公司代码
 */ 
	protected String gsdm;

/**
 * 提取码
 */ 
	protected String tqm;

	protected String clztmc;

/**
 * 发票操作类型名称
 */ 
	protected String fpczlxmc;

/**
 * 发票种类名称
 */ 
	protected String fpzlmc;

	protected String newgfmc;
	
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date sqsj;
	
	protected String ckhkyy;
	
	protected Integer sqid;
	
	public Integer getDjh(){
		return djh;
	}

	public void setDjh(Integer djh){
		this.djh=djh;
	}

	public String getJylsh(){
		return jylsh;
	}

	public void setJylsh(String jylsh){
		this.jylsh=jylsh;
	}

	public Date getJylssj(){
		return jylssj;
	}

	public void setJylssj(Date jylssj){
		this.jylssj=jylssj;
	}

	public String getDdh(){
		return ddh;
	}

	public void setDdh(String ddh){
		this.ddh=ddh;
	}

	public String getFpzldm(){
		return fpzldm;
	}

	public void setFpzldm(String fpzldm){
		this.fpzldm=fpzldm;
	}

	public String getFpczlxdm(){
		return fpczlxdm;
	}

	public void setFpczlxdm(String fpczlxdm){
		this.fpczlxdm=fpczlxdm;
	}

	public Integer getXfid(){
		return xfid;
	}

	public void setXfid(Integer xfid){
		this.xfid=xfid;
	}

	public String getXfsh(){
		return xfsh;
	}

	public void setXfsh(String xfsh){
		this.xfsh=xfsh;
	}

	public String getXfmc(){
		return xfmc;
	}

	public void setXfmc(String xfmc){
		this.xfmc=xfmc;
	}

	public String getXfyh(){
		return xfyh;
	}

	public void setXfyh(String xfyh){
		this.xfyh=xfyh;
	}

	public String getXfyhzh(){
		return xfyhzh;
	}

	public void setXfyhzh(String xfyhzh){
		this.xfyhzh=xfyhzh;
	}

	public String getXflxr(){
		return xflxr;
	}

	public void setXflxr(String xflxr){
		this.xflxr=xflxr;
	}

	public String getXfdz(){
		return xfdz;
	}

	public void setXfdz(String xfdz){
		this.xfdz=xfdz;
	}

	public String getXfdh(){
		return xfdh;
	}

	public void setXfdh(String xfdh){
		this.xfdh=xfdh;
	}

	public String getXfyb(){
		return xfyb;
	}

	public void setXfyb(String xfyb){
		this.xfyb=xfyb;
	}

	public Integer getGfid(){
		return gfid;
	}

	public void setGfid(Integer gfid){
		this.gfid=gfid;
	}

	public String getGfsh(){
		return gfsh;
	}

	public void setGfsh(String gfsh){
		this.gfsh=gfsh;
	}

	public String getGfmc(){
		return gfmc;
	}

	public void setGfmc(String gfmc){
		this.gfmc=gfmc;
	}

	public String getGfyh(){
		return gfyh;
	}

	public void setGfyh(String gfyh){
		this.gfyh=gfyh;
	}

	public String getGfyhzh(){
		return gfyhzh;
	}

	public void setGfyhzh(String gfyhzh){
		this.gfyhzh=gfyhzh;
	}

	public String getGflxr(){
		return gflxr;
	}

	public void setGflxr(String gflxr){
		this.gflxr=gflxr;
	}

	public String getGfdz(){
		return gfdz;
	}

	public void setGfdz(String gfdz){
		this.gfdz=gfdz;
	}

	public String getGfdh(){
		return gfdh;
	}

	public void setGfdh(String gfdh){
		this.gfdh=gfdh;
	}

	public String getGfyb(){
		return gfyb;
	}

	public void setGfyb(String gfyb){
		this.gfyb=gfyb;
	}

	public String getGfemail(){
		return gfemail;
	}

	public void setGfemail(String gfemail){
		this.gfemail=gfemail;
	}

	public String getSffsyj(){
		return sffsyj;
	}

	public void setSffsyj(String sffsyj){
		this.sffsyj=sffsyj;
	}

	public String getClztdm(){
		return clztdm;
	}

	public void setClztdm(String clztdm){
		this.clztdm=clztdm;
	}

	public String getBz(){
		return bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getSkr(){
		return skr;
	}

	public void setSkr(String skr){
		this.skr=skr;
	}

	public String getKpr(){
		return kpr;
	}

	public void setKpr(String kpr){
		this.kpr=kpr;
	}

	public String getFhr(){
		return fhr;
	}

	public void setFhr(String fhr){
		this.fhr=fhr;
	}

	public String getSsyf(){
		return ssyf;
	}

	public void setSsyf(String ssyf){
		this.ssyf=ssyf;
	}

	public String getHztzdh(){
		return hztzdh;
	}

	public void setHztzdh(String hztzdh){
		this.hztzdh=hztzdh;
	}

	public String getYfpdm(){
		return yfpdm;
	}

	public void setYfpdm(String yfpdm){
		this.yfpdm=yfpdm;
	}

	public String getYfphm(){
		return yfphm;
	}

	public void setYfphm(String yfphm){
		this.yfphm=yfphm;
	}

	public String getHsbz(){
		return hsbz;
	}

	public void setHsbz(String hsbz){
		this.hsbz=hsbz;
	}

	public Double getJshj(){
		return jshj;
	}

	public void setJshj(Double jshj){
		this.jshj=jshj;
	}

	public Double getYkpjshj(){
		return ykpjshj;
	}

	public void setYkpjshj(Double ykpjshj){
		this.ykpjshj=ykpjshj;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
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

	public String getGfsjr(){
		return gfsjr;
	}

	public void setGfsjr(String gfsjr){
		this.gfsjr=gfsjr;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public String getTqm(){
		return tqm;
	}

	public void setTqm(String tqm){
		this.tqm=tqm;
	}

	public String getClztmc(){
		return clztmc;
	}

	public void setClztmc(String clztmc){
		this.clztmc=clztmc;
	}

	public String getFpczlxmc(){
		return fpczlxmc;
	}

	public void setFpczlxmc(String fpczlxmc){
		this.fpczlxmc=fpczlxmc;
	}

	public String getFpzlmc(){
		return fpzlmc;
	}

	public void setFpzlmc(String fpzlmc){
		this.fpzlmc=fpzlmc;
	}

	public String getNewgfmc() {
		return newgfmc;
	}

	public void setNewgfmc(String newgfmc) {
		this.newgfmc = newgfmc;
	}

	public Date getSqsj() {
		return sqsj;
	}

	public void setSqsj(Date sqsj) {
		this.sqsj = sqsj;
	}

	public String getCkhkyy() {
		return ckhkyy;
	}

	public void setCkhkyy(String ckhkyy) {
		this.ckhkyy = ckhkyy;
	}

	public Integer getSqid() {
		return sqid;
	}

	public void setSqid(Integer sqid) {
		this.sqid = sqid;
	}

}

