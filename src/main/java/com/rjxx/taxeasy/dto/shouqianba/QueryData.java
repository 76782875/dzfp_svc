package com.rjxx.taxeasy.dto.shouqianba;

import java.util.List;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/4
 */
public class QueryData {
    //收钱吧终端ID
    private String terminal_sn;
    //收钱吧唯一订单号
    private String sn;
    //商户订单号
    private String client_sn;
    //流水状态
    private String status;
    //订单状态
    private String order_status;
    //支付方式
    private String payway;
    //支付方式名称
    private String payway_name;
    //付款人id
    private String payer_uid;
    //支付平台的订单凭证号
    private String trade_no;
    //交易总金额
    private String total_amount;
    //剩余金额
    private String net_amount;
    //上次操作在收钱吧的完成时间
    private String finish_time;
    //上次操作再支付平台完成的时间
    private String channel_finish_time;
    //商品概述
    private String subject;
    //操作员
    private String operator;
    //活动优惠
    private List<QueryPayment> payment_list;

    public String getTerminal_sn() {
        return terminal_sn;
    }

    public void setTerminal_sn(String terminal_sn) {
        this.terminal_sn = terminal_sn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getClient_sn() {
        return client_sn;
    }

    public void setClient_sn(String client_sn) {
        this.client_sn = client_sn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getPayway_name() {
        return payway_name;
    }

    public void setPayway_name(String payway_name) {
        this.payway_name = payway_name;
    }

    public String getPayer_uid() {
        return payer_uid;
    }

    public void setPayer_uid(String payer_uid) {
        this.payer_uid = payer_uid;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getNet_amount() {
        return net_amount;
    }

    public void setNet_amount(String net_amount) {
        this.net_amount = net_amount;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    public String getChannel_finish_time() {
        return channel_finish_time;
    }

    public void setChannel_finish_time(String channel_finish_time) {
        this.channel_finish_time = channel_finish_time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<QueryPayment> getPayment_list() {
        return payment_list;
    }

    public void setPayment_list(List<QueryPayment> payment_list) {
        this.payment_list = payment_list;
    }
}
