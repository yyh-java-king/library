package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.UserInfoDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.dao.impl.UserInfoDaoImpl;
import com.atguigu.domain.User;
import com.atguigu.domain.UserInfo;
import com.atguigu.service.UserService;

import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/8 - 11:14
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    UserInfoDao userInfoDao = new UserInfoDaoImpl();

    @Override
    public boolean queryUserByUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        return userDao.queryUserByUsernameAndPassword(username, password);
    }

    @Override
    public UserInfo queryUserInfoById(Integer id) {
        return userInfoDao.queryUserInfoById(id);
    }

    @Override
    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }

    @Override
    public boolean queryUserByUsername(String username, Integer id) {
        return userDao.queryUserByUsername(username, id) != null;
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        if (userInfoDao.updateUserInfo(userInfo)) {
            /*修改成功返回修改成功的信息*/
            return userInfo;
        }
        /*修改不成功即返回null*/
        return null;
    }

    @Override
    public User updateUser(User user) {
        if (userDao.updateUser(user)) {
            /*修改成功返回修改成功的信息*/
            return user;
        }
        /*修改不成功即返回null*/
        return null;
    }

    @Override
    public User updatePasswordById(User user) {
        if (userDao.updatePasswordById(user)) {
            /*修改成功返回修改成功的信息*/
            return user;
        }
        /*修改不成功即返回null*/
        return null;
    }

    @Override
    public List<User> queryUsers() {
        return userDao.queryUsers();
    }

    @Override
    public List<UserInfo> queryUserInfos() {
        return userInfoDao.queryUserInfos();
    }

    @Override
    public boolean addUser(User user, UserInfo userInfo) {
        boolean addUser = userDao.addUser(user);
        /*给userInfo设置user的ID号*/
        userInfo.setId(userDao.queryIdByUsername(user.getUsername()));
        boolean addUserInfo = userInfoDao.addUserInfo(userInfo);
        return addUser && addUserInfo;
    }

    @Override
    public boolean deleteUser(Integer id) {

        return userInfoDao.deleteUserInfo(id) && userDao.deleteUser(id);
    }
}
