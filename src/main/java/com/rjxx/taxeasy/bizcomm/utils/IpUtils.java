package com.rjxx.taxeasy.bizcomm.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by xlm on 2018/2/2.
 */
public class IpUtils {

    /**
     * @Title: getIpAddr
     * @author xlm
     * @Description: 获取客户端IP地址
     * @param @return
     * @return String
     * @throws
     */
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }
    /**
     * 判断IP是否在指定IP段内，方法一（推荐）
     * ipRange IP段（以'-'分隔）
     *
     * @param ipRange
     * @param ip
     * @return boolean
     */
    public static boolean ipIsInRange(String ip, String ipRange) {
        if (ipRange == null) {
            throw new NullPointerException("IP段不能为空！");
        }
        if (ip == null) {
            throw new NullPointerException("IP不能为空！");
        }
        ipRange = ipRange.trim();
        ip = ip.trim();
        final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
        final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;
        if (!ipRange.matches(REGX_IPB) || !ip.matches(REGX_IP))
            return false;
        int idx = ipRange.indexOf('-');
        String[] sips = ipRange.substring(0, idx).split("\\.");
        String[] sipe = ipRange.substring(idx + 1).split("\\.");
        String[] sipt = ip.split("\\.");
        long ips = 0L, ipe = 0L, ipt = 0L;
        for (int i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(sips[i]);
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);
        }
        if (ips > ipe) {
            long t = ips;
            ips = ipe;
            ipe = t;
        }
        return ips <= ipt && ipt <= ipe;
    }

    /**
     * 判断IP是否在指定IP段内，方法二
     * ipRange IP段（以'-'分隔）
     *
     * @param ipRange
     * @param ip
     * @return boolean
     */
    public static boolean ipInRange(String ip, String ipRange) {
        int idx = ipRange.indexOf('-');
        String beginIP = ipRange.substring(0, idx);
        String endIP = ipRange.substring(idx + 1);
        return getIp2long(beginIP) <= getIp2long(ip)
                && getIp2long(ip) <= getIp2long(endIP);
    }

    public static long getIp2long(String ip) {
        String[] ips = ip.split("\\.");
        long ip2long = 0L;
        for (int i = 0; i < 4; ++i) {
            ip2long = ip2long << 8 | Integer.parseInt(ips[i]);
        }
        return ip2long;
    }
}
