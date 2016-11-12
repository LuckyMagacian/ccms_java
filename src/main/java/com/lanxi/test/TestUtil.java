package com.lanxi.test;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.lanxi.common.ConfigUtil;
import com.lanxi.common.ExcelUtil;
import com.lanxi.common.RandomUtil;
import com.lanxi.common.SmsUtil;
import com.lanxi.common.TimeUtil;
import com.lanxi.entity.TempSms;

public class TestUtil {

	
	@Test
	public void testPostSms(){
		ConfigUtil.loadProperties(TestUtil.class.getClassLoader().getResourceAsStream("cmss.properties"));
		TempSms sms=new TempSms();
		sms.setContent("短信发送测试");
		sms.setMchtId("10");
		sms.setMobile("15068610940");
		sms.setOrderId("10"+TimeUtil.getDateTime()+RandomUtil.getRandomNumber(4));
		sms.setTdId("1");
		sms.setTradeDate(TimeUtil.getDate());
		sms.setTradeTime(TimeUtil.getTime());
		System.out.println(SmsUtil.postSms(sms));
		System.out.println(sms);
	}
	 @Test
    public void testExcel() throws IOException{
		 System.out.println(ExcelUtil.getRow(0, 0));
		 System.out.println(ExcelUtil.getRow(0, 0));
		 System.out.println(ExcelUtil.getCell(0, 0,0));
		 System.out.println(ExcelUtil.getCell(0, 0,0));
		 System.out.println(ExcelUtil.getRow(0, 0));
		 System.out.println(ExcelUtil.getCell(0, 0,0));

	 }
    
    @Test
    public void testGetExcel(){
    	System.out.println(ExcelUtil.getCell(1,10,1));
    }
}
