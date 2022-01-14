package com.atguigu.dao.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.domain.User;

import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/8 - 11:02
 */
public class UserDaoImpl extends BasicDao<User> implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select * from `user` where username=?";
        return querySingle(sql, User.class, username);
    }

    @Override
    public User queryUserById(Integer id) {
        String sql="select * from `user` where id=?";
        return querySingle(sql,User.class,id);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from `user` where username=? and password=?";
        return querySingle(sql, User.class, username, password);
    }

    @Override
    public User queryUserByUsername(String username, Integer id) {
        String sql = "select * from `user` where username=? and id!=?";
        return querySingle(sql, User.class, username, id);
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "update `user` set `username`=?,`password`=?  where id=?";
        int update = update(sql, user.getUsername(), user.getPassword(), user.getId());
        System.out.println(update);
        return update > 0;
    }

    @Override
    public boolean updatePasswordById(User user) {
        String sql = "update `user` set `password`=? where id=?";
        return update(sql, user.getPassword(), user.getId()) > 0;
    }

    @Override
    public List<User> queryUsers() {
        String sql="select * from `user` where status=0";
        return queryMulti(sql,User.class);
    }

    @Override
    public boolean addUser(User user) {
        String  sql = "insert into `user` values(null,?,?,0)";
        return update(sql,user.getUsername(),user.getPassword()) > 0;
    }
    @Override
    public boolean deleteUser(Integer id) {
        String  sql = "delete from `user` where id=?";
        return update(sql,id) > 0;
    }

    @Override
    public Integer queryIdByUsername(String username) {
        String sql = "select id from `user` where username=?";
        return (Integer) queryScalar(sql,username);
    }


}
