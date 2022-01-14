package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.LendDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.LendDaoImpl;
import com.atguigu.domain.Book;
import com.atguigu.domain.Lend;
import com.atguigu.service.LendService;
import com.atguigu.utility.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/13 - 9:40
 */
public class LendServiceImpl implements LendService {
    LendDao lendDao = new LendDaoImpl();
    BookDao bookDao = new BookDaoImpl();
    @Override
    public boolean addLend(Integer userId, Integer bookId) {
        /*图书数量减一*/
        bookDao.numberSubtractOne(bookId);
        return lendDao.addLend(new Lend(null, userId, bookId, DateUtils.nowDateTime(), null));
    }

    @Override
    public boolean returnBook(Integer userId, Integer bookId) {
        /*图书数量加一*/
        bookDao.numberAddOne(bookId);
        return lendDao.returnBook(DateUtils.nowDateTime(), userId, bookId);
    }

    @Override
    public boolean deleteLend(Integer id) {
        return lendDao.deleteLend(id);
    }

    @Override
    public List<Lend> queryLends() {
        return lendDao.queryLends();
    }

    @Override
    public List<Lend> queryLendByUserId(Integer userId) {
        return lendDao.queryLendsByUserId(userId);
    }

    @Override
    public List<Integer> unpaid(Integer userId) {
        List<Integer> list = new ArrayList<>();
        for (Lend lend : lendDao.queryLendsByUserId(userId)) {
            if (lend.getBack_date() == null){
                list.add(lend.getBook_id());
            }
        }
        return list;
    }
}
