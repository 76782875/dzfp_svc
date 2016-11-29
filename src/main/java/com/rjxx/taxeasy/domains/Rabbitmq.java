package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import java.io.Serializable;

/**
 * t_rabbitmq 实体类
 * rabbitmq的信息
 * 由GenEntityMysql类自动生成
 * Sun Oct 09 12:05:27 CST 2016
 *
 * @ZhangBing
 */
@Entity
@Table(name = "t_rabbitmq")
public class Rabbitmq implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * 主机地址
     */
    @Column(name = "host")
    protected String host;

    /**
     * 端口
     */
    @Column(name = "port")
    protected Integer port;

    /**
     * 账号
     */
    @Column(name = "account")
    protected String account;

    /**
     * 密码
     */
    @Column(name = "password")
    protected String password;

    /**
     * 虚拟主机
     */
    @Column(name = "vhost")
    protected String vhost;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVhost() {
        return vhost;
    }

    public void setVhost(String vhost) {
        this.vhost = vhost;
    }

}

