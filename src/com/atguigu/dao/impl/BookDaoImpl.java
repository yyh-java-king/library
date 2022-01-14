package com.atguigu.dao.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.domain.Book;

import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/12 - 9:07
 */
public class BookDaoImpl extends BasicDao<Book> implements BookDao {
    @Override
    public boolean addBook(Book book) {
        String sql = "insert into book values(null,?,?,?,?,?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPublish(), book.getISBN(),
                book.getIntro(), book.getLanguage(), book.getPrice(), book.getPub_date(),
                book.getType(), book.getNumber()) > 0;
    }

    @Override
    public boolean deleteBook(Integer id) {
        String sql = "delete from book where id=?";
        return update(sql, id) > 0;
    }

    @Override
    public boolean updateBook(Book book) {
        String sql = "update book set name=?,author=?,publish=?,ISBN=?,intro=?,language=?,price=?,pub_date=?,type=?,number=? where id=?";
        return update(sql, book.getName(), book.getAuthor(), book.getPublish(), book.getISBN(),
                book.getIntro(), book.getLanguage(), book.getPrice(), book.getPub_date(),
                book.getType(), book.getNumber(), book.getId()) > 0;
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select * from book where id=?";
        return querySingle(sql, Book.class, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from book";
        return queryMulti(sql, Book.class);
    }

    @Override
    public List<Book> dimQueryBooks(String name) {
        String sql = "select * from book where name like ?";
        return queryMulti(sql, Book.class, "%"+name+"%");
    }

    @Override
    public boolean numberSubtractOne(Integer id) {
        String sql = "update book set `number`=number-1 where id=?";
        return update(sql,id) > 0;
    }

    @Override
    public boolean numberAddOne(Integer id) {
        String sql = "update book set `number`=number+1 where id=?";
        return update(sql,id) > 0;
    }
}
