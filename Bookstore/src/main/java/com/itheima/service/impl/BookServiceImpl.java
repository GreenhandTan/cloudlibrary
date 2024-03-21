package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.Book;
import com.itheima.domain.Record;
import com.itheima.domain.User;
import com.itheima.entity.PageResult;
import com.itheima.mapper.BookMapper;
import com.itheima.service.BookService;
import com.itheima.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RecordService recordService;

    @Override
    public PageResult selectNewBooks(int pageNum, int pageSize) {
        //开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        // 调用dao
        Page<Book> page = bookMapper.selectNewBooks();
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public Book findById(String id) {
        return bookMapper.findById(id);
    }

    @Override
    public Integer borrowBook(Book book) {
        //设置借阅状态0可借阅1借阅中2归还中3已经下架
        book.setStatus("1");
        //设置借阅时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime = format.format(new Date());
        book.setBorrowTime(borrowTime);
        //调用mapper
        return bookMapper.editBook(book);
    }

    @Override
    public PageResult search(Book book, int pageNum, int pageSize) {
        //开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        //调用dao
        Page<Book> page = bookMapper.searchBooks(book);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public Integer addBook(Book book) {
        //设置上架时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String uploadTime = format.format(new Date());
        book.setUploadTime(uploadTime);
        return bookMapper.addBook(book);
    }

    @Override
    public Integer editBook(Book book) {
        return bookMapper.editBook(book);
    }

    @Override
    public PageResult searchBorrowed(Book book, User user, Integer pageNum, Integer pageSize) {
        //分页查询
        PageHelper.startPage(pageNum, pageSize);
        //设置借阅人
        book.setBorrower(user.getName());
        //区分用户类型
        Page<Book> page = null;
        if ("ADMIN".equals(user.getRole())) {
            page = bookMapper.selectBorrowed(book);
        } else {
            page = bookMapper.selectMyBorrow(book);
        }
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public boolean returnBook(String id, User user) {
        //判断当前用户与图书借阅者是否相同
        Book book = bookMapper.findById(id);
        boolean flag = book.getBorrower().equals(user.getName());
        //调用dao
        Integer num = 0;
        if (flag) {
            //相同用户
            book.setStatus("2");
            num = bookMapper.editBook(book);
        }
        return num > 0;
    }

    @Override
    public boolean confirmBook(String id) {
        Book book = bookMapper.findById(id);
        String borrower = book.getBorrower();
        String borrowTime = book.getBorrowTime();
        //设置状态为可借阅
        book.setStatus("0");
        //清空相关信息
        book.setBorrower("");
        book.setBorrowTime("");
        book.setReturnTime("");
        Integer num = bookMapper.editBook(book);
        if (num > 0) {
            //增加归还记录
            Record record = new Record();
            record.setBookisbn(book.getIsbn());
            record.setBookname(book.getName());
            record.setBorrower(borrower);
            record.setBorrowTime(borrowTime);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String remandTime = simpleDateFormat.format(new Date());
            record.setRemandTime(remandTime);
            recordService.addRecord(record);
        }
        return num > 0;
    }
}
