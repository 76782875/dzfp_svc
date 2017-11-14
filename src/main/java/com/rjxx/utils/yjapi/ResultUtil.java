package com.rjxx.utils.yjapi;



/**
 * Created by wangyahui on 2017/4/7.
 */
public class ResultUtil {
    public static Result success(Object data){
        Result result = new Result();
        result.setCode("0000");
        result.setData(data);
        result.setMsg("成功");
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(String msg){
        Result result = new Result();
        result.setCode("9999");
        result.setMsg(msg);
        return result;
    }
}
