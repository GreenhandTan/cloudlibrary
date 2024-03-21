package com.itheima.domain;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;       	    //用户id
    private String name;      	    //用户名称
    private String password;  		//用户密码
    private String email;     		//用户邮箱（用户账号）
    private String role;      		//用户角色
    private String status;    		//用户状态

    public User(String user_name, String user_password, String user_email, String user_role) {
        name = user_name;
        password = user_password;
        email = user_email;
        role = user_role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
