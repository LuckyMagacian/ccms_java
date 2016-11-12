package com.lanxi.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanxi.service.SmsService;

@Controller
@RequestMapping("/sms")
public class SmsController {
	@Resource
	SmsService smsService;
	@RequestMapping("/sendSms.do")
	public String sendSms(HttpServletRequest req){
		return smsService.sendSms(req);
	}
}
