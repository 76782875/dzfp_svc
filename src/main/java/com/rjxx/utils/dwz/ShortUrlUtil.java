package com.rjxx.utils.dwz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.utils.weixin.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyahui on 2017/12/18 0018.
 * 短网址URL
 * 因为是免费的且是路人自制，服务稳定性与持久性不敢保证
 */
public class ShortUrlUtil {
    public static String dwz(String longUrl) {
        Map param = new HashMap<>();
        param.put("longurl", longUrl);
        param.put("api", "sina");
        String result = HttpClientUtil.doGet("http://tools.aeink.com/tools/dwz/urldwz.php",param);
        JSONObject jsonObject =JSON.parseObject(result);
        String ae_url = jsonObject.getString("ae_url");
        return ae_url;
    }
}
