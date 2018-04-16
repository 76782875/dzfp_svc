package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.rjxx.utils.StringUtils;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zsq
 * @date: 2018/4/16 13:59
 * @describe: 导出txt文件
 */
public class ExportTextUtil {
    /**
     * 声明日志记录器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportTextUtil.class);

    /**
     * 导出文本文件
     * @param response
     * @param jsonString
     */
    public static void writeToTxt(HttpServletResponse response,String jsonString) {//设置响应的字符集


        BufferedOutputStream buff = null;
        ServletOutputStream outStr = null;
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String filename = timeFormat.format(new Date()) + ".txt";
            //设置响应内容的类型
            response.setCharacterEncoding("utf-8");
            //设置文件的名称和格式
            response.setContentType("text/plain");
            response.setHeader("Content-Disposition",
                    "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(delNull(jsonString).getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            LOGGER.error("导出文件文件出错，e:{}",e);
        } finally {try {
            buff.close();
            outStr.close();
        } catch (Exception e) {
            LOGGER.error("关闭流对象出错 e:{}",e);
        }
        }
    }

    /**
     * 如果字符串对象为 null，则返回空字符串，否则返回去掉字符串前后空格的字符串
     * @param str
     * @return
     */
    public static String delNull(String str) {
        String returnStr="";
        if (StringUtils.isNotBlank(str)) {
            returnStr=str.trim();
        }
        return returnStr;
    }

}
