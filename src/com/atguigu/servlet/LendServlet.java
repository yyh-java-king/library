package com.atguigu.servlet;

import com.atguigu.domain.Lend;
import com.atguigu.domain.User;
import com.atguigu.service.LendService;
import com.atguigu.service.impl.LendServiceImpl;
import com.atguigu.utility.WebUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/13 - 10:46
 */
public class LendServlet extends BaseServlet {
    LendService lendService = new LendServiceImpl();

    /*借阅*/
    protected void borrow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取借阅图书的用户id*/
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();
        /*获取该用户要借阅的图书id*/
        Integer bookId = WebUtility.parseInt(req.getParameter("bookId"), -1);
        /*创建借还记录*/
        lendService.addLend(userId, bookId);
    }

    /*还书*/
    protected void returnBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取借阅图书的用户id*/
        Integer userId = ((User) req.getSession().getAttribute("user")).getId();
        /*获取该用户要借阅的图书id*/
        Integer bookId = WebUtility.parseInt(req.getParameter("bookId"), -1);
        /*还书*/
        lendService.returnBook(userId, bookId);

    }

    /*查询个人的借书信息*/
    protected void getLandByUserId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*查看用户id*/
        Integer userId = ((User) req.getSession().getAttribute("user")).getId();
        /*调用业务层查询*/
        List<Lend> lends = lendService.queryLendByUserId(userId);
        /*发送到request域*/
        req.setAttribute("lends", lends);
        /*请求转发到用户的借还页面*/
        req.getRequestDispatcher("/pages/record/my_record.jsp").forward(req, resp);
    }

    /*获取所有人的借还记录*/
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*调用业务层查询*/
        List<Lend> lends = lendService.queryLends();
        /*发送到request域*/
        req.setAttribute("lends", lends);
        /*请求转发到所有用户的借还页面*/
        req.getRequestDispatcher("/pages/record/all_record.jsp").forward(req, resp);
    }

    /*删除借还记录*/
    protected void deleteLend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取要删除的id*/
        int id = WebUtility.parseInt(req.getParameter("id"), -1);
        /*调用业务层进行删除操作*/
        lendService.deleteLend(id);
        /*请求转发到所有人的借还记录页面*/
        req.getRequestDispatcher("lendServlet?action=list").forward(req,resp);
    }

}
