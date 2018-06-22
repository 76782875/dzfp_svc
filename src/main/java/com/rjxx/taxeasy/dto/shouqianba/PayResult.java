package com.rjxx.taxeasy.dto.shouqianba;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/4
 */
public class PayResult {
    //成功标识
    private String is_success;
    //错误码
    private String error_code;
    //错误描述
    private String error_message;
    //收钱吧终端ID
    private String terminal_sn;
    //收钱吧唯一订单号
    private String sn;
    //支付服务商订单号
    private String trade_no;
    //商户系统订单号
    private String client_sn;
    //支付状态
    private String status;
    //业务错误码
    private String result_code;
    //业务错误描述
    private String result_message;
    //交易总金额
    private String total_amount;
    //交易概述
    private String subject;
    //门店操作员
    private String operator;
    //反射参数
    private String reflect;
    //签名
    private String sign;

    public String getIs_success() {
        return is_success;
    }

    public void setIs_success(String is_success) {
        this.is_success = is_success;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

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

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
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

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
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

    public String getReflect() {
        return reflect;
    }

    public void setReflect(String reflect) {
        this.reflect = reflect;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "PayResult{" +
                "is_success='" + is_success + '\'' +
                ", error_code='" + error_code + '\'' +
                ", error_message='" + error_message + '\'' +
                ", terminal_sn='" + terminal_sn + '\'' +
                ", sn='" + sn + '\'' +
                ", trade_no='" + trade_no + '\'' +
                ", client_sn='" + client_sn + '\'' +
                ", status='" + status + '\'' +
                ", result_code='" + result_code + '\'' +
                ", result_message='" + result_message + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", subject='" + subject + '\'' +
                ", operator='" + operator + '\'' +
                ", reflect='" + reflect + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
