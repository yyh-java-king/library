package com.atguigu.filter;


import com.atguigu.utility.DruidUtility;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2021/12/21 - 21:42
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            /*提交事务*/
            DruidUtility.commitAndClose();
        } catch (Exception e) {
            /*回滚事务*/
            DruidUtility.rollbackAndClose();
            e.printStackTrace();
            /*把异常抛给Tomcat服务器,让服务器做出发生异常进行友好的展示页面*/
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
