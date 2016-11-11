package com.lanxi.dao;

import com.lanxi.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */
@Repository
public interface AdminDao {
    //添加用户
    void addUser(Admin admin);

    //得到最大值
    String getMaxId();

    //得到用户列表
    List<Admin> getAllUser();

    //根据用户名修改用户
    void updateUser(Admin admin);

    //删除用户
    void deleteUser(String userName);

    //查询是否存在该用户名
    int  getCount(String userName);

    //根据用户名取出信息
    Admin getByUserName(String userName);

}
