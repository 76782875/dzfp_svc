package com.rjxx.utils.yjapi;


/**
 * Created by wangyahui on 2017/4/7.
 */
public class Result<T> {
    /*错误信息*/
    private String msg;
    /*错误码*/
    private String code;
    /*数据*/
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
