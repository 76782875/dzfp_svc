package com.rjxx.taxeasy.domains;

import java.io.Serializable;

public class CorpUserBaseInfo implements Serializable {
    private String deviceId;
    private Boolean is_sys;
    private Integer sys_level;
    private String userid;

    public CorpUserBaseInfo() {
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getIs_sys() {
        return this.is_sys;
    }

    public void setIs_sys(Boolean is_sys) {
        this.is_sys = is_sys;
    }

    public Integer getSys_level() {
        return this.sys_level;
    }

    public void setSys_level(Integer sys_level) {
        this.sys_level = sys_level;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
