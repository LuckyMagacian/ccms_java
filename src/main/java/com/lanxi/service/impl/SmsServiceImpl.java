package com.lanxi.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.lanxi.common.AppException;
import com.lanxi.common.RandomUtil;
import com.lanxi.common.SmsUtil;
import com.lanxi.common.TimeUtil;
import com.lanxi.entity.TempSms;
import com.lanxi.service.SmsService;
@Service("smsService")
public class SmsServiceImpl implements SmsService {

	@Override
	public String sendSms(String phone, String content) {
		TempSms sms=new TempSms();
		sms.setMchtId("10");
		sms.setOrderId("10"+TimeUtil.getDateTime()+RandomUtil.getRandomNumber(4));
		sms.setTdId("1");
		sms.setTradeDate(TimeUtil.getDate());
		sms.setTradeTime(TimeUtil.getTime());
		sms.setContent(content);
		sms.setMobile(phone);
		return sendSms(sms);
	}

	@Override
	public String sendSms(TempSms sms) {
		return SmsUtil.postSms(sms);
	}

	@Override
	public String sendSms(HttpServletRequest req) {
		try{
			String content=req.getParameter("content");
			byte[] bytes=content.getBytes("ISO8859-1");
			content=new String(bytes, "utf-8");
			String phone=req.getParameter("phone");
			return sendSms(phone, content);
		}catch (Exception e) {
			throw new AppException("发送短信失败",e);
		}
	}

}
