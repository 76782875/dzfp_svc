package com.rjxx.taxeasy.domains.leshui;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by wangyahui on 2018/1/31 0031.
 */
@Entity(name="t_fprz_jl")
public class Fprzjl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer fplsh;
    private String batchid;
    private String resultcode;
    private String resultmsg;
    private String returncode;
    private String returnmsg;

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

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getResultmsg() {
        return resultmsg;
    }

    public void setResultmsg(String resultmsg) {
        this.resultmsg = resultmsg;
    }

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public String getReturnmsg() {
        return returnmsg;
    }

    public void setReturnmsg(String returnmsg) {
        this.returnmsg = returnmsg;
    }
}
