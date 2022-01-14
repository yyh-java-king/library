package com.atguigu.utility;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2021/11/3 - 16:46
 * Druid连接池的工具类
 */
public class DruidUtility {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> connections = new InheritableThreadLocal<>();

    static {
        Properties properties = new Properties();
        try {
            //使用类加载器获取配置文件的信息
            InputStream is = DruidUtility.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取数据库的连接
     *
     * @return 连接
     */
    public static Connection getConnection() {
        /*获取ThreadLocal域中保存的数据库连接*/
        Connection conn = connections.get();
        /*判断有没有保存连接*/
        if (conn == null) {
            try {
                /*如果没有保存连接就从数据库链接池中取一个连接*/
                conn = dataSource.getConnection();
                /*将其保存到ThreadLocal域中*/
                connections.set(conn);
                /*设置为手动事务管理*/
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    /**
     * 提交事务并且关闭连接
     */
    public static void commitAndClose()  {
        /*获取ThreadLocal域中保存的数据库连接*/
        Connection connection = DruidUtility.connections.get();
        /*如果conne   ction不等于null表示该连接使用过需要提交事务和关闭连接*/
        if (connection != null){
            try {
                /*提交事务*/
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    /*关闭连接*/
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        /*
        * 一定要执行的操作,否则会报错
        * 因为Tomcat服务器底层使用了线程池技术
        */
        connections.remove();
    }

    /**
     * 回滚事务并且关闭连接
     */
    public static void rollbackAndClose()  {
        /*获取ThreadLocal域中保存的数据库连接*/
        Connection connection = DruidUtility.connections.get();
        /*如果connection不等于null表示该连接使用过需要提交事务和关闭连接*/
        if (connection != null){
            try {
                /*回滚事务*/
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    /*关闭连接*/
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        /*
         * 一定要执行的操作,否则会报错
         * 因为Tomcat服务器底层使用了线程池技术
         */
        connections.remove();
    }

    /**
     * 关闭资源方法
     * 如果不需要关闭该类的资源,实参传入null即可
     *
     * @param connection 连接
     */
    public static void close(Connection connection) {
        try {

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            //把编译异常转成运行异常
            throw new RuntimeException(e);
        }
    }

}
