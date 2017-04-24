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
 * t_jymxsq 实体类
 * 交易信息明细申请表
 * 由GenEntityMysql类自动生成
 * Thu Apr 20 21:03:07 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_jymxsq")
public class Jymxsq  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 单据号
 */ 
@Column(name="sqlsh")
	protected Integer sqlsh;

/**
 * 订单号 业务系统或电商平台生成。
 */ 
@Column(name="ddh")
	protected String ddh;

/**
 * 开票点代码
 */ 
@Column(name="kpddm")
	protected String kpddm;

/**
 * 含税标志，1含税，0不含税
 */ 
@Column(name="hsbz")
	protected String hsbz;

/**
 * 商品明细序号
 */ 
@Column(name="spmxxh")
	protected Integer spmxxh;

/**
 * 0、正常行；1、折扣行；2、被折扣行
 */ 
@Column(name="fphxz")
	protected String fphxz;

/**
 * 商品代码
 */ 
@Column(name="spdm")
	protected String spdm;

/**
 * 商品名称
 */ 
@Column(name="spmc")
	protected String spmc;

/**
 * 商品规格型号
 */ 
@Column(name="spggxh")
	protected String spggxh;

/**
 * 商品自行编码
 */ 
@Column(name="spzxbm")
	protected String spzxbm;

/**
 * 优惠政策标识
 */ 
@Column(name="yhzcbs")
	protected String yhzcbs;

/**
 * 优惠政策名称
 */ 
@Column(name="yhzcmc")
	protected String yhzcmc;

/**
 * 零税率标志
 */ 
@Column(name="lslbz")
	protected String lslbz;

/**
 * 商品单位
 */ 
@Column(name="spdw")
	protected String spdw;

/**
 * 商品数量
 */ 
@Column(name="sps")
	protected Double sps;

/**
 * 商品单价
 */ 
@Column(name="spdj")
	protected Double spdj;

/**
 * 商品金额
 */ 
@Column(name="spje")
	protected Double spje;

/**
 * 商品税率
 */ 
@Column(name="spsl")
	protected Double spsl;

/**
 * 商品税额
 */ 
@Column(name="spse")
	protected Double spse;

/**
 * 扣除金额
 */ 
@Column(name="kce")
	protected Double kce;

/**
 * 价税合计
 */ 
@Column(name="jshj")
	protected Double jshj;

/**
 * 汇总开票序号
 */ 
@Column(name="hzkpxh")
	protected Integer hzkpxh;

/**
 * 录入时间
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 录入人员
 */ 
@Column(name="lrry")
	protected Integer lrry;

/**
 * 修改时间
 */ 
@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

/**
 * 修改人员
 */ 
@Column(name="xgry")
	protected Integer xgry;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

@Column(name="skpid")
	protected Integer skpid;

@Column(name="xfid")
	protected Integer xfid;

/**
 * 有效标志 1 有效 0 无效
 */ 
@Column(name="yxbz")
	protected String yxbz;

/**
 * 商品id
 */ 
@Column(name="spid")
	protected Integer spid;

@Column(name="ykjje")
	protected Double ykjje;

@Column(name="kkjje")
	protected Double kkjje;


/**
 * 商品备注说明
 */ 
@Column(name="spbz")
	protected String spbz;

    
public Jymxsq() {
    // TODO Auto-generated constructor stub
}

public Jymxsq(Integer spmxxh, String fphxz, String spdm, String spmc, String spggxh, String spdw, Double sps,
		Double spdj, Double spje, Double spsl, Double spse, Double jshj, Integer hzkpxh, Date lrsj,
		Integer lrry, Date xgsj, Integer xgry, String gsdm, Integer xfid, Integer skpid,String yxbz,Double kkjje,Double ykjje) {
	super();
	this.spmxxh = spmxxh;
	this.fphxz = fphxz;
	this.spdm = spdm;
	this.spmc = spmc;
	this.spggxh = spggxh;
	this.spdw = spdw;
	this.sps = sps;
	this.spdj = spdj;
	this.spje = spje;
	this.spsl = spsl;
	this.spse = spse;
	this.jshj = jshj;
	//this.ykphj = ykphj;
	this.hzkpxh = hzkpxh;
	this.lrsj = lrsj;
	this.lrry = lrry;
	this.xgsj = xgsj;
	this.xgry = xgry;
	this.gsdm = gsdm;
	this.xfid = xfid;
	this.skpid = skpid;
	this.yxbz = yxbz;
	this.kkjje = kkjje;
	this.ykjje = ykjje;
	
}
	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getSqlsh(){
		return sqlsh;
	}

	public void setSqlsh(Integer sqlsh){
		this.sqlsh=sqlsh;
	}

	public String getDdh(){
		return ddh;
	}

	public void setDdh(String ddh){
		this.ddh=ddh;
	}

	public String getKpddm(){
		return kpddm;
	}

	public void setKpddm(String kpddm){
		this.kpddm=kpddm;
	}

	public String getHsbz(){
		return hsbz;
	}

	public void setHsbz(String hsbz){
		this.hsbz=hsbz;
	}

	public Integer getSpmxxh(){
		return spmxxh;
	}

	public void setSpmxxh(Integer spmxxh){
		this.spmxxh=spmxxh;
	}

	public String getFphxz(){
		return fphxz;
	}

	public void setFphxz(String fphxz){
		this.fphxz=fphxz;
	}

	public String getSpdm(){
		return spdm;
	}

	public void setSpdm(String spdm){
		this.spdm=spdm;
	}

	public String getSpmc(){
		return spmc;
	}

	public void setSpmc(String spmc){
		this.spmc=spmc;
	}

	public String getSpggxh(){
		return spggxh;
	}

	public void setSpggxh(String spggxh){
		this.spggxh=spggxh;
	}

	public String getSpzxbm(){
		return spzxbm;
	}

	public void setSpzxbm(String spzxbm){
		this.spzxbm=spzxbm;
	}

	public String getYhzcbs(){
		return yhzcbs;
	}

	public void setYhzcbs(String yhzcbs){
		this.yhzcbs=yhzcbs;
	}

	public String getYhzcmc(){
		return yhzcmc;
	}

	public void setYhzcmc(String yhzcmc){
		this.yhzcmc=yhzcmc;
	}

	public String getLslbz(){
		return lslbz;
	}

	public void setLslbz(String lslbz){
		this.lslbz=lslbz;
	}

	public String getSpdw(){
		return spdw;
	}

	public void setSpdw(String spdw){
		this.spdw=spdw;
	}

	public Double getSps(){
		return sps;
	}

	public void setSps(Double sps){
		this.sps=sps;
	}

	public Double getSpdj(){
		return spdj;
	}

	public void setSpdj(Double spdj){
		this.spdj=spdj;
	}

	public Double getSpje(){
		return spje;
	}

	public void setSpje(Double spje){
		this.spje=spje;
	}

	public Double getSpsl(){
		return spsl;
	}

	public void setSpsl(Double spsl){
		this.spsl=spsl;
	}

	public Double getSpse(){
		return spse;
	}

	public void setSpse(Double spse){
		this.spse=spse;
	}

	public Double getKce(){
		return kce;
	}

	public void setKce(Double kce){
		this.kce=kce;
	}

	public Double getJshj(){
		return jshj;
	}

	public void setJshj(Double jshj){
		this.jshj=jshj;
	}

	public Integer getHzkpxh(){
		return hzkpxh;
	}

	public void setHzkpxh(Integer hzkpxh){
		this.hzkpxh=hzkpxh;
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

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public Integer getSkpid(){
		return skpid;
	}

	public void setSkpid(Integer skpid){
		this.skpid=skpid;
	}

	public Integer getXfid(){
		return xfid;
	}

	public void setXfid(Integer xfid){
		this.xfid=xfid;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
	}

	public Integer getSpid(){
		return spid;
	}

	public void setSpid(Integer spid){
		this.spid=spid;
	}

	public Double getYkjje(){
		return ykjje;
	}

	public void setYkjje(Double ykjje){
		this.ykjje=ykjje;
	}

	public Double getKkjje(){
		return kkjje;
	}

	public void setKkjje(Double kkjje){
		this.kkjje=kkjje;
	}

	

	public String getSpbz(){
		return spbz;
	}

	public void setSpbz(String spbz){
		this.spbz=spbz;
	}

}

