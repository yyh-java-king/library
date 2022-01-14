package com.atguigu.dao.impl;


import com.atguigu.utility.DruidUtility;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2021/11/4 - 19:36
 * 关闭数据库连接可以使用filter来进行事务的提交,事务的回滚和连接的关闭
 */
public abstract class BasicDao<T> {
    private QueryRunner queryRunner = new QueryRunner();


    /**
     * @param sql       增删改SQL语句
     * @param parameter 指定SQL语句中问号的数据
     * @return 返回表中受影响的行数 如果返回小于等于0 表示执行失败
     */
    public int update(String sql, Object... parameter) {
        Connection connection = null;
        try {
            connection = DruidUtility.getConnection();
            return queryRunner.update(connection, sql, parameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param sql       sql语句
     * @param clazz     返回的List集合中的类型
     * @param parameter 指定SQL语句中问号的数据
     * @return 返回表中所有的数据
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameter) {
        Connection connection = null;
        try {
            connection = DruidUtility.getConnection();
            return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), parameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param sql       sql语句
     * @param clazz     返回的List集合中的类型
     * @param parameter 指定SQL语句中问号的数据
     * @return 返回表中一行的数据
     */
    public T querySingle(String sql, Class<T> clazz, Object... parameter) {
        Connection connection = null;
        try {
            connection = DruidUtility.getConnection();
            return queryRunner.query(connection, sql, new BeanHandler<>(clazz), parameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * @param sql       SQL语句
     * @param parameter 指定SQL语句中问号的数据
     * @return 返回单个数据
     */
    public Object queryScalar(String sql, Object... parameter) {
        Connection connection = null;
        try {
            connection = DruidUtility.getConnection();
            return queryRunner.query(connection, sql, new ScalarHandler(), parameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
