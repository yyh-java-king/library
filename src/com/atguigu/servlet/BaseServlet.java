package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2021/12/8 - 9:36
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*解决post请求中文乱码问题*/
        req.setCharacterEncoding("UTF-8");
        /*解决响应中文乱码问题*/
        resp.setContentType("text/html; charset=UTF-8");
        /*获取客户端请求调用的方法*/
        String action = req.getParameter("action");
        /*使用反射动态的调用方法*/
        try {
            /*使用反射动态执行对应方法*/
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            /*执行  clazz.newInstance() : 获取UserService类的对象*/
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            /*向外抛异常,因为如果在这里把异常消化掉了的话,
            拦截器中就无法进行捕获异常.就无法进行事务的回滚*/
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

}
