package com.rjxx.taxeasy.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by xlm on 2017/6/7.
 */
public class Jyzfmxvo implements Serializable{


    protected Integer id;


    protected Integer sqlsh;


    protected String ddh;



    protected String zffsDm;


    protected Double zfje;


    protected String gsdm;


    protected Date lrsj;


    protected Integer lrry;


    protected Date xgsj;


    protected Integer xgry;

    protected String zfmc;

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


    public String getDdh() {
        return ddh;
    }

    public void setDdh(String ddh) {
        this.ddh = ddh;
    }

    public String getZffsDm(){
        return zffsDm;
    }

    public void setZffsDm(String zffsDm){
        this.zffsDm=zffsDm;
    }

    public Double getZfje(){
        return zfje;
    }

    public void setZfje(Double zfje){
        this.zfje=zfje;
    }

    public String getGsdm(){
        return gsdm;
    }

    public void setGsdm(String gsdm){
        this.gsdm=gsdm;
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

    public String getZfmc() {
        return zfmc;
    }

    public void setZfmc(String zfmc) {
        this.zfmc = zfmc;
    }
}
