package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
}
