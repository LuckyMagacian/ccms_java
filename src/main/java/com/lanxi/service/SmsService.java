package com.lanxi.service;

import javax.servlet.http.HttpServletRequest;

import com.lanxi.entity.TempSms;

public interface SmsService {
	public String sendSms(String phone,String content);
	public String sendSms(TempSms sms);
	public String sendSms(HttpServletRequest req);
}
