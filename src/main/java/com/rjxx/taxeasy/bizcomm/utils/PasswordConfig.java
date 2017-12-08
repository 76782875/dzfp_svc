package com.rjxx.taxeasy.bizcomm.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by xlm on 2017/11/27.
 */
@Component
public class PasswordConfig {
    public static  String FTP_URL;

    @Value("${ftp_url}")
    public void setFtp_url(String ftp_url) {
        FTP_URL = ftp_url;
    }

    public static  int FTP_PORT;

    @Value("${ftp_port}")
    public void setFTP_PORT(int ftp_port) {
        FTP_PORT = ftp_port;
    }


    public static  String FTP_USERNAME;

    @Value("${ftp_username}")
    public void setFTP_USERNAME(String ftp_username) {
        FTP_USERNAME = ftp_username;
    }

    public static  String FTP_PASSWORD;

    @Value("${ftp_password}")
    public void setFTP_PASSWORD(String ftp_password) {
        FTP_PASSWORD = ftp_password;
    }

    public static  String FTP_BASEPATH;

    @Value("${ftp_basepath}")
    public void setFTP_BASEPATH(String ftp_basepath) {
        FTP_BASEPATH = ftp_basepath;
    }

    public static  String FTP_FILEPATH;

    @Value("${ftp_filepath}")
    public void setFTP_FILEPATH(String ftp_filepath) {
        FTP_FILEPATH = ftp_filepath;
    }

    public static  String PDF_SIGNUSER;//pdf签名证书别名

    @Value("${pdf_signuser}")
    public void setPDF_SIGNUSER(String pdf_signuser){
        PDF_SIGNUSER = pdf_signuser;
    }

    public static  String PDF_SIGNPASSWORD;//pdf签名证书密码

    @Value("${pdf_signpassword}")
    public void setPDF_SIGNPASSWORD(String pdf_signpassword) {
        PDF_SIGNPASSWORD = pdf_signpassword;
    }

    public static  String keyStorePath;//pdf证书路径

    @Value("${pdf_keyStorePath}")
    public void  setKeyStorePath(String pdf_keyStorePath){
        keyStorePath=pdf_keyStorePath;
    }
}
