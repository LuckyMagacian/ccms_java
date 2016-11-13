package com.lanxi.controller;


import com.lanxi.entity.Select;
import com.lanxi.common.RequestUtil;
import com.lanxi.service.SelectService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    //短信上行
    @RequestMapping("/msg.receive")
    @ResponseBody
    public Map receiveMsg(HttpServletRequest servletRequest) {
        Map<String, String> map = new HashMap<>();
        try {
            //得到传过来的map
            map = RequestUtil.getFromsRequest(servletRequest);
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

            map.put("retCode" + ":" + "0000", "retDesc" + ":" + "发送成功");
        } catch (Exception e) {
            map.put("发送失败", e.getMessage());
            logger.error("MsgController-receive" + e.getMessage(), e);
        } finally {
            return map;
        }
    }

}
