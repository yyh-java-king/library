package com.atguigu.servlet;

import com.atguigu.domain.User;
import com.atguigu.domain.UserInfo;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utility.CookieUtils;
import com.atguigu.utility.DateUtils;
import com.atguigu.utility.WebUtility;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/8 - 11:31
 */
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取用户登入的用户名和密码*/
        User user = WebUtility.copyParamToBean(new User(), req.getParameterMap());
        /*查询数据库中是否有这个用户*/
        User user1 = userService.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (user1 == null) {
            /*账户密码有误时*/
            req.setAttribute("error", "密码不正确!");
            /*请求转发回去*/
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            /*密码输入正确时*/
            /*根据id获取该用户的信息*/
            UserInfo userInfo = userService.queryUserInfoById(user1.getId());
            /*将用户信息发送到session域中*/
            HttpSession session = req.getSession();
            session.setAttribute("user", user1);
            session.setAttribute("userInfo", userInfo);
            req.setAttribute("error", "");
            Cookie username;
            Cookie password;
            int time = 0;
            /*判断用户有没有点击保存密码按钮*/
            if (req.getParameter("remember") != null) {
                /*如果点击了就将用户名和密码发送到Cookie中*/
                username = new Cookie("username", user1.getUsername());
                password = new Cookie("password", user1.getPassword());
                /*设置时间*/
                time = 60 * 60 * 24 * 7;
            } else {
                /*获取cookie*/
                username = CookieUtils.getCookieByName(req.getCookies(), "username");
                password = CookieUtils.getCookieByName(req.getCookies(), "password");
            }
            /*设置Cookie的生命周期*/
            username.setMaxAge(time);
            password.setMaxAge(time);
            /*告知客户端*/
            resp.addCookie(username);
            resp.addCookie(password);
            /*请求重定向到主页面*/
            resp.sendRedirect("/library/pages/user/main.jsp");
        }
    }

    /*登入时的用户名的ajax请求*/
    protected void checkUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取用户名*/
        String username = req.getParameter("username");
        /*查询有没有该用户名*/
        Boolean bool = userService.queryUserByUsername(username);
        /*将结果转成JSON回传给客户端*/
        resp.getWriter().write(new Gson().toJson(bool));
    }

    /*修改时的用户名的ajax请求*/
    protected void update_add_ajax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取用户名*/
        String username = req.getParameter("username");
        Integer id = WebUtility.parseInt(req.getParameter("id"), -1);
        /*查询有没有该用户名*/
        Boolean bool = userService.queryUserByUsername(username, id);
        /*将结果转成JSON回传给客户端*/
        resp.getWriter().write(new Gson().toJson(bool));
    }


    /*修改用户信息*/
    protected void updateUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取要修改的信息并且封装成javaBean*/
        UserInfo userInfo = populateUserInfo(req.getParameterMap());
        userInfo = userService.updateUserInfo(userInfo);
        /*判断是否修改成功*/
        if (userInfo != null) {
            /*修改成功即更新session域中的数据*/
            req.getSession().setAttribute("userInfo", userInfo);
            /*请求转发到显示用户的信息界面*/
            req.getRequestDispatcher("/pages/user/my_data.jsp").forward(req, resp);
        } else {
            /* 发送失败信息到Request域中*/
            req.setAttribute("error", "修改失败!");
            /* 请求转发到修改信息页面*/
            req.getRequestDispatcher("/pages/user/update_my_data.jsp").forward(req, resp);
        }
    }

    /*修改密码*/
    protected void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*封装用户要修改的信息*/
        User user = WebUtility.copyParamToBean(new User(), req.getParameterMap());
        /*调用业务层修改密码*/
        User user1 = userService.updatePasswordById(user);
        /*判断是否修改成功*/
        if (user1 != null) {
            /*修改成功即更新session域的密码*/
            HttpSession session = req.getSession();
            User sessionUser = (User) session.getAttribute("user");
            /*修改密码*/
            sessionUser.setPassword(user1.getPassword());
            /*提示修改成功*/
            req.setAttribute("hint", "修改成功");
        } else {
            /*修改失败即提示修改失败*/
            req.setAttribute("hint", "修改失败");
        }
        /*请求转发到修改密码界面*/
        req.getRequestDispatcher("/pages/user/update_password.jsp").forward(req, resp);
    }

    /*获取用户的个人信息*/
    protected void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取查询的用户的id*/
        Integer id = WebUtility.parseInt(req.getParameter("id"), 0);
        /*调用业务层的方法进行查询*/
        UserInfo userInfo = userService.queryUserInfoById(id);
        User user = userService.queryUserById(id);
        /*发送到request域*/
        req.setAttribute("userInfo", userInfo);
        req.setAttribute("user", user);
        /*请求转发*/
        req.getRequestDispatcher("/pages/user/add_update_user.jsp").forward(req, resp);
    }

    /*查询所有用户以及账号密码*/
    protected void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取所有用户的信息并发送到request域*/
        req.setAttribute("userInfos", userService.queryUserInfos());
        req.setAttribute("users", userService.queryUsers());
        /*请求转发到所有图书界面*/
        req.getRequestDispatcher("/pages/user/user_data.jsp").forward(req, resp);
    }

    /*修改用户信息*/
    protected void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*封装用户要修改的信息*/
        User user = WebUtility.copyParamToBean(new User(), req.getParameterMap());
        UserInfo userInfo = populateUserInfo(req.getParameterMap());
        /*调用业务层修改信息*/
        User user1 = userService.updateUser(user);
        UserInfo userInfo1 = userService.updateUserInfo(userInfo);
        if (user1 != null && userInfo1 != null) {
            /*修改成功请求转发到全部读者页面*/
            req.getRequestDispatcher("userServlet?action=listUsers").forward(req, resp);
        } else {
            /*修改失败返回修改读者界面*/
            req.getRequestDispatcher("userServlet?action=getUser").forward(req, resp);
            /*并发送提示信息*/
            req.setAttribute("hint", "修改失败!");
        }
    }

    /*添加用户*/
    protected void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*封装用户要添加的信息*/
        User user = WebUtility.copyParamToBean(new User(), req.getParameterMap());
        UserInfo userInfo = populateUserInfo(req.getParameterMap());
        /*调用业务层的添加方法进行添加用户操作*/
        if (userService.addUser(user, userInfo)) {
            /*添加成功请求重定向到全部读者页面*/
            resp.sendRedirect("userServlet?action=listUsers");
        } else {
            /*修改失败返回修改读者界面*/
            req.getRequestDispatcher("userServlet?action=getUser").forward(req, resp);
            /*并发送提示信息*/
            req.setAttribute("hint", "添加失败!");
        }
    }

    /*删除用户*/
    protected void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取要删除的id*/
        Integer id = WebUtility.parseInt(req.getParameter("id"), -1);
        /*调用业务层进行删除操作*/
        userService.deleteUser(id);
        /*请求转发到所有读者界面*/
        req.getRequestDispatcher("userServlet?action=listUsers").forward(req, resp);
    }

    /**
     * 封装UserInfo的信息
     */
    private UserInfo populateUserInfo(Map<String, String[]> value) {
        UserInfo userInfo = new UserInfo();
        /*遍历map*/
        for (Map.Entry<String, String[]> entry : value.entrySet()) {
            switch (entry.getKey()) {
                case "id":
                    userInfo.setId(WebUtility.parseInt(entry.getValue()[0], 0));
                    break;
                case "name":
                    userInfo.setName(entry.getValue()[0]);
                    break;
                case "sex":
                    userInfo.setSex(entry.getValue()[0]);
                    break;
                case "birthday":
                    userInfo.setBirthday(DateUtils.parseDate(entry.getValue()[0]));
                    break;
                case "address":
                    userInfo.setAddress(entry.getValue()[0]);
                    break;
                case "phone":
                    userInfo.setPhone(entry.getValue()[0]);
                    break;
            }
        }
        return userInfo;
    }

}
