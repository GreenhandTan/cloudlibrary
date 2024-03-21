package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/toMainPage")
    public String toMainPage() {
        return "main";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "login";
    }

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request) {
        User dbUser = userService.login(user);
        if (dbUser == null) {
            //登陆失败
            request.setAttribute("msg", "用户名或者密码错误");
            return "login";
        } else {
            //登陆成功
            request.getSession().setAttribute("USER_SESSION", dbUser);
            return "redirect:/admin/main.jsp";
        }
    }

    @RequestMapping("/register")
    public void addUser(String user_name, String user_password, String user_email, String user_role, HttpServletResponse response) throws IOException {
        Integer num = -1;
        if (user_name == "" || user_password == "" || user_email == "" || user_role == "") {
            //信息为空
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('Fail!');");
            out.println("window.history.back(-1);");
            out.println("</script>");
            out.close();
        } else {
            num = userService.addUser(user_name, user_password, user_email, user_role);
        }
        if (num > 0) {
            //添加成功
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('Success!');");
            out.println("window.location.href='/Bookstore/admin/login.jsp'");
            out.println("</script>");
            out.close();
        }
    }
}
