package com.rjxx.taxeasy.bizcomm.utils.pdf;

import javax.persistence.Column;
import javax.persistence.Table;

/* 待开电子发票明细表 */
@Table(name = "t_jyspmx")
public class FpPdfMxInfo extends AbstractDocumentVo {
    @Column
    private int djh;//单据号 主键
    @Column
    private int spmxxh;//商品序列号 主键
    @Column
    private String spmc;//商品名称
    @Column
    private String fphxz;//发票行性质
    @Column
    private String spggxh;//商品规格型号
    @Column
    private String spdw;//商品单位
    @Column
    private Double sps;//商品数量
    @Column
    private Double spdj;//商品单价
    @Column
    private Double spje;//金额
    @Column
    private Double spsl;//税率
    @Column
    private Double spse;//税额
    @Column
    private Double jshj;//价税合计

    private int spmcSize;

    private int spggxhSize;

    private int spslSize;

    private int spdjSize;

    public FpPdfMxInfo() {

    }

    private String idd;//明细条数 	商品序列号
    private String sl;//税率
    private String se;//税额
    private String xmsl;//数量
    private String xmdj;//单价
    private String xmje;//金额

    public FpPdfMxInfo(String description, String speType,
                       String unit, String xmsl, String xmdj, String xmje, String sl, String se, String idd) {
        this.spmc = description;
        this.spggxh = speType;
        this.spdw = unit;
        this.xmsl = xmsl;
        this.xmdj = xmdj;
        this.xmje = xmje;
        this.sl = sl;
        this.se = se;
        this.idd = idd;
    }

    @Override
    public String findPrimaryKey() {
        return idd;
    }

    public int getDjh() {
        return djh;
    }

    public void setDjh(int djh) {
        this.djh = djh;
    }

    public int getSpmxxh() {
        return spmxxh;
    }

    public void setSpmxxh(int spmxxh) {
        this.spmxxh = spmxxh;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getSpggxh() {
        return spggxh;
    }

    public void setSpggxh(String spggxh) {
        this.spggxh = spggxh;
    }

    public String getSpdw() {
        return spdw;
    }

    public void setSpdw(String spdw) {
        this.spdw = spdw;
    }

    public Double getSps() {
        return sps;
    }

    public void setSps(Double sps) {
        this.sps = sps;
    }

    public Double getSpdj() {
        return spdj;
    }

    public void setSpdj(Double spdj) {
        this.spdj = spdj;
    }

    public Double getSpje() {
        return spje;
    }

    public void setSpje(Double spje) {
        this.spje = spje;
    }

    public Double getSpsl() {
        return spsl;
    }

    public void setSpsl(Double spsl) {
        this.spsl = spsl;
    }

    public Double getSpse() {
        return spse;
    }

    public void setSpse(Double spse) {
        this.spse = spse;
    }

    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

    public String getXmsl() {
        return xmsl;
    }

    public void setXmsl(String xmsl) {
        this.xmsl = xmsl;
    }

    public String getXmdj() {
        return xmdj;
    }

    public Double getJshj() {
        return jshj;
    }

    public void setJshj(Double jshj) {
        this.jshj = jshj;
    }

    public void setXmdj(String xmdj) {
        this.xmdj = xmdj;
    }

    public String getXmje() {
        return xmje;
    }

    public void setXmje(String xmje) {
        this.xmje = xmje;
    }

    public String getFphxz() {
        return fphxz;
    }

    public void setFphxz(String fphxz) {
        this.fphxz = fphxz;
    }

    public int getSpmcSize() {
        return spmcSize;
    }

    public void setSpmcSize(int spmcSize) {
        this.spmcSize = spmcSize;
    }

    public int getSpggxhSize() {
        return spggxhSize;
    }

    public void setSpggxhSize(int spggxhSize) {
        this.spggxhSize = spggxhSize;
    }

    public int getSpslSize() {
        return spslSize;
    }

    public void setSpslSize(int spslSize) {
        this.spslSize = spslSize;
    }

    public int getSpdjSize() {
        return spdjSize;
    }

    public void setSpdjSize(int spdjSize) {
        this.spdjSize = spdjSize;
    }
}
