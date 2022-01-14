package com.atguigu.dao.impl;

import com.atguigu.dao.UserInfoDao;
import com.atguigu.domain.UserInfo;

import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/8 - 13:34
 */
public class UserInfoDaoImpl extends BasicDao<UserInfo> implements UserInfoDao {
    @Override
    public UserInfo queryUserInfoById(Integer id) {
        String sql = "select * from `user_info` where id=?";
        return querySingle(sql, UserInfo.class, id);
    }

    @Override
    public boolean updateUserInfo(UserInfo userInfo) {
        String sql = "update `user_info` set `name`=?,sex=?,birthday=?,address=?,phone=? where id=?";
        int update = update(sql, userInfo.getName(), userInfo.getSex(),
                userInfo.getBirthday(), userInfo.getAddress(), userInfo.getPhone(), userInfo.getId());
        return update > 0;
    }

    @Override
    public List<UserInfo> queryUserInfos() {
        String sql = "select * from user_info";
        return queryMulti(sql, UserInfo.class);
    }

    @Override
    public boolean addUserInfo(UserInfo userInfo) {
        String sql = "insert into user_info values(?,?,?,?,?,?)";
        return update(sql,userInfo.getId(),userInfo.getName(),userInfo.getSex(),
                userInfo.getBirthday(),userInfo.getAddress(),userInfo.getPhone()) > 0;
    }

    @Override
    public boolean deleteUserInfo(Integer id) {
        String sql = "delete from user_info where id=?";
        return update(sql,id) > 0;
    }


}
