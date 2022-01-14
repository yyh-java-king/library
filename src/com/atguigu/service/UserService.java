package com.atguigu.service;

import com.atguigu.domain.User;
import com.atguigu.domain.UserInfo;

import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/8 - 11:12
 */
public interface UserService {
    /**
     * 根据用户名查询有没有该用户
     * true - 数据库中有该账号
     * false - 数据库中没有该账号
     */
    boolean queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户
     *
     * @param username 用户名
     * @param password 密码
     */
    User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 根据id查询用户信息
     *
     * @param id id
     * @return 用户信息
     */
    UserInfo queryUserInfoById(Integer id);

    /**
     * 根据id查询用户账号密码
     *
     * @param id id
     * @return 用户信息
     */
    User queryUserById(Integer id);

    /**
     * 根据用户名有没有除自己以外的其他用户
     * true - 有除id以外的其他账号重复 false - 没有重复,该账号可用
     */
    boolean queryUserByUsername(String username, Integer id);

    /**
     * 根据用户id修改用户信息
     *
     * @param userInfo 用户信息
     * @return 修改后的用户信息 返回null即修改不成功
     */
    UserInfo updateUserInfo(UserInfo userInfo);

    /**
     * 根据用户id修改用户的账号密码
     *
     * @param user 用户账号密码
     * @return 修改后的用户信息 返回null即修改不成功
     */
    User updateUser(User user);


    /**
     * 根据id修改密码
     * @return 修改成功的用户信息 null - 修改失败
     */
    User updatePasswordById(User user);


    /**
     * 获取所有读者信息
     */
    List<User> queryUsers();

    /**
     * 获取所有读者的信息
     * @return 所有读者信息
     */
    List<UserInfo> queryUserInfos();


    /**
     * 添加用户
     * @return true - 删除成功 false - 删除失败
     */
    boolean addUser(User user,UserInfo userInfo);

    /**
     * 删除用户
     * @return true - 删除成功 false - 删除失败
     */
    boolean deleteUser(Integer id);

}
