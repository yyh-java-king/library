package com.atguigu.dao;


import com.atguigu.domain.Book;

import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/12 - 8:52
 */
public interface BookDao {
    /**
     * 添加图书
     * @param book 要添加的图书信息
     * @return true - 添加成功 false - 添加失败
     */
    boolean addBook(Book book);

    /**
     * 删除图书
     * @param id 要删除的id
     * @return  true - 删除成功 false - 删除失败
     */
    boolean deleteBook(Integer id);

    /**
     * 修改图书
     * @param book 要修改的id和信息
     * @return true - 修改成功 false - 修改失败
     */
    boolean updateBook(Book book);

    /**
     * 根据id查询图书
     * @return 返回查询到的图书信息
     */
    Book queryBookById(Integer id);

    /**
     * 查询所有图书
     * @return 返回所有图书信息
     */
    List<Book> queryBooks();

    /**
     * 模糊查询
     * @param name 图书书名
     * @return 返回查询到的图书信息
     */
    List<Book> dimQueryBooks(String name);

    /**
     * 根据图书id修改图书数量图书数量减一
     * @param id 图书id
     * @return true - 修改成功 false - 修改失败
     */
    boolean numberSubtractOne(Integer id);

    /**
     * 根据图书id修改图书数量图书数量加一
     * @param id 图书id
     * @return true - 修改成功 false - 修改失败
     */
    boolean numberAddOne(Integer id);
}
