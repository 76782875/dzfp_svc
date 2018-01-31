package com.rjxx.taxeasy.domains.leshui;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by wangyahui on 2018/1/31 0031.
 */
@Entity(name="t_fpplcx_jl")
public class Fpplcxjl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer fplsh;
    private String uniqueid;
    private String kssj;
    private String jssj;
    private String gfsh;
    private String gsdm;
    private Integer djy;
    private Integer mysl;
    private Integer jls;
    private String fpzt;
    private String rzbz;
    private String rzlx;
    private String rzsj;
    private String rtncode;
    private String rtnmsg;

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getRtncode() {
        return rtncode;
    }

    public void setRtncode(String rtncode) {
        this.rtncode = rtncode;
    }

    public String getRtnmsg() {
        return rtnmsg;
    }

    public void setRtnmsg(String rtnmsg) {
        this.rtnmsg = rtnmsg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFplsh() {
        return fplsh;
    }

    public void setFplsh(Integer fplsh) {
        this.fplsh = fplsh;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getGfsh() {
        return gfsh;
    }

    public void setGfsh(String gfsh) {
        this.gfsh = gfsh;
    }

    public String getGsdm() {
        return gsdm;
    }

    public void setGsdm(String gsdm) {
        this.gsdm = gsdm;
    }

    public Integer getDjy() {
        return djy;
    }

    public void setDjy(Integer djy) {
        this.djy = djy;
    }

    public Integer getMysl() {
        return mysl;
    }

    public void setMysl(Integer mysl) {
        this.mysl = mysl;
    }

    public Integer getJls() {
        return jls;
    }

    public void setJls(Integer jls) {
        this.jls = jls;
    }

    public String getFpzt() {
        return fpzt;
    }

    public void setFpzt(String fpzt) {
        this.fpzt = fpzt;
    }

    public String getRzbz() {
        return rzbz;
    }

    public void setRzbz(String rzbz) {
        this.rzbz = rzbz;
    }

    public String getRzlx() {
        return rzlx;
    }

    public void setRzlx(String rzlx) {
        this.rzlx = rzlx;
    }

    public String getRzsj() {
        return rzsj;
    }

    public void setRzsj(String rzsj) {
        this.rzsj = rzsj;
    }
}
