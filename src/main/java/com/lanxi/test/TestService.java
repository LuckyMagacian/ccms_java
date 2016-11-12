package com.lanxi.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lanxi.common.AppException;
import com.lanxi.service.DaoService;
import com.lanxi.service.SmsService;

public class TestService {
	private ApplicationContext ac;
	private DaoService		   dao;
	@Before
	public void init(){
		ac=new ClassPathXmlApplicationContext("spring-mvc.xml");
		dao=ac.getBean(DaoService.class);
		AppException.enTest();
	}
	@Test
	public void testInit(){
		System.out.println(ac);
		System.out.println(dao);
	}
	@Test
	public void testSendSms(){
		SmsService service=ac.getBean(SmsService.class);
		service.sendSms("15068610940", "短信发送测试");
	}
}
