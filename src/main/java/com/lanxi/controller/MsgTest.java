package com.lanxi.controller;

import com.lanxi.common.HttpUtil;
import com.lanxi.common.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/14.
 */
public class MsgTest {
    public static void main(String[] args) {
        String url="http://localhost:8080/ccms_java/msg/msg.receive";
        String content = "actv_No=2,bacth_No=1";
        Map<String,String> map=new HashMap();
        map.put("content",content);
        map.put("mobile","18368093686");
        HttpUtils.getJsonByPost(url,map,"utf-8");
       //HttpUtil.postStr(content,url,"utf-8");

    }
}
