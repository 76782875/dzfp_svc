package com.rjxx.taxeasy.vo;


import pdf.AbstractDocumentVo;

import java.sql.Timestamp;
import java.util.List;

/* 待开电子发票主表 */

public class FpPdfInfo extends AbstractDocumentVo {

    private int djh;//单据号 主键 单纯的当作id就行，不易混淆
    //   private String ptdm;//平台代码 外键
      
    private String jylsh;//交易流水号
      
    private Timestamp jylssj;//订单时间
      
    private String ddh;//订单号
      
    private String fpzldm;//发票种类代码 如增值税普通电子发票
      
    private String fpczlxdm;//发票操作类型 如红字 蓝字
      
    private String xfsh;//销方税号
      
    private String xfmc;//名称
      
    private String xfyh;//银行
      
    private String xfyhzh;//银行帐号
      
    private String xflxr;//联系人
      
    private String xfdz;//地址
      
    private String xfdh;//电话
      
    private String xfyb;//邮编
      
    private String gfsh;//购房税号
      
    private String gfmc;
      
    private String gfyh;
      
    private String gfyhzh;
      
    private String gflxr;
      
    private String gfdz;
      
    private String gfdh;
      
    private String gfyb;
      
    private String gfemail;//邮箱地址
      
    private String sffsyj;//是否发送邮件
      
    private String clztdm;//电子发票处理状态代码  等同之前的flag 00需要开票
      
    private String bz;
      
    private String skr;//收款人
      
    private String kpr;//开票人
      
    private String fhr;//复核人
      
    private Double jshj;//jshj - ykpjshj > 0 #未完全开具
      
    private Double ykpjshj;
      
    private String ssyf;//所属月份 格式为201512
      
    private String yfpdm;//原发票代码
      
    private String yfphm;//原发票号码
      
    private String hsbz;//含税标志 1含税
      
    private String yxbz;//有效标志 1有效
      
    private Timestamp lrsj;//录入时间
      
    private int lrry;//录入人员
      
    private Timestamp xgsj;//修改
      
    private int xgry;
      
    private String gsdm;//公司代码

    public FpPdfInfo() {

    }

     @Override
    public String findPrimaryKey() {
        return null;
    }

    /**
     * 以下为表中不存在的字段，添加的，为生成pdf
     */
    private String totalString;//价税合计  pdf格式需要
    private String totalAmountString;
    private String totalTaxAmountString;
    private String iddd;//明细条数
    private String base64Image;//二维码
    private String imagePath;//印章
    private String jqbh;//机器编号
    private String kprq;//开票日期
    private String jym;//校验码
    private String fpmw;//密文
    private String fpmw1;
    private String fpmw2;
    private String fpmw3;
    private String fpmw4;
    private String jshjdx;//价税合计（大写）

    private List<FpPdfMxInfo> jyspmxls;

    public int getDjh() {
        return djh;
    }

    public void setDjh(int djh) {
        this.djh = djh;
    }

    public String getJylsh() {
        return jylsh;
    }

    public void setJylsh(String jylsh) {
        this.jylsh = jylsh;
    }

    public Timestamp getJylssj() {
        return jylssj;
    }

    public void setJylssj(Timestamp jylssj) {
        this.jylssj = jylssj;
    }

    public String getDdh() {
        return ddh;
    }

    public void setDdh(String ddh) {
        this.ddh = ddh;
    }

    public String getFpzldm() {
        return fpzldm;
    }

    public void setFpzldm(String fpzldm) {
        this.fpzldm = fpzldm;
    }

    public String getFpczlxdm() {
        return fpczlxdm;
    }

    public void setFpczlxdm(String fpczlxdm) {
        this.fpczlxdm = fpczlxdm;
    }

    public String getXfsh() {
        return xfsh;
    }

    public void setXfsh(String xfsh) {
        this.xfsh = xfsh;
    }

    public String getXfmc() {
        return xfmc;
    }

    public void setXfmc(String xfmc) {
        this.xfmc = xfmc;
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

    public String getGfsh() {
        return gfsh;
    }

    public void setGfsh(String gfsh) {
        this.gfsh = gfsh;
    }

    public String getGfmc() {
        return gfmc;
    }

    public void setGfmc(String gfmc) {
        this.gfmc = gfmc;
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

    public String getKpr() {
        return kpr;
    }

    public void setKpr(String kpr) {
        this.kpr = kpr;
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

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

    public Timestamp getLrsj() {
        return lrsj;
    }

    public void setLrsj(Timestamp lrsj) {
        this.lrsj = lrsj;
    }

    public int getLrry() {
        return lrry;
    }

    public void setLrry(int lrry) {
        this.lrry = lrry;
    }

    public Timestamp getXgsj() {
        return xgsj;
    }

    public void setXgsj(Timestamp xgsj) {
        this.xgsj = xgsj;
    }

    public int getXgry() {
        return xgry;
    }

    public void setXgry(int xgry) {
        this.xgry = xgry;
    }

    public String getGsdm() {
        return gsdm;
    }

    public void setGsdm(String gsdm) {
        this.gsdm = gsdm;
    }

    public String getIddd() {
        return iddd;
    }

    public void setIddd(String iddd) {
        this.iddd = iddd;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getJqbh() {
        return jqbh;
    }

    public void setJqbh(String jqbh) {
        this.jqbh = jqbh;
    }

    public String getKprq() {
        return kprq;
    }

    public void setKprq(String kprq) {
        this.kprq = kprq;
    }

    public String getJym() {
        return jym;
    }

    public void setJym(String jym) {
        this.jym = jym;
    }

    public String getFpmw() {
        return fpmw;
    }

    public void setFpmw(String fpmw) {
        this.fpmw = fpmw;
    }

    public String getFpmw4() {
        return fpmw4;
    }

    public void setFpmw4(String fpmw4) {
        this.fpmw4 = fpmw4;
    }

    public String getFpmw3() {
        return fpmw3;
    }

    public void setFpmw3(String fpmw3) {
        this.fpmw3 = fpmw3;
    }

    public String getFpmw2() {
        return fpmw2;
    }

    public void setFpmw2(String fpmw2) {
        this.fpmw2 = fpmw2;
    }

    public String getFpmw1() {
        return fpmw1;
    }

    public void setFpmw1(String fpmw1) {
        this.fpmw1 = fpmw1;
    }

    public Double getJshj() {
        return jshj;
    }

    public void setJshj(Double jshj) {
        this.jshj = jshj;
    }

    public Double getYkpjshj() {
        return ykpjshj;
    }

    public void setYkpjshj(Double ykpjshj) {
        this.ykpjshj = ykpjshj;
    }

    public String getTotalString() {
        return totalString;
    }

    public void setTotalString(String totalString) {
        this.totalString = totalString;
    }

    public String getTotalAmountString() {
        return totalAmountString;
    }

    public void setTotalAmountString(String totalAmountString) {
        this.totalAmountString = totalAmountString;
    }

    public String getTotalTaxAmountString() {
        return totalTaxAmountString;
    }

    public void setTotalTaxAmountString(String totalTaxAmountString) {
        this.totalTaxAmountString = totalTaxAmountString;
    }

    public String getJshjdx() {
        return jshjdx;
    }

    public void setJshjdx(String jshjdx) {
        this.jshjdx = jshjdx;
    }

    public String getSffsyj() {
        return sffsyj;
    }

    public void setSffsyj(String sffsyj) {
        this.sffsyj = sffsyj;
    }

    public List<FpPdfMxInfo> getJyspmxls() {
        return jyspmxls;
    }

    public void setJyspmxls(List<FpPdfMxInfo> jyspmxls) {
        this.jyspmxls = jyspmxls;
    }
}
