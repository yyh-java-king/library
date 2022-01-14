package com.atguigu.dao.impl;

import com.atguigu.dao.LendDao;
import com.atguigu.domain.Lend;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/12 - 20:02
 */
public class LendDaoImpl extends BasicDao<Lend> implements LendDao {
    @Override
    public boolean addLend(Lend lend) {
        String sql = "insert into lend values(null,?,?,?,?)";
        return update(sql, lend.getUser_id(), lend.getBook_id(), lend.getLend_date(), lend.getBack_date()) > 0;
    }

    @Override
    public boolean updateLend(Lend lend) {
        String sql = "update lend set book_id=?,user_id=?,lend_date=?,back_date=? where id=?";
        return update(sql, lend.getBook_id(), lend.getUser_id(), lend.getLend_date(), lend.getBack_date(), lend.getId()) > 0;
    }

    @Override
    public boolean deleteLend(Integer id) {
        String sql = "delete from lend where id=?";
        return update(sql, id) > 0;
    }

    @Override
    public List<Lend> queryLends() {
        String sql = "select * from lend";
        return queryMulti(sql, Lend.class);
    }

    @Override
    public List<Lend> queryLendsByUserId(Integer userId) {
        String sql = "select * from lend where user_id=?";
        return queryMulti(sql, Lend.class, userId);
    }

    @Override
    public boolean returnBook(Date date, Integer userId, Integer bookId) {
        String sql = "update lend set back_date=? where user_id=? and book_id=? and ISNULL(back_date)=1";
        return update(sql, date, userId, bookId) > 0;
    }

}
