package com.atguigu.utility;

import javax.servlet.http.Cookie;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2021/12/14 - 15:55
 */
public class CookieUtils {

    /**
     * 用过cookie的name值获取对应的cookie
     * @param cookies 客户端发送过来的所有Cookie
     * @param name 要查找的cookie的name值
     * @return 如果返回null则查找失败
     */
    public static Cookie getCookieByName(Cookie[] cookies,String name){
        /*检验有效性*/
        if (cookies == null || name == null || cookies.length == 0){
            return null;
        }
        /*遍历查找*/
        for (Cookie cookie : cookies) {
            /*找到对应的name这返回该cookie*/
            if (cookie.getName().equals(name)){
                return cookie;
            }
        }
        /*没找到返回null*/
        return null;
    }
}
