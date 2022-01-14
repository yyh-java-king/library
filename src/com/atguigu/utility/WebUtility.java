package com.atguigu.utility;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2021/12/8 - 14:19
 */
public class WebUtility {
    /**
     * 将Map中的数据注入到JavaBean中
     *
     * @param bean javaBean
     */
    public static <T> T copyParamToBean(T bean, Map value) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换成为int类型的数据
     * @param strInt 要转换的字符串
     * @param defaultValue 转换失败默认返回值
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
    /**
     * 将字符串转换成为double类型的数据
     * @param strInt 要转换的字符串
     * @param defaultValue 转换失败默认返回值
     */
    public static double parseDouble(String strInt,double defaultValue) {
        try {
            return Double.parseDouble(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}
