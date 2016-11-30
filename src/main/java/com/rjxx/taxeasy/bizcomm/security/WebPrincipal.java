package com.rjxx.taxeasy.bizcomm.security;

import com.rjxx.taxeasy.domains.PrivilegeTypes;
import com.rjxx.taxeasy.domains.Privileges;
import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.domains.Xf;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

/**
 * Created by lenovo on 2015/12/18.
 */
public class WebPrincipal implements Principal, Serializable {

    public String name = "WEB-FRAME-PRINCIPAL";

    @Override
    public String getName() {
        return name;
    }

    private String password;

    private String xfmc;

    private String xfsh;

    private int xfid;

    private String dlyhid;

    private String yhmc;

    private String sjhm;

    private String yx;

    private String xb;

    private int yhid;

    private List<Xf> xfList;

    private String gsdm;

    private String sup;

    private List<Privileges> privilegesList;
    
    private List<Skp> skpList;

    private List<PrivilegeTypes> privilegeTypesList;

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDlyhid() {
        return dlyhid;
    }

    public void setDlyhid(String dlyhid) {
        this.dlyhid = dlyhid;
    }

    public int getYhid() {
        return yhid;
    }

    public void setYhid(int yhid) {
        this.yhid = yhid;
    }


    public int getXfid() {
        return xfid;
    }

    public void setXfid(int xfid) {
        this.xfid = xfid;
    }

    public String getXfmc() {
        return xfmc;
    }

    public void setXfmc(String xfmc) {
        this.xfmc = xfmc;
    }

    public String getXfsh() {
        return xfsh;
    }

    public void setXfsh(String xfsh) {
        this.xfsh = xfsh;
    }

    public List<Xf> getXfList() {
        return xfList;
    }

    public void setXfList(List<Xf> xfList) {
        this.xfList = xfList;
    }

    public String getGsdm() {
        return gsdm;
    }

    public void setGsdm(String gsdm) {
        this.gsdm = gsdm;
    }

    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }

    public String getSup() {
        return sup;
    }

    public void setSup(String sup) {
        this.sup = sup;
    }

    public List<Privileges> getPrivilegesList() {
        return privilegesList;
    }

    public void setPrivilegesList(List<Privileges> privilegesList) {
        this.privilegesList = privilegesList;
    }

    public List<PrivilegeTypes> getPrivilegeTypesList() {
        return privilegeTypesList;
    }

    public void setPrivilegeTypesList(List<PrivilegeTypes> privilegeTypesList) {
        this.privilegeTypesList = privilegeTypesList;
    }

	public List<Skp> getSkpList() {
		return skpList;
	}

	public void setSkpList(List<Skp> skpList) {
		this.skpList = skpList;
	}
    
}
