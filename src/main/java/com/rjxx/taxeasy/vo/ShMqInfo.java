package com.rjxx.taxeasy.vo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ShMqInfo {

    
    private String xfsh;

    
    private String host;

    
    private int port;

    
    private String account;

    
    private String password;

    
    private String vhost;

    public String getXfsh() {
        return xfsh;
    }

    public void setXfsh(String xfsh) {
        this.xfsh = xfsh;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
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

    private Connection connection = null;

    /**
     * 获取mq的通道
     *
     * @return
     * @throws Exception
     */
    public Channel getChannel() throws Exception {
        if (connection == null || !connection.isOpen()) {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);
            factory.setPort(port);
            factory.setUsername(account);
            factory.setPassword(password);
            factory.setVirtualHost(vhost);
            connection = factory.newConnection();
        }
        return connection.createChannel();
    }

}
