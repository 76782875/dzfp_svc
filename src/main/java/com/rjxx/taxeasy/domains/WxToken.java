package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017-09-18.
 */
@Entity
@Table(name="wx_token")
public class WxToken implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_time")
    private Date creatTime;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "ticket")
    private String ticket;

    @Column(name = "expires_in")
    private String expiresin;

    @Column(name = "flag")
    private String flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getExpiresin() {
        return expiresin;
    }

    public void setExpiresin(String expiresin) {
        this.expiresin = expiresin;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
