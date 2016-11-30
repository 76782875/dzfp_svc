package com.rjxx.taxeasy.bizcomm.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by admin on 2016/5/31.
 */
public class SecurityContextUtils {

    /**
     * 获取当前登录的用户信息
     *
     * @return
     */
    public static SecurityUser getCurrentLoginInfo() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if(securityContext == null){
            return null;
        }
        Authentication authentication = securityContext.getAuthentication();
        if(authentication == null){
            return null;
        }
        Object user = authentication.getPrincipal();
        if (user instanceof SecurityUser) {
            return (SecurityUser) user;
        }
        return null;
    }

    /**
     * 获取登陆用户的id
     *
     * @return
     */
    public static String getLoginUserId() {
        SecurityUser securityUser = getCurrentLoginInfo();
        if (securityUser == null) {
            return null;
        }
//        return securityUser.getUser().getId();
        return null;
    }

    /**
     * 获取当前登录的账户
     *
     * @return
     */
    public static String getCurrentLoginUsername() {
        SecurityUser securityUser = getCurrentLoginInfo();
        if (securityUser == null) {
            return null;
        }
        return securityUser.getUsername();
    }

}
