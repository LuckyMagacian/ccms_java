package com.lanxi.entity;

/**
 * Created by Administrator on 2016/11/7.
 */
//管理员表
public class Admin {
    //管理员ID
    private String admin_Id;
    //管理员类型
    private String admin_Type;
    //用户名
    private String username;
    //密码
    private String password;

    public String getAdmin_Id() {
        return admin_Id;
    }

    public void setAdmin_Id(String admin_Id) {
        this.admin_Id = admin_Id;
    }

    public String getAdmin_Type() {
        return admin_Type;
    }

    public void setAdmin_Type(String admin_Type) {
        this.admin_Type = admin_Type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
