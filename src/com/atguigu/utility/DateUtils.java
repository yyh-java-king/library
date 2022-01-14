package com.atguigu.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/10 - 19:05
 */
public class DateUtils {
    private static SimpleDateFormat sdf;

    /**
     * @param date 时间字符串
     * @return 转换后的Date类型
     */
    public static Date parseDate(String date) {
        try {
            return java.sql.Date.valueOf(date);
        } catch (Exception e) {
            throw new RuntimeException("时间字符串转换异常!");
        }
    }

    /**
     * @param date 时间字符串
     * @return 转换后的Date类型
     */
    public static Date parseDate(String date, String format) {
        sdf = new SimpleDateFormat(format);
        try {
            Date cDate2 = sdf.parse(date);
            java.sql.Date dd2 = new java.sql.Date(cDate2.getTime());
            return new java.sql.Timestamp(dd2.getTime());
        } catch (Exception e) {
            throw new RuntimeException("时间字符串转换异常!");
        }
    }

    /**
     * @param date   时间
     * @param format 要转换的date格式
     * @return 转换后的字符串
     */
    public static String parseStr(Date date, String format) {
        sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 返回当前时间
     */
    public static Date nowDateTime(){
        sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = new Date();
            java.sql.Date dd2 = new java.sql.Date(date.getTime());
            return new java.sql.Timestamp(dd2.getTime());
        } catch (Exception e) {
            throw new RuntimeException("时间字符串转换异常!");
        }
    }

}
