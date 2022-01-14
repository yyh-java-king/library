package com.atguigu.service;


import com.atguigu.domain.Lend;

import java.util.Date;
import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/13 - 9:11
 */
public interface LendService {

    /**
     * 创建借还记录
     * @param userId 用户id
     * @param bookId 图书id
     * @return true - 创建成功 false 创建失败
     */
    boolean addLend(Integer userId, Integer bookId);

    /**
     * 还书
     * @param userId 用户id
     * @param bookId 图书id
     * @return true - 还书成功 false 还书失败
     */
    boolean returnBook(Integer userId, Integer bookId);

    /**
     * 删除借还记录
     * @return true - 删除成功 false - 删除失败
     */
    boolean deleteLend(Integer id);

    /**
     * 查询所有借还记录
     * @return 所有借还记录
     */
    List<Lend> queryLends();

    /**
     * 通过用户id返回该用户所有借还记录
     * @param userId 用户id
     */
    List<Lend> queryLendByUserId(Integer userId);

    /**
     * 获取该用户所有未还的图书id
     * @param userId 用户id
     * @return 图书id集合
     */
    List<Integer> unpaid(Integer userId);
}
