package com.atguigu.dao;

import com.atguigu.domain.User;
import com.atguigu.domain.UserInfo;

import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/8 - 13:33
 */
public interface UserInfoDao {
    /**
     * 根据id查询用户信息
     * @param id id
     * @return 用户信息
     */
    UserInfo queryUserInfoById(Integer id);

    /**
     * 根据用户id修改用户的信息
     * @param userInfo 用户信息
     * @return true - 修改成功 false - 修改失败
     */
    boolean updateUserInfo(UserInfo userInfo);

    /**
     * 获取所有读者的信息
     * @return 所有读者信息
     */
    List<UserInfo> queryUserInfos();
    /**
     * 添加读者
     * @return  true - 添加成功 false - 添加失败
     */
    boolean addUserInfo(UserInfo userInfo);
    /**
     * 删除读者
     * @return true - 删除成功 false - 删除失败
     */
    boolean deleteUserInfo(Integer id);

}
