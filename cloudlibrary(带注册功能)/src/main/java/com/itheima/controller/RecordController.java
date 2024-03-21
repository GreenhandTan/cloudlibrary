package com.itheima.controller;

import com.itheima.domain.Record;
import com.itheima.domain.User;
import com.itheima.entity.PageResult;
import com.itheima.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @RequestMapping("/searchRecords")
    public ModelAndView searchRecords(Record record, HttpServletRequest request, Integer pageNum, Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        //没有值初始化默认值
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageResult pageResult = recordService.searchRecords(record, user, pageNum, pageSize);
        //页面
        modelAndView.setViewName("record");
        //数据
        modelAndView.addObject("search", record);
        //分页信息
        modelAndView.addObject("pageResult", pageResult);
        //当前页码数
        modelAndView.addObject("pageNum", pageNum);
        //地址
        modelAndView.addObject("gourl", request.getRequestURI());
        return modelAndView;
    }
}
