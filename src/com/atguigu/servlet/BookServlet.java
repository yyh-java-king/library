package com.atguigu.servlet;

import com.atguigu.domain.Book;
import com.atguigu.domain.User;
import com.atguigu.service.BookService;
import com.atguigu.service.LendService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.service.impl.LendServiceImpl;
import com.atguigu.utility.DateUtils;
import com.atguigu.utility.WebUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/12 - 10:17
 */
public class BookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();
    LendService lendService = new LendServiceImpl();

    /*查询所有图书*/
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取所有图书信息*/
        List<Book> books = bookService.queryBooks();
        /*发送到Request域*/
        req.setAttribute("books", books);
        /*根据登入的用户级别请求转发到相应界面*/
        User user = (User) req.getSession().getAttribute("user");
        /*查询登入的用户的级别*/
        if (user.getStatus() == 0) {
            /*获取该读者已借但未还的图书id集合*/
            List<Integer> unpaid = lendService.unpaid(user.getId());
            /*发送到request域中*/
            req.setAttribute("unpaid", unpaid);
            /*读者转发到读者的页面*/
            req.getRequestDispatcher("/pages/book/user_book_query.jsp").forward(req, resp);
        } else if (user.getStatus() == 1) {
            /*管理员转发到管理员的页面*/
            req.getRequestDispatcher("/pages/book/admin_book_query.jsp").forward(req, resp);
        }
    }

    /*根据id查询图书*/
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取要查询的图书id*/
        Integer id = WebUtility.parseInt(req.getParameter("id"), -1);
        /*调用业务层查询图书*/
        Book book = bookService.queryBookById(id);
        /*发送到request域*/
        req.setAttribute("book", book);
        if ("update".equals(req.getParameter("method"))) {
            /*请求转发到修改图书界面*/
            req.getRequestDispatcher("/pages/book/add_update_book.jsp").forward(req, resp);
        } else {
            /*请求转发到图书详情界面*/
            req.getRequestDispatcher("/pages/book/book_details.jsp").forward(req, resp);
        }
    }

    /*根据书名查询(模糊查询)图书*/
    protected void dimListBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取要查询的图书*/
        String name = req.getParameter("name");
        /*调用业务层进行模糊查询*/
        List<Book> books = bookService.dimQueryBooks(name);
        /*发送到request域*/
        req.setAttribute("books", books);
        /*根据登入的用户级别请求转发到相应界面*/
        User user = (User) req.getSession().getAttribute("user");
        /*查询登入的用户的级别*/
        if (user.getStatus() == 0) {
            /*请求转发到用户查询图书的页面*/
            req.getRequestDispatcher("/pages/book/user_book_query.jsp").forward(req, resp);
        } else if (user.getStatus() == 1) {
            /*请求转发到管理员查询图书的页面*/
            req.getRequestDispatcher("/pages/book/admin_book_query.jsp").forward(req, resp);
        }
    }

    /*修改图书*/
    protected void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取要修改的信息*/
        Book book = populateBook(req.getParameterMap());
        System.out.println(book);
        /*调用业务层修改图书信息*/
        if (bookService.updateBook(book)) {
            /*修改成功请求转发到所有图书页面*/
            req.getRequestDispatcher("bookServlet?action=list").forward(req, resp);
        } else {
            /*修改失败请求转发到原地址,并且提示*/
            req.setAttribute("error", "修改失败");
            req.getRequestDispatcher("bookServlet?action=getBook&method=update&id=" + book.getId()).forward(req, resp);
        }
    }

    /*添加图书*/
    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取要添加的信息*/
        Book book = populateBook(req.getParameterMap());
        /*调用业务层修改图书信息*/
        if (bookService.addBook(book)) {
            /*添加成功请求转发到所有图书页面*/
            resp.sendRedirect("/library/bookServlet?action=list");
        } else {
            /*修改失败请求转发到原地址,并且提示*/
            req.setAttribute("error", "修改失败");
            req.getRequestDispatcher("/pages/book/add_update_book.jsp").forward(req, resp);
        }
    }

    /*删除图书*/
    protected void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取要删除的图书id*/
        int id = WebUtility.parseInt(req.getParameter("id"), -1);
        /*调用业务层进行删除操作*/
        bookService.deleteBook(id);
        /*请求转发到所有图书页面*/
        req.getRequestDispatcher("bookServlet?action=list").forward(req, resp);
    }


    /**
     * 封装book的信息
     */
    private Book populateBook(Map<String, String[]> value) {
        Book book = new Book();
        /*遍历map*/
        for (Map.Entry<String, String[]> entry : value.entrySet()) {
            switch (entry.getKey()) {
                case "id":
                    book.setId(WebUtility.parseInt(entry.getValue()[0], 0));
                    break;
                case "name":
                    book.setName(entry.getValue()[0]);
                    break;
                case "author":
                    book.setAuthor(entry.getValue()[0]);
                    break;
                case "publish":
                    book.setPublish(entry.getValue()[0]);
                    break;
                case "ISBN":
                    book.setISBN(entry.getValue()[0]);
                    break;
                case "intro":
                    book.setIntro(entry.getValue()[0]);
                    break;
                case "language":
                    book.setLanguage(entry.getValue()[0]);
                    break;
                case "price":
                    book.setPrice(BigDecimal.valueOf(WebUtility.parseDouble(entry.getValue()[0], 0)));
                    break;
                case "pub_date":
                    book.setPub_date(DateUtils.parseDate(entry.getValue()[0]));
                    break;
                case "type":
                    book.setType(entry.getValue()[0]);
                    break;
                case "number":
                    book.setNumber(WebUtility.parseInt(entry.getValue()[0], 0));
                    break;
            }
        }
        return book;
    }

}
