package com.lanxi.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanxi.common.AppException;
import com.lanxi.entity.Select;
import com.lanxi.common.RequestUtil;
import com.lanxi.service.SelectService;
import com.lanxi.service.SmsService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/9.
 */
@Controller
@RequestMapping("msg")
public class MsgController {

    private static Logger logger = Logger.getLogger(MsgController.class);
    @Resource
    SelectService selectService;
    @Resource
    SmsService smsService;

//   public static void main(String[] args) {
//        String params = "[{ phone:15757135754,  param: [p1,p2,p3] }," +
//                "{phone:15757135754, param:[p4,p5,p6]}," +
//                "{phone:15757135754, param:[p7,p8,p9]}," +
//                "{phone:15757135754, param:[p10,p11,p12]}," +
//                "{phone:15757135754, param:[p13,p14,p15]}" +
//                "]";
//        Type type = new TypeToken<ArrayList<Map<String, Object>>>() {
//
//        }.getType();
//        ArrayList<Map<String, Object>> jsonObjects = new Gson().fromJson(params, type);
//        for (Map<String, Object> map : jsonObjects) {
//            double phone = (double) map.get("phone");
//            ArrayList<String> paramss = (ArrayList) map.get("param");
//            StringBuffer stringBuffer = new StringBuffer();
//            stringBuffer.append("aaa").append(paramss.get(1)).append("bbb").append(paramss.get(2));
//            System.out.println(stringBuffer.toString());
//            //System.out.println(paramss);
//            // System.out.println(phone);
//            // System.out.println(new DecimalFormat("0").format(phone));
//        }
//
//        // System.out.println(jsonObjects);
//    }


    //192.168.17.122:8080/msg/mag.receive content="actv_No=1,bacth_No=1"
    //短信上行
    @RequestMapping("/msg.receive")
    @ResponseBody
    public Map receiveMsg(HttpServletRequest servletRequest) {
        Map<String, String> map = new HashMap<>();
        try {
            //得到传过来的map
            // String content=getStr(servletRequest);
            map = RequestUtil.getFromsRequest(servletRequest);
            logger.info("得到的信息" + map);
            String content = map.get("content");
            //content="actv_No=XXXX,bacth_No=XXXX"
            String actv = content.split(",")[0];
            String actv_No = actv.split("=")[1];

            String batch = content.split(",")[1];
            String batch_no = batch.split("=")[1];
            int batch_No = Integer.parseInt(batch_no);

            String phone = map.get("mobile");

            Select select = new Select();
            select.setActv_No(actv_No);
            select.setBatch_No(batch_No);
            select.setPhone(phone);
            //修改表中的apply的值
            selectService.updateApply(select);
            //回复用户
            String message = "报名成功";
            smsService.sendSms(phone, message);
            map.put("retCode:0000", "retDesc:发送成功");
        } catch (Exception e) {
            map.put("message", "发送失败");
            logger.error("MsgController-receive" + e.getMessage(), e);
        } finally {
            logger.info("回复内容"+map);
            return map;
        }
    }

    public String getStr(HttpServletRequest req) {
        try {
            req.setCharacterEncoding("utf-8");
            InputStream is = req.getInputStream();
            InputStreamReader reader = new InputStreamReader(is, "utf-8");
            BufferedReader buffReader = new BufferedReader(reader);
            String temp = null;
            StringBuffer strBuff = new StringBuffer();
            while ((temp = buffReader.readLine()) != null) {
                strBuff.append(temp);
            }
            System.out.println(strBuff);
            return strBuff.toString();
        } catch (Exception e) {
            throw new AppException("接收字符串异常", e);
        }
    }
}
