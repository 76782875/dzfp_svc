package com.rjxx.taxeasy.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import com.rjxx.taxeasy.domains.Jymxsq;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * t_jymxsq_cl 实体类
 * 交易信息明细申请处理表
 * 由GenEntityMysql类自动生成
 * Wed May 31 17:17:56 CST 2017
 * @ZhangBing
 */
public class JymxsqClVo {

	protected Integer id;

/**
 * 单据号
 */
	protected Integer sqlsh;

/**
 * 订单号 业务系统或电商平台生成。
 */
	protected String ddh;

/**
 * 开票点代码
 */
	protected String kpddm;

/**
 * 含税标志，1含税，0不含税
 */
	protected String hsbz;

/**
 * 商品明细序号
 */
	protected Integer spmxxh;

/**
 * 0、正常行；1、折扣行；2、被折扣行;
 */
	protected String fphxz;

/**
 * 商品代码
 */
	protected String spdm;

/**
 * 商品名称
 */
	protected String spmc;

/**
 * 商品规格型号
 */
	protected String spggxh;

/**
 * 商品自行编码
 */
	protected String spzxbm;

/**
 * 优惠政策标识
 */
	protected String yhzcbs;

/**
 * 优惠政策名称
 */
	protected String yhzcmc;

/**
 * 零税率标志
 */
	protected String lslbz;

/**
 * 商品单位
 */
	protected String spdw;

/**
 * 商品数量
 */
	protected Double sps;

/**
 * 商品单价
 */
	protected Double spdj;

/**
 * 商品金额
 */
	protected Double spje;

/**
 * 商品税率
 */
	protected Double spsl;

/**
 * 商品税额
 */
	protected Double spse;

/**
 * 扣除金额
 */
	protected Double kce;

/**
 * 价税合计
 */
	protected Double jshj;

/**
 * 分摊后的全局折扣金额
 */
protected Double qjzkje;

/**
 * 折扣总金额
 */
protected Double zkzje;

/**
 * 商品行折扣金额（含税，不是折扣单价）
 */
protected Double sphzkje;

/**
 * 分摊全局折扣后的商品行净销售额
 */
protected Double sphjjxse;


/**
 *
 *分摊的支付金额
 */
protected Double ftzfje;

/**
 * 扣除分摊金额后的单价
 */
protected Double fthspdj;


/**
 * 汇总开票序号
 */
	protected Integer hzkpxh;

/**
 * 录入时间
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
 * 公司代码
 */
	protected String gsdm;

	protected Integer skpid;

	protected Integer xfid;

/**
 * 有效标志 1 有效 0 无效
 */
	protected String yxbz;

/**
 * 商品id
 */
	protected Integer spid;

	protected Double ykjje;

	protected Double kkjje;

/**
 * 商品备注说明
 */
	protected String spbz;


    public JymxsqClVo(){

    }

    public JymxsqClVo(Jymxsq jymxsq) {
		super();
		//this.id = id;
		this.sqlsh = jymxsq.getSqlsh();
		this.ddh = jymxsq.getDdh();
		this.kpddm = jymxsq.getKpddm();
		this.hsbz = jymxsq.getHsbz();
		this.spmxxh = jymxsq.getSpmxxh();
		this.fphxz = jymxsq.getFphxz();
		this.spdm = jymxsq.getSpdm();
		this.spmc = jymxsq.getSpmc();
		this.spggxh = jymxsq.getSpggxh();
		//this.sphzkje = jymxsq.getSpje();
		this.spzxbm = jymxsq.getSpzxbm();
		this.yhzcbs = jymxsq.getYhzcbs();
		this.yhzcmc = jymxsq.getYhzcmc();
		this.lslbz = jymxsq.getLslbz();
		this.spdw = jymxsq.getSpdw();
		this.sps = jymxsq.getSps();
		this.spdj = jymxsq.getSpdj();
		this.spje = jymxsq.getSpje();
		this.spsl = jymxsq.getSpsl();
		this.spse = jymxsq.getSpse();
		this.kce = jymxsq.getKce();
		this.jshj = jymxsq.getJshj();
		this.hzkpxh = jymxsq.getHzkpxh();
		this.lrsj = jymxsq.getLrsj();
		this.lrry = jymxsq.getLrry();
		this.xgsj = jymxsq.getXgsj();
		this.xgry = jymxsq.getXgry();
		this.gsdm = jymxsq.getGsdm();
		this.skpid = jymxsq.getSkpid();
		this.xfid = jymxsq.getXfid();
		this.yxbz = jymxsq.getYxbz();
		this.spid = jymxsq.getSpid();
		this.ykjje = jymxsq.getYkjje();
		this.kkjje = jymxsq.getKkjje();
		this.spbz = jymxsq.getSpbz();
	}

	public JymxsqClVo(JymxsqClVo jymxsqClVo) {
		this.id = jymxsqClVo.getId();
		this.sqlsh = jymxsqClVo.getSqlsh();
		this.ddh = jymxsqClVo.getDdh();
		this.kpddm = jymxsqClVo.getKpddm();
		this.hsbz = jymxsqClVo.getHsbz();
		this.spmxxh = jymxsqClVo.getSpmxxh();
		this.fphxz = jymxsqClVo.getFphxz();
		this.spdm = jymxsqClVo.getSpdm();
		this.spmc = jymxsqClVo.getSpmc();
		this.spggxh = jymxsqClVo.getSpggxh();
		this.spzxbm = jymxsqClVo.getSpzxbm();
		this.yhzcbs = jymxsqClVo.getYhzcbs();
		this.yhzcmc = jymxsqClVo.getYhzcmc();
		this.lslbz = jymxsqClVo.getLslbz();
		this.spdw = jymxsqClVo.getSpdw();
		this.sps = jymxsqClVo.getSps();
		this.spdj = jymxsqClVo.getSpdj();
		this.spje = jymxsqClVo.getSpje();
		this.spsl = jymxsqClVo.getSpsl();
		this.spse = jymxsqClVo.getSpse();
		this.kce = jymxsqClVo.getKce();
		this.jshj = jymxsqClVo.getJshj();
		this.qjzkje = jymxsqClVo.getQjzkje();
		this.zkzje = jymxsqClVo.getZkzje();
		this.sphzkje = jymxsqClVo.getSphzkje();
		this.sphjjxse = jymxsqClVo.getSphjjxse();
		this.ftzfje = jymxsqClVo.getFtzfje();
		this.fthspdj = jymxsqClVo.getFthspdj();
		this.hzkpxh = jymxsqClVo.getHzkpxh();
		this.lrsj = jymxsqClVo.getLrsj();
		this.lrry = jymxsqClVo.getLrry();
		this.xgsj = jymxsqClVo.getXgsj();
		this.xgry = jymxsqClVo.getXgry();
		this.gsdm = jymxsqClVo.getGsdm();
		this.skpid = jymxsqClVo.getSkpid();
		this.xfid = jymxsqClVo.getXfid();
		this.yxbz = jymxsqClVo.getYxbz();
		this.spid = jymxsqClVo.getSpid();
		this.ykjje = jymxsqClVo.getYkjje();
		this.kkjje = jymxsqClVo.getKkjje();
		this.spbz = jymxsqClVo.getSpbz();
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

	public Double getQjzkje() {
		return qjzkje;
	}

	public void setQjzkje(Double qjzkje) {
		this.qjzkje = qjzkje;
	}

	public Double getZkzje() {
		return zkzje;
	}

	public void setZkzje(Double zkzje) {
		this.zkzje = zkzje;
	}

	public Double getSphjjxse() {
		return sphjjxse;
	}

	public void setSphjjxse(Double sphjjxse) {
		this.sphjjxse = sphjjxse;
	}

	public Double getFtzfje() {
		return ftzfje;
	}

	public void setFtzfje(Double ftzfje) {
		this.ftzfje = ftzfje;
	}

	public Double getFthspdj() {
		return fthspdj;
	}

	public void setFthspdj(Double fthspdj) {
		this.fthspdj = fthspdj;
	}


	public Double getSphzkje() {
		return sphzkje;
	}

	public void setSphzkje(Double sphzkje) {
		this.sphzkje = sphzkje;
	}

}

