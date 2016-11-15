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
    @SuppressWarnings("finally")
	@RequestMapping("/admin.add")
    @ResponseBody
    public Map<String,Object> AddUser(HttpSession session, HttpServletRequest servletRequest) {
        Map<String, Object> map = new HashMap<>();
        //得到登录用户的类型
        try {
            String type = (String) session.getAttribute("admin_Type");
            if (!type.equals("1")) {
                map.put("message", "您不是管理员，没有权限");
                return map;
            }
        } catch (Exception e) {
            map.put("message", "获取身份类型失败");
            return map;
        }
        String admin_Type = null;
        String username = null;
        String password = null;
        try {
            admin_Type = servletRequest.getParameter("admin_Type").trim();
            username = servletRequest.getParameter("username").trim();
            password = servletRequest.getParameter("password").trim();
        } catch (Exception e) {
            map.put("message", "获取参数失败");
            return map;
        }
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
        admin.setPassword(password);
        admin.setUsername(username);
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
    @SuppressWarnings("finally")
	@RequestMapping("/admin.getAllAdmin")
    @ResponseBody
    public Map<String, Object> getAllAdmin() {
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
    @SuppressWarnings("finally")
	@RequestMapping("/admin.update")
    @ResponseBody
    public Map<String, Object> updateAdmin(HttpServletRequest servletRequest, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            //得到登录用户的类型
            String type = (String) session.getAttribute("admin_Type");
            if (!type.equals("1")) {
                map.put("message", "您不是管理员，没有权限");
                return map;
            }
        } catch (Exception e) {
            map.put("message", "获取身份类型失败");
            return map;
        }
        Admin admin = new Admin();
        String username = null;
        String password = null;
        String admin_Id = null;
        try {
            username = servletRequest.getParameter("username").trim();
            admin_Id = servletRequest.getParameter("admin_Id");
            password = servletRequest.getParameter("password").trim();
        } catch (Exception e) {
            map.put("message", "获取参数失败");
            return map;
        }
        admin.setAdmin_Id(admin_Id);
        admin.setUsername(username);
        admin.setPassword(password);
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
    @SuppressWarnings("finally")
	@RequestMapping("/admin.delete")
    @ResponseBody
    public Map<String, Object> deleteAdmin(HttpServletRequest servletRequest, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        // 得到登录用户的类型
        String name = null;
        String type=null;
        try {
            type = (String) session.getAttribute("admin_Type");
            name = (String) session.getAttribute("username");
            if (!type.equals("1")) {
                map.put("message", "您不是管理员，没有权限");
                return map;
            }
        } catch (Exception e) {
            map.put("message", "获取身份失败");
            return map;
        }
        String username = null;
        String admin_Id = null;
        try {
            username = servletRequest.getParameter("username").trim();
            admin_Id = servletRequest.getParameter("admin_Id").trim();
        } catch (Exception e) {
            map.put("message", "获取参数失败");
            return map;
        }
        if (username == name) {
            map.put("message", "您不能删除自己");
            return map;
        }
        try {
            adminService.deleteAdmin(admin_Id);
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

    //得到session里面的数据
    @SuppressWarnings("finally")
	@RequestMapping("/admin.getSessionMessage")
    @ResponseBody
    public Map<String, Object> getSessionMessage(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Admin admin = (Admin) session.getAttribute("admin");
            String username = (String) session.getAttribute("username");
            String admin_Type = (String) session.getAttribute("admin_Type");
            map.put("username", username);
            map.put("admin", admin);
            map.put("admin_Type", admin_Type);
            map.put("statusCode", 200);
        } catch (Exception e) {
            map.put("message", "获取信息失败");
            map.put("statusCode", 300);
            logger.error("AdminController-getSessionMessage" + e.getMessage(), e);
        } finally {
            return map;
        }
    }

}
