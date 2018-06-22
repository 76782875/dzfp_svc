package com.rjxx.taxeasy.dto.shouqianba;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/4
 */
public class QueryResult {
    //结果码
    private String result_code;
    //错误码
    private String error_code;
    //错误消息
    private String error_message;

    private QueryBizResponse biz_response;

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
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

    public QueryBizResponse getBiz_response() {
        return biz_response;
    }

    public void setBiz_response(QueryBizResponse biz_response) {
        this.biz_response = biz_response;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "result_code='" + result_code + '\'' +
                ", error_code='" + error_code + '\'' +
                ", error_message='" + error_message + '\'' +
                ", biz_response=" + biz_response +
                '}';
    }
}
