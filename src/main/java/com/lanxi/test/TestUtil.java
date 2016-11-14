package com.lanxi.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.lanxi.common.ConfigUtil;
import com.lanxi.common.ExcelUtil;
import com.lanxi.common.HttpUtil;
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
		sms.setMobile("15757135741");
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
    @Test
    public void testConfig(){
    	System.out.println(ConfigUtil.get("chooseTestUrl"));
    }
    @Test
    public void testPostKeyValue(){
    	Map<String, String> map=new HashMap<>();
		map.put("actv_no","1");
		map.put("batch_no","1");
		map.put("update_flag","1");
		String rs=HttpUtil.postKeyValue(map, ConfigUtil.get("chooseTestUrl"),"utf-8");
		System.out.println(rs);
    }
    @Test
    public void testPost(){
    	String url="http://192.168.17.122:8080/ccms_java/msg/msg.receive";
    	String content = "actv_No=2,bacth_No=1";
    	String mobile="18368093686";
    	Map<String, Object>map=new HashMap<>();
    	map.put("content",content);
    	map.put("mobile",mobile);
    	System.out.println(HttpUtil.postStr("mobile="+map.get("mobile")+"content="+map.get("content"), url, "utf-8"));
    }
}
