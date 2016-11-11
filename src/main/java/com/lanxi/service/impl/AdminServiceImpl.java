package com.lanxi.service.impl;

import com.lanxi.dao.AdminDao;
import com.lanxi.entity.Admin;
import com.lanxi.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    AdminDao adminDao;

    @Override
    public void addAdmin(Admin admin) {
        adminDao.addUser(admin);
    }

    @Override
    public String getMaxId() {
        return adminDao.getMaxId();
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminDao.getAllUser();
    }

    @Override
    public void updateAdmin(Admin admin) {

        adminDao.updateUser(admin);
    }

    @Override
    public void deleteAdmin(String userName) {
        adminDao.deleteUser(userName);
    }

    @Override
    public int getCount(String userName) {

        return adminDao.getCount(userName);
    }

    @Override
    public Admin getByUserName(String userName) {

        return adminDao.getByUserName(userName);
    }
}
