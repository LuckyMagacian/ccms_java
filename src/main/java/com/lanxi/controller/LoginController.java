package com.lanxi.controller;

import com.lanxi.entity.Admin;
import com.lanxi.service.AdminService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7.
 */
@Controller
@RequestMapping("login")
public class LoginController {
    Logger logger = Logger.getLogger(LoginController.class);
    @Resource
    AdminService adminService;

    //登录页面
    @RequestMapping("/toLogin.do")
    @ResponseBody
    public Map login() {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("statusCode", 200);
            map.put("message", "登录的界面");
            return map;
        } catch (Exception e) {
            map.put("statusCode", 300);
            logger.error("loginController-toLogin" + e.getMessage(), e);
        } finally {
            return map;
        }
    }

    //登陆
    @RequestMapping("/login.do")
    @ResponseBody
    public Map login(HttpSession session, HttpServletRequest req, HttpServletResponse rep) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        //根据用户名从数据库中取出信息
        Admin admin = adminService.getByUsername(username);
        if (admin == null) {//验证用户名是否存在
            map.put("statusCode", 300);
            map.put("message", "用户名错误");
            return map;
        } else if (!admin.getPassword().equals(password)) {//验证密码是否正确
            map.put("statusCode", 300);
            map.put("message", "密码错误");
            return map;
        } else {//登陆
            String admin_Type = admin.getAdmin_Type();
            session.setAttribute("admin", admin);//session存储用户
            session.setAttribute("username", username);
            session.setAttribute("admin_Type", admin_Type);
            try {
                map.put("admin_Type", admin_Type);
                map.put("username", username);
                map.put("statusCode", 200);
                map.put("message", "登陆成功");
            } catch (Exception e) {
                map.put("statusCode", 300);
                logger.error("loginController-login.do" + e.getMessage(), e);
            } finally {
                return map;
            }
        }
    }

    //退出
    @RequestMapping("/loginOut.do")
    @ResponseBody
    public Map loginOut(HttpSession session) {
        session.invalidate();//注销
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("statusCode", 200);
            map.put("message", "重回登录界面");
        } catch (Exception e) {
            map.put("statusCode", 300);
            logger.error("loginController-loginOut" + e.getMessage(), e);
        } finally {
            return map;
        }
    }

    //退出页面
    @RequestMapping("/outLog.do")
    @ResponseBody
    public Map outLog(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("statusCode", 200);
            map.put("message", "退出的界面");
        } catch (Exception e) {
            map.put("statusCode", 300);
            logger.error("loginController-outLog.do" + e.getMessage(), e);
        } finally {
            return map;
        }
    }
}
