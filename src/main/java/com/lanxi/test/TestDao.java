package com.lanxi.test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lanxi.common.BeanUtil;
import com.lanxi.common.ExcelUtil;
import com.lanxi.common.HttpUtil;
import com.lanxi.common.RandomUtil;
import com.lanxi.common.SmsUtil;
import com.lanxi.common.TimeUtil;
import com.lanxi.dao.ActivityDao;
import com.lanxi.dao.MsgDao;
import com.lanxi.dao.SelectedUserDao;
import com.lanxi.entity.Activity;
import com.lanxi.entity.Msg;
import com.lanxi.entity.SelectedUser;
import com.lanxi.service.ActivityService;
import com.lanxi.service.DaoService;
import com.lanxi.service.SmsService;
import com.lanxi.service.impl.DaoServiceImpl;
import com.sun.scenario.effect.AbstractShadow;

@SuppressWarnings("unused")
public class TestDao {

	private ApplicationContext ac;
	private DaoService		   dao;
	@Before
	public void init(){
		ac=new ClassPathXmlApplicationContext("spring-mvc.xml");
		dao=ac.getBean(DaoService.class);
	}
	@Test
	public void testInit(){
		System.out.println(ac);
		System.out.println(dao);
	}
	@Test
	public void testUserDao() throws Exception{
		String[] titleStr={"活动编号","活动批次","用户卡号","用户姓名","用户手机","报名方式","用户状态","活动结果"};
		Activity activity=new Activity();
		activity.setActv_no("1");
		activity.setBatch_no(1);
		//获取用户列表
		List<SelectedUser> users=dao.querySuccessUser(activity);
		//设置标题
		List<HSSFCell> titiles=new ArrayList<>();
		for(int i=0;i<8;i++){
			HSSFCell cell=ExcelUtil.getCell(0, 0, i);
			cell.setCellStyle(ExcelUtil.getTitleStyle());
			cell.setCellValue(titleStr[i]);
			titiles.add(cell);
		}
		//获取字段列表
		List<Field> fields=new ArrayList<>();
		Field[] arr=SelectedUser.class.getDeclaredFields();
		for(Field each:arr)
			if(!Modifier.isStatic(each.getModifiers()))
				fields.add(each);
		//设置单元格
		for(int i=0;i<users.size();i++)
			for(int j=0;j<fields.size();j++){
				HSSFCell cell=ExcelUtil.getCell(0, i+1, j);
				cell.setCellStyle(ExcelUtil.getCommonStyle());
				Field field=fields.get(j);
				field.setAccessible(true);
				String value=field.get(users.get(i))+"";
				
				if(field.getName().equals("custr_nbr"))
					value=value.substring(0,6)+value.substring(0,value.length()-10).replaceAll("\\w","*")+value.substring(value.length()-4);
				if(field.getName().equals("apply")){
					switch (value) {
					case "0":
						value="自动";
						break;
					case "1":
						value="主动报名";
						break;
					default:
						value="未报名";
						break;
					}
				}
				if(field.getName().equals("state")){
					switch (value) {
					case "0":
						value="有效用户";
						break;
					case "1":
						value="无效";
						break;
					}
				}
				if(field.getName().equals("result")){
					switch (value) {
					case "0":
					case "4":
						value="活动完成";
						break;
					case "1":
						value="失败";
						break;
					case "2":
						value="将完成";
						break;
					default:
						value="";
						break;
					}
				}
				cell.setCellValue(value);
			}
		
		ExcelUtil.generatorExcel("D:/");
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testWhat(){
//		List<Activity> list=dao.queryActivity(null, Activity.ACTIVITY_STATE_WAIT, null, null);
		
//		System.out.println(list);
//		System.out.println(list.get(0).getStart_date());
		Date date=new Date();
		System.out.println(date);
		System.out.println(date.getYear());
		System.out.println(date.getMonth());
		System.out.println(date.getDate());
		System.out.println(date.getDay());
		System.out.println(date.getTime());
	}
	
	@Test
	public void testDaoA(){
		ActivityDao adao=ac.getBean(ActivityDao.class);
		Activity activity=new Activity();
		activity.setActv_no("1");
		activity.setBatch_no(1);
		System.out.println(adao.queryActivity(activity));
	}
	@Test
	public void testMsgDao(){
		MsgDao msgDao=dao.getMsgDao();
		SmsService service=ac.getBean(SmsService.class);
		Msg msg=new Msg();
//		msg.setActv_no("1");
//		msg.setBatch_no(1);
//		msg.setContent("测试短信");
//		msg.setMsg_id(TimeUtil.getDateTime()+RandomUtil.getRandomNumber(6));
//		msg.setPhone("15757135741");
//		msg.setMsg_type(Msg.MSG_TYPE_PROGRESS);
//		msg.setSend_state(Msg.MSG_SEND_STATE_READY);
//		msgDao.addMsg(msg);
//		msg=msgDao.selectMsg(msg).get(0);
//		System.out.println(msg);
//		System.out.println(service.sendSms(msg.getPhone(), msg.getContent()));
//		msg.setSend_time(TimeUtil.getDateTime());
//		msg.setSend_state(Msg.MSG_SEND_STATE_SEND);
//		msgDao.updateMsg(msg);
		System.out.println(msgDao.queryNeedSend(null));
	}
}
