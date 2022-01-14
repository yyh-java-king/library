package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.domain.Book;
import com.atguigu.service.BookService;

import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/12 - 9:48
 */
public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();
    @Override
    public boolean addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public boolean deleteBook(Integer id) {
        return bookDao.deleteBook(id);
    }

    @Override
    public boolean updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public List<Book> dimQueryBooks(String name) {
        return bookDao.dimQueryBooks(name);
    }
}
