package com.rjxx.taxeasy.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

public class Fpcxvo implements Serializable {
	protected Integer kplsh;
	protected Integer djh;
	protected String jylsh;
	protected String fpzlmc;
	protected String fpczlxdm;
	protected String fpczlxmc;
	protected String fpdm;
	protected String fphm;
	protected Integer xfid;
	protected String xfsh;
	protected String xfmc;
	protected Integer gfid;
	protected String gfsh;
	protected String gfmc;
	protected String hzyfpdm;
	protected String hzyfphm;
	protected String pdfurl;
	protected String lrsj;
	protected String kprq;
	protected String kpr;
	protected Double jshj;
	protected Double hjje;
	protected Double hjse;
	protected String printflag;
	protected String spmc;
	protected Double spje;
	protected Double spse;
	protected String fpzt;
	protected String ddh;
	protected String sfdy;
	protected Integer xcyf;
	protected String hkbz;
	protected String errorReason;
	protected String sqsj;
	protected String ckhkyy;
	protected int sqid;
	protected String ckztbz;
	protected String hkztbz;
	protected String newgfmc;
	protected String fpztdm;
	protected String kpddm;
	protected String kpcount;
	protected String serialorder;
	protected String price;
	protected String khh;
	protected String kprq2;
	protected String orderNo;
	protected String kplsh2;
	protected String filename;


	/**
	 * 交易流水时间 业务系统或电商平台生成。
	 */
	@Column(name = "jylssj")
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date jylssj;

	/**
	 * 发票种类代码 业务系统或电商平台生成，或提供业务规则由TaxEasy生成。代码见t_fpzl。
	 */
	@Column(name = "fpzldm")
	protected String fpzldm;

	/**
	 * 销方银行
	 */
	@Column(name = "xfyh")
	protected String xfyh;

	/**
	 * 销方银行账号
	 */
	@Column(name = "xfyhzh")
	protected String xfyhzh;

	/**
	 * 销方联系人
	 */
	@Column(name = "xflxr")
	protected String xflxr;

	/**
	 * 销方地址
	 */
	@Column(name = "xfdz")
	protected String xfdz;

	/**
	 * 销方电话
	 */
	@Column(name = "xfdh")
	protected String xfdh;

	/**
	 * 销方邮编
	 */
	@Column(name = "xfyb")
	protected String xfyb;

	/**
	 * 购方银行
	 */
	@Column(name = "gfyh")
	protected String gfyh;

	/**
	 * 购方银行账号
	 */
	@Column(name = "gfyhzh")
	protected String gfyhzh;

	/**
	 * 购方联系人
	 */
	@Column(name = "gflxr")
	protected String gflxr;

	/**
	 * 购方地址
	 */
	@Column(name = "gfdz")
	protected String gfdz;

	/**
	 * 购方电话
	 */
	@Column(name = "gfdh")
	protected String gfdh;

	/**
	 * 购方手机号
	 */
	@Column(name = "gfsjh")
	protected String gfsjh;

	/**
	 * 购方邮编
	 */
	@Column(name = "gfyb")
	protected String gfyb;

	/**
	 * 邮箱地址
	 */
	@Column(name = "gfemail")
	protected String gfemail;

	/**
	 * 开具类型(0、纸质开具；1、电子开具；2、部分退货暂不开具；3、部分折扣暂不开具
	 */
	@Column(name = "sffsyj")
	protected String sffsyj;

	/**
	 * 电子发票处理状态代码
	 */
	@Column(name = "clztdm")
	protected String clztdm;

	/**
	 * 备注
	 */
	@Column(name = "bz")
	protected String bz;

	/**
	 * 收款人
	 */
	@Column(name = "skr")
	protected String skr;

	/**
	 * 复核人
	 */
	@Column(name = "fhr")
	protected String fhr;

	/**
	 * 所属月份
	 */
	@Column(name = "ssyf")
	protected String ssyf;

	/**
	 * 专票红字通知单号
	 */
	@Column(name = "hztzdh")
	protected String hztzdh;

	/**
	 * 红冲(作废)对应原发票代码
	 */
	@Column(name = "yfpdm")
	protected String yfpdm;

	/**
	 * 红冲(作废)对应原发票号码
	 */
	@Column(name = "yfphm")
	protected String yfphm;

	/**
	 * 含税标志 0、不含税；1、含税
	 */
	@Column(name = "hsbz")
	protected String hsbz;

	/**
	 * 已开票价税合计 已开票价税合计
	 */
	@Column(name = "ykpjshj")
	protected Double ykpjshj;

	/**
	 * 有效标志 1、有效；0、无效
	 */
	@Column(name = "yxbz")
	protected String yxbz;

	/**
	 * 录入人员
	 */
	@Column(name = "lrry")
	protected Integer lrry;

	/**
	 * 修改时间
	 */
	@Column(name = "xgsj")
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

	/**
	 * 修改人员
	 */
	@Column(name = "xgry")
	protected Integer xgry;

	/**
	 * 购方收件人
	 */
	@Column(name = "gfsjr")
	protected String gfsjr;

	/**
	 * 公司代码
	 */
	@Column(name = "gsdm")
	protected String gsdm;

	/**
	 * 提取码
	 */
	@Column(name = "tqm")
	protected String tqm;

	@Column(name = "skpid")
	protected Integer skpid;
	protected String kpdmc;

	protected String gfsjrdz;

	public Integer getKplsh() {
		return kplsh;
	}

	public Integer getDjh() {
		return djh;
	}

	public String getJylsh() {
		return jylsh;
	}

	public String getFpzlmc() {
		return fpzlmc;
	}

	public String getFpczlxdm() {
		return fpczlxdm;
	}

	public String getFpdm() {
		return fpdm;
	}

	public String getFphm() {
		return fphm;
	}

	public Integer getXfid() {
		return xfid;
	}

	public String getXfsh() {
		return xfsh;
	}

	public String getXfmc() {
		return xfmc;
	}

	public Integer getGfid() {
		return gfid;
	}

	public String getGfsh() {
		return gfsh;
	}

	public String getGfmc() {
		return gfmc;
	}

	public String getHzyfpdm() {
		return hzyfpdm;
	}

	public String getHzyfphm() {
		return hzyfphm;
	}

	public String getPdfurl() {
		return pdfurl;
	}

	public String getLrsj() {
		return lrsj;
	}

	public Double getJshj() {
		return jshj;
	}

	public String getPrintflag() {
		return printflag;
	}

	public String getSpmc() {
		return spmc;
	}

	public String getFpzt() {
		return fpzt;
	}

	public void setKplsh(Integer kplsh) {
		this.kplsh = kplsh;
	}

	public void setDjh(Integer djh) {
		this.djh = djh;
	}

	public void setJylsh(String jylsh) {
		this.jylsh = jylsh;
	}

	public void setFpzlmc(String fpzlmc) {
		this.fpzlmc = fpzlmc;
	}

	public void setFpczlxdm(String fpczlxdm) {
		this.fpczlxdm = fpczlxdm;
	}

	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}

	public void setFphm(String fphm) {
		this.fphm = fphm;
	}

	public void setXfid(Integer xfid) {
		this.xfid = xfid;
	}

	public void setXfsh(String xfsh) {
		this.xfsh = xfsh;
	}

	public void setXfmc(String xfmc) {
		this.xfmc = xfmc;
	}

	public void setGfid(Integer gfid) {
		this.gfid = gfid;
	}

	public void setGfsh(String gfsh) {
		this.gfsh = gfsh;
	}

	public void setGfmc(String gfmc) {
		this.gfmc = gfmc;
	}

	public void setHzyfpdm(String hzyfpdm) {
		this.hzyfpdm = hzyfpdm;
	}

	public void setHzyfphm(String hzyfphm) {
		this.hzyfphm = hzyfphm;
	}

	public void setPdfurl(String pdfurl) {
		this.pdfurl = pdfurl;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public String getKpr() {
		return kpr;
	}

	public void setKpr(String kpr) {
		this.kpr = kpr;
	}

	public void setJshj(Double jshj) {
		this.jshj = jshj;
	}

	public Double getHjje() {

		return hjje;
	}

	public void setHjje(Double hjje) {
		this.hjje = hjje;
	}

	public Double getHjse() {

		return hjse;
	}

	public void setHjse(Double hjse) {
		this.hjse = hjse;
	}

	public void setPrintflag(String printflag) {
		this.printflag = printflag;
	}

	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}

	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}

	public String getDdh() {
		return ddh;
	}

	public void setDdh(String ddh) {
		this.ddh = ddh;
	}

	public String getSfdy() {
		return sfdy;
	}

	public void setSfdy(String sfdy) {
		this.sfdy = sfdy;
	}

	public String getFpczlxmc() {
		return fpczlxmc;
	}

	public void setFpczlxmc(String fpczlxmc) {
		this.fpczlxmc = fpczlxmc;
	}

	public Integer getXcyf() {
		return xcyf;
	}

	public void setXcyf(Integer xcyf) {
		this.xcyf = xcyf;
	}

	public String getHkbz() {
		return hkbz;
	}

	public void setHkbz(String hkbz) {
		this.hkbz = hkbz;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		if (errorReason == null) {
			this.errorReason = "";
		} else {
			this.errorReason = errorReason;
		}

	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getCkhkyy() {
		return ckhkyy;
	}

	public void setCkhkyy(String ckhkyy) {
		if (ckhkyy == null) {
			this.ckhkyy = "";
		} else {
			this.ckhkyy = ckhkyy;
		}

	}

	public int getSqid() {
		return sqid;
	}

	public void setSqid(int sqid) {
		this.sqid = sqid;
	}

	public String getCkztbz() {
		return ckztbz;
	}

	public void setCkztbz(String ckztbz) {
		this.ckztbz = ckztbz;
	}

	public String getHkztbz() {
		return hkztbz;
	}

	public void setHkztbz(String hkztbz) {
		this.hkztbz = hkztbz;
	}

	public String getNewgfmc() {
		return newgfmc;
	}

	public void setNewgfmc(String newgfmc) {
		this.newgfmc = newgfmc;
	}

	public String getKprq() {
		if (kprq != null) {
			return kprq.substring(0, 10);
		} else {
			return null;
		}

	}

	public void setKprq(String kprq) {
		if (kprq != null) {
			this.kprq = kprq.substring(0, 10);
		} else {
			this.kprq = null;
		}

	}

	public String getFpztdm() {
		return fpztdm;
	}

	public void setFpztdm(String fpztdm) {
		this.fpztdm = fpztdm;
	}

	public Date getJylssj() {
		return jylssj;
	}

	public void setJylssj(Date jylssj) {
		this.jylssj = jylssj;
	}

	public String getFpzldm() {
		return fpzldm;
	}

	public void setFpzldm(String fpzldm) {
		this.fpzldm = fpzldm;
	}

	public String getXfyh() {
		return xfyh;
	}

	public void setXfyh(String xfyh) {
		this.xfyh = xfyh;
	}

	public String getXfyhzh() {
		return xfyhzh;
	}

	public void setXfyhzh(String xfyhzh) {
		this.xfyhzh = xfyhzh;
	}

	public String getXflxr() {
		return xflxr;
	}

	public void setXflxr(String xflxr) {
		this.xflxr = xflxr;
	}

	public String getXfdz() {
		return xfdz;
	}

	public void setXfdz(String xfdz) {
		this.xfdz = xfdz;
	}

	public String getXfdh() {
		return xfdh;
	}

	public void setXfdh(String xfdh) {
		this.xfdh = xfdh;
	}

	public String getXfyb() {
		return xfyb;
	}

	public void setXfyb(String xfyb) {
		this.xfyb = xfyb;
	}

	public String getGfyh() {
		return gfyh;
	}

	public void setGfyh(String gfyh) {
		this.gfyh = gfyh;
	}

	public String getGfyhzh() {
		return gfyhzh;
	}

	public void setGfyhzh(String gfyhzh) {
		this.gfyhzh = gfyhzh;
	}

	public String getGflxr() {
		return gflxr;
	}

	public void setGflxr(String gflxr) {
		this.gflxr = gflxr;
	}

	public String getGfdz() {
		return gfdz;
	}

	public void setGfdz(String gfdz) {
		this.gfdz = gfdz;
	}

	public String getGfdh() {
		return gfdh;
	}

	public void setGfdh(String gfdh) {
		this.gfdh = gfdh;
	}

	public String getGfsjh() {
		return gfsjh;
	}

	public void setGfsjh(String gfsjh) {
		this.gfsjh = gfsjh;
	}

	public String getGfyb() {
		return gfyb;
	}

	public void setGfyb(String gfyb) {
		this.gfyb = gfyb;
	}

	public String getGfemail() {
		return gfemail;
	}

	public void setGfemail(String gfemail) {
		this.gfemail = gfemail;
	}

	public String getSffsyj() {
		return sffsyj;
	}

	public void setSffsyj(String sffsyj) {
		this.sffsyj = sffsyj;
	}

	public String getClztdm() {
		return clztdm;
	}

	public void setClztdm(String clztdm) {
		this.clztdm = clztdm;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSkr() {
		return skr;
	}

	public void setSkr(String skr) {
		this.skr = skr;
	}

	public String getFhr() {
		return fhr;
	}

	public void setFhr(String fhr) {
		this.fhr = fhr;
	}

	public String getSsyf() {
		return ssyf;
	}

	public void setSsyf(String ssyf) {
		this.ssyf = ssyf;
	}

	public String getHztzdh() {
		return hztzdh;
	}

	public void setHztzdh(String hztzdh) {
		this.hztzdh = hztzdh;
	}

	public String getYfpdm() {
		return yfpdm;
	}

	public void setYfpdm(String yfpdm) {
		this.yfpdm = yfpdm;
	}

	public String getYfphm() {
		return yfphm;
	}

	public void setYfphm(String yfphm) {
		this.yfphm = yfphm;
	}

	public String getHsbz() {
		return hsbz;
	}

	public void setHsbz(String hsbz) {
		this.hsbz = hsbz;
	}

	public Double getYkpjshj() {
		return ykpjshj;
	}

	public void setYkpjshj(Double ykpjshj) {
		this.ykpjshj = ykpjshj;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public Integer getLrry() {
		return lrry;
	}

	public void setLrry(Integer lrry) {
		this.lrry = lrry;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public Integer getXgry() {
		return xgry;
	}

	public void setXgry(Integer xgry) {
		this.xgry = xgry;
	}

	public String getGfsjr() {
		return gfsjr;
	}

	public void setGfsjr(String gfsjr) {
		this.gfsjr = gfsjr;
	}

	public String getGsdm() {
		return gsdm;
	}

	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}

	public String getTqm() {
		return tqm;
	}

	public void setTqm(String tqm) {
		this.tqm = tqm;
	}

	public Integer getSkpid() {
		return skpid;
	}

	public void setSkpid(Integer skpid) {
		this.skpid = skpid;
	}

	public String getKpdmc() {
		return kpdmc;
	}

	public void setKpdmc(String kpdmc) {
		this.kpdmc = kpdmc;
	}

	public String getKpddm() {
		return kpddm;
	}

	public void setKpddm(String kpddm) {
		this.kpddm = kpddm;
	}

	public String getKpcount() {
		return kpcount;
	}

	public void setKpcount(String kpcount) {
		this.kpcount = kpcount;
	}

	public Double getSpje() {
		return spje;
	}

	public void setSpje(Double spje) {
		this.spje = spje;
	}

	public Double getSpse() {
		return spse;
	}

	public void setSpse(Double spse) {
		this.spse = spse;
	}

	public String getGfsjrdz() {
		return gfsjrdz;
	}

	public void setGfsjrdz(String gfsjrdz) {
		this.gfsjrdz = gfsjrdz;
	}

	public String getSerialorder() {
		return serialorder;
	}

	public void setSerialorder(String serialorder) {
		this.serialorder = serialorder;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getKhh() {
		return khh;
	}

	public void setKhh(String khh) {
		this.khh = khh;
	}

	public String getKprq2() {
		return kprq2;
	}

	public void setKprq2(String kprq2) {
		this.kprq2 = kprq2;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getKplsh2() {
		return kplsh2;
	}

	public void setKplsh2(String kplsh2) {
		this.kplsh2 = kplsh2;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
