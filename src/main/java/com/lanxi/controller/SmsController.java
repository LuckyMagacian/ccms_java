package com.lanxi.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanxi.service.QuartzTaskService;
import com.lanxi.service.SmsService;

@Controller
@RequestMapping("/sms")
public class SmsController {
	@Resource
	SmsService smsService;
	@Resource
	QuartzTaskService taskService;
	@RequestMapping("/sendSms.do")
	@ResponseBody
	public String sendSms(HttpServletRequest req){
		return smsService.sendSms(req);
	}
	@RequestMapping("/sendMsg.do")
	@ResponseBody
	public String sendMsg(HttpServletRequest req){
		String rs=taskService.sendMsg(req);
		switch (rs) {
		case "1":
			rs="全部发送成功";
			break;
		case "2":
			rs="全部发送失败";
			break;
		case "3":
			rs="部分发送成功";
			break;
		default:
			rs="发生异常,返回值不在范围内";
			break;
		}
		return rs;
	}
}
