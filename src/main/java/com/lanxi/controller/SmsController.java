package com.lanxi.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanxi.service.SmsService;

@Controller
@RequestMapping("/sms")
public class SmsController {
	@Resource
	SmsService smsService;
	@RequestMapping("/sendSms.do")
	@ResponseBody
	public String sendSms(HttpServletRequest req){
		return smsService.sendSms(req);
	}
}
