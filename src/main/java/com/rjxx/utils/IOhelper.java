package com.rjxx.utils;

import java.io.*;

/**
 * Created by wangyahui on 2017-06-14.
 */
public class IOhelper {

    //往文件写入字符串
    public static void wirteString(String path, String context) {
        try {
            //append为true往尾追加，flase往头追加
            FileWriter fw = new FileWriter(path, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(context + "\r\n");// 往已有的文件上添加字符串
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取文件
    public static BufferedReader readString(String path) {
        //创建读取对象
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //创建缓存区
        BufferedReader reader = new BufferedReader(fileReader);
        return reader;
    }

    public static void deleteFile(String src){
        File file = new File(src);
        if(!file.exists()){
            return;
        }
        if(file.isFile()){
            file.delete();
        }
    }

    //清空文件内容
    public static void clearInfoForFile(String src) {
        File file =new File(src);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
