package com.atguigu.dao;

import com.atguigu.domain.Lend;

import java.util.Date;
import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/12 - 19:16
 */
public interface LendDao {
    /**
     * 创建借还记录
     *
     * @param lend 记录信息
     * @return true - 创建成功 false - 创建失败
     */
    boolean addLend(Lend lend);

    /**
     * 修改借还记录
     *
     * @param lend 记录信息
     * @return true - 修改成功 false - 修改失败
     */
    boolean updateLend(Lend lend);

    /**
     * 根据id删除借还记录
     *
     * @param id 记录id
     * @return true - 删除成功 false删除失败
     */
    boolean deleteLend(Integer id);

    /**
     * 查询所有借还记录
     *
     * @return 所有借还记录
     */
    List<Lend> queryLends();

    /**
     * 根据读者id该读者的借还记录
     */
    List<Lend> queryLendsByUserId(Integer userId);

    /**
     * 还书
     * @param date 还书时间
     * @param userId 还书的用户id
     * @param bookId 归还的图书
     * @return true - 还书成功 false - 还书失败
     */
    boolean returnBook(Date date, Integer userId, Integer bookId);
}
