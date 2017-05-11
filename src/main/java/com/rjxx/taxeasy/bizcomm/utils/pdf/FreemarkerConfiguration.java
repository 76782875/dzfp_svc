package com.rjxx.taxeasy.bizcomm.utils.pdf;

import com.rjxx.utils.ResourceLoader;
import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * freemark配置
 * Created by Him on 2015-09-22.
 */
public class FreemarkerConfiguration {
    private static Configuration config = null;

    /**
     * 获取 FreemarkerConfiguration
     *
     * @Title: getConfiguation
     * @Description:
     * @return
     * @author
     */
    public static synchronized Configuration getConfiguation() {
        if (config == null) {
            setConfiguation();
        }
        return config;
    }
    /**
     * 设置 配置
     * @Title: setConfiguation
     * @Description:
     * @author
     */
    private static void setConfiguation() {
        config = new Configuration();
        String path = ResourceLoader.getPath("");
        System.out.println("path="+path);
        try {
            config.setDirectoryForTemplateLoading(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String path = ResourceLoader.getPath("config/fonts");
        System.out.println("path="+path);
    }
}
