package com.itheima.mapper;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user where user_email=#{email} AND user_password=#{password} AND user_status!='1'")
    @Results(id = "userMap", value = {
            //id字段默认为false，表示不是主键
            //column表示数据库表字段，property表示持久化类属性名称。
            @Result(id = true, column = "user_id", property = "id"),
            @Result(column = "user_name", property = "name"),
            @Result(column = "user_password", property = "password"),
            @Result(column = "user_email", property = "email"),
            @Result(column = "user_role", property = "role"),
            @Result(column = "user_status", property = "status"),
    })
    User login(User user);
}

