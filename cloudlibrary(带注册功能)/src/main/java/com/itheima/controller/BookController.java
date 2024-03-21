package com.itheima.controller;

import com.itheima.domain.Book;
import com.itheima.domain.User;
import com.itheima.entity.PageResult;
import com.itheima.entity.Result;
import com.itheima.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/selectNewbooks")
    public ModelAndView selectNewbooks() {
        ModelAndView modelAndView = new ModelAndView();
        //数据和视图
        int pageNum = 1;
        int pageSize = 5;
        PageResult pageResult = bookService.selectNewBooks(pageNum, pageSize);
        modelAndView.addObject("pageResult", pageResult);
        modelAndView.setViewName("books_new");
        return modelAndView;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Result<Book> findById(String id) {
        Book book = bookService.findById(id);
        return new Result<>(true, "", book);
    }

    @RequestMapping("/borrowBook")
    @ResponseBody
    public Result borrowBook(Book book, HttpSession session) {
        //设置借阅人信息
        User user = (User) session.getAttribute("USER_SESSION");
        book.setBorrower(user.getName());
        //调用service
        Integer num = bookService.borrowBook(book);
        if (num > 0) {
            //借阅成功
            return new Result(true, "借阅成功");
        } else {
            //借阅失败
            return new Result(false, "借阅失败");
        }
    }

    @RequestMapping("/search")
    public ModelAndView search(Book book, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        //调用Service
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageResult pageResult = bookService.search(book, pageNum, pageSize);
        //页面
        modelAndView.setViewName("books");
        //数据
        //搜索框信息回显
        modelAndView.addObject("search", book);
        //分页数据信息
        modelAndView.addObject("pageResult", pageResult);
        //当前页码数
        modelAndView.addObject("pageNum", pageNum);
        //分页请求再次请求的地址
        modelAndView.addObject("gourl", request.getRequestURI());
        return modelAndView;
    }

    @RequestMapping("/addBook")
    @ResponseBody
    public Result addBook(Book book) {
        Integer num = bookService.addBook(book);
        if (num > 0) {
            //添加成功
            return new Result(true, "新增图书成功");
        } else {
            //添加失败
            return new Result(false, "新增图书失败");
        }
    }

    @RequestMapping("/editBook")
    @ResponseBody
    public Result editBook(Book book) {
        Integer num = bookService.editBook(book);
        if (num > 0) {
            //成功
            return new Result(true, "编辑图书成功");
        } else {
            //失败
            return new Result(false, "编辑图书失败");
        }
    }

    @RequestMapping("/searchBorrowed")
    public ModelAndView searchBorrowed(Book book, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        //调用Service
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        PageResult pageResult = bookService.searchBorrowed(book, user, pageNum, pageSize);
        //页面
        modelAndView.setViewName("book_borrowed");
        //数据
        //搜索框信息回显
        modelAndView.addObject("search", book);
        //分页数据信息
        modelAndView.addObject("pageResult", pageResult);
        //当前页码数
        modelAndView.addObject("pageNum", pageNum);
        //分页请求再次请求的地址
        modelAndView.addObject("gourl", request.getRequestURI());
        return modelAndView;
    }

    @RequestMapping("/returnBook")
    @ResponseBody
    public Result returnBook(String id, HttpSession session) {
        User user = (User) session.getAttribute("USER_SESSION");
        boolean flag = bookService.returnBook(id, user);
        if (flag) {
            return new Result(true, "还书确认中，请自行前往归还");
        } else {
            return new Result(false, "归还失败");
        }
    }

    @RequestMapping("/returnConfirm")
    @ResponseBody
    public Result returnConfirm(String id) {
        boolean flag = bookService.confirmBook(id);
        if (flag) {
            return new Result(true, "确认成功");
        } else {
            return new Result(false, "确认失败");
        }
    }
}
