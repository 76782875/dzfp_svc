package com.rjxx.taxeasy.domains.leshui;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
/**
 * Created by wangyahui on 2018/1/25 0025
 */
@Entity(name = "t_fpcy_mx")
public class Fpcymx {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer fpcyid;
    private Integer spmxxh;
    private String spmc;
    private String spdw;
    private String spggxh;
    private BigDecimal sps;
    private BigDecimal spdj;
    private BigDecimal spje;
    private BigDecimal spsl;
    private BigDecimal spse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFpcyid() {
        return fpcyid;
    }

    public void setFpcyid(Integer fpcyid) {
        this.fpcyid = fpcyid;
    }

    public Integer getSpmxxh() {
        return spmxxh;
    }

    public void setSpmxxh(Integer spmxxh) {
        this.spmxxh = spmxxh;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getSpdw() {
        return spdw;
    }

    public void setSpdw(String spdw) {
        this.spdw = spdw;
    }

    public String getSpggxh() {
        return spggxh;
    }

    public void setSpggxh(String spggxh) {
        this.spggxh = spggxh;
    }

    public BigDecimal getSps() {
        return sps;
    }

    public void setSps(BigDecimal sps) {
        this.sps = sps;
    }

    public BigDecimal getSpdj() {
        return spdj;
    }

    public void setSpdj(BigDecimal spdj) {
        this.spdj = spdj;
    }

    public BigDecimal getSpje() {
        return spje;
    }

    public void setSpje(BigDecimal spje) {
        this.spje = spje;
    }

    public BigDecimal getSpsl() {
        return spsl;
    }

    public void setSpsl(BigDecimal spsl) {
        this.spsl = spsl;
    }

    public BigDecimal getSpse() {
        return spse;
    }

    public void setSpse(BigDecimal spse) {
        this.spse = spse;
    }
}
