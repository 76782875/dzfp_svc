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

    private QueryData data;

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

    public QueryData getData() {
        return data;
    }

    public void setData(QueryData data) {
        this.data = data;
    }
}
