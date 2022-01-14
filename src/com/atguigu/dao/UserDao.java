package com.atguigu.dao;

import com.atguigu.domain.User;
import com.atguigu.domain.UserInfo;

import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/8 - 10:59
 */
public interface UserDao {

    /**
     * 根据用户名查询用户
     */
   User queryUserByUsername(String username);

 /**
  * 根据用户id查询用户
  */
 User queryUserById(Integer id);

    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     */
    User queryUserByUsernameAndPassword(String  username, String password);

    /**
     * 根据用户名查询除自己以外的其他用户
     */
    User queryUserByUsername(String username,Integer id);

    /**
     * 根据用户id修改用户的账号和密码
     * @param user 用户账号密码
     * @return true - 修改成功 false - 修改失败
     */
    boolean updateUser(User user);

    /**
     * 根据id修改密码
     * @return true - 修改成功 false - 修改失败
     */
    boolean updatePasswordById(User user);

    /**
     * 获取所有读者信息
     */
    List<User> queryUsers();

    /**
     * 添加用户
     * @return true - 添加成功 false - 添加失败
     */
    boolean addUser(User user);

    /**
     * 删除用户
     * @return true - 添加成功 false - 添加失败
     */
    boolean deleteUser(Integer id);

   /**
    * 根据用户名查询用户id
    * @param username 用户名
    * @return 用户id
    */
    Integer queryIdByUsername(String username);


}
