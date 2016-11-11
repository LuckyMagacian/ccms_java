package com.lanxi.controller;

import com.lanxi.entity.Admin;
import com.lanxi.service.AdminService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/11/7.
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    private static Logger logger = Logger.getLogger(AdminController.class);
    @Resource
    AdminService adminService;

    //添加用户
    @RequestMapping("/admin.add")
    @ResponseBody
    public Map AddUser(HttpSession session,HttpServletRequest servletRequest) {
        Map<String, Object> map = new HashMap<>();
        //得到登录用户的类型
        String type=(String)session.getAttribute("admin_Type");
        if(!type.equals("系统管理员")){
            map.put("message","您不是管理员，没有权限");
            return map;
        }
        String admin_Type = servletRequest.getParameter("admin_Type").trim();
        String username = servletRequest.getParameter("username").trim();
        String password = servletRequest.getParameter("password").trim();
        //检查表中是否已经存在该用户名
        int count = adminService.getCount(username);
        if (count > 0) {
            map.put("message", "用户名已经存在");
            return map;
        }
        //获得表中id最大值
        String idMax = adminService.getMaxId();
        int id = Integer.parseInt(idMax) + 1;
        String admin_Id = id + "";
        Admin admin = new Admin();
        admin.setAdmin_Id(admin_Id);
        admin.setAdmin_Type(admin_Type);
        admin.setPassWord(password);
        admin.setUserName(username);
        logger.info(" admin_type=" + admin_Type + " username=" + username + " password=" + password + " admin_Id=" + admin_Id);
        try {
            adminService.addAdmin(admin);
            map.put("statusCode", 200);
        } catch (Exception e) {
            map.put("statusCode", 300);
            logger.error("AdminController-add" + e.getMessage(), e);
        } finally {
            return map;
        }

    }

    //获取用户列表
    @RequestMapping("/admin.getAllAdmin")
    @ResponseBody
    public Map getAllAdmin() {
        Map<String, Object> map = new HashMap<>();
        List<Admin> adminList = adminService.getAllAdmin();
        try {
            map.put("adminList", adminList);
            map.put("statusCode", 200);
        } catch (Exception e) {
            map.put("statusCode", 300);
            logger.error("AdminController-getAllAdmin" + e.getMessage(), e);
        } finally {
            return map;
        }
    }

    //修改用户
    @RequestMapping("/admin.update")
    @ResponseBody
    public Map updateAdmin(HttpServletRequest servletRequest,HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        //得到登录用户的类型
        String type=(String)session.getAttribute("admin_Type");
        if(!type.equals("系统管理员")){
            map.put("message","您不是管理员，没有权限");
            return map;
        }
        Admin admin = new Admin();
        String username = servletRequest.getParameter("username").trim();
        String admin_Type = servletRequest.getParameter("admin_Type").trim();
        String password = servletRequest.getParameter("password").trim();
        admin.setUserName(username);
        admin.setAdmin_Type(admin_Type);
        admin.setPassWord(password);
        try {
            adminService.updateAdmin(admin);
            map.put("statusCode", 200);
        } catch (Exception e) {
            map.put("statusCode", 300);
            logger.error("AdminController-update" + e.getMessage(), e);
        } finally {
            return map;
        }
    }

    //删除用户
    @RequestMapping("/admin.delete")
    @ResponseBody
    public Map deleteAdmin(HttpServletRequest servletRequest,HttpSession session) {
        Map<String, Object> map = new HashMap<>();
       // 得到登录用户的类型
        String type=(String)session.getAttribute("admin_Type");
        String name=(String)session.getAttribute("username");
        if(!type.equals("系统管理员")){
            map.put("message","您不是管理员，没有权限");
            return map;
        }
        String username = servletRequest.getParameter("username").trim();
        if(username==name){
            map.put("message","您不能删除自己");
            return map;
        }
        try {
            adminService.deleteAdmin(username);
            map.put("statusCode", 200);
            map.put("message", "删除成功");
        } catch (Exception e) {
            map.put("statusCode", 300);
            map.put("message", "删除失败");
            logger.error("AdminController-delete" + e.getMessage(), e);
        } finally {
            return map;
        }
    }

}
