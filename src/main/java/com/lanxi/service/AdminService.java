package com.lanxi.service;

import com.lanxi.entity.Admin;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */
public interface AdminService {
    //添加用户
    void addAdmin(Admin admin);

    //得到表中id的最大值
    String getMaxId();

    //得到用户列表
    List<Admin> getAllAdmin();

    //根据用户名修改用户、
    void updateAdmin(Admin admin);

    //删除用户
    void deleteAdmin(String username);

    //查询是否存在该用户名
    int getCount(String username);

    //根据用户名取出信息
    Admin getByUsername(String username);
}
