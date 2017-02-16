package com.rjxx.taxeasy.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * t_client_login 实体类
 * 客户端登录session
 * 由GenEntityMysql类自动生成
 * Thu Jan 05 14:35:46 CST 2017
 *
 * @ZhangBing
 */
@Entity
@Table(name = "t_client_login")
public class ClientLogin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    protected Integer userId;

    /**
     * 会话id
     */
    @Column(name = "session_id")
    protected String sessionId;

    /**
     * MAC地址
     */
    @Column(name = "mac_addr")
    protected String macAddr;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date loginTime;

    /**
     * 过期日期
     */
    @Column(name = "expire_time")
    @JsonSerialize(using = JsonDateFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date expireTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

}

