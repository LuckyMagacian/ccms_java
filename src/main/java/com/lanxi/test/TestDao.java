package com.lanxi.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import com.lanxi.dao.SelectedUserDao;
import com.lanxi.entity.Activity;
import com.lanxi.entity.SelectedUser;
import com.lanxi.service.ActivityService;
import com.lanxi.service.DaoService;
import com.lanxi.service.impl.DaoServiceImpl;

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
		List<SelectedUser> users=dao.querySuccessUser(activity);
//		System.out.println(users);
		List<HSSFCell> titiles=new ArrayList<>();
		for(int i=0;i<8;i++){
			HSSFCell cell=ExcelUtil.getCell(0, 0, i);
			cell.setCellStyle(ExcelUtil.getTitleStyle());
			cell.setCellValue(titleStr[i]);
			titiles.add(cell);
		}
//		for(SelectedUser each:users){
//			for(int i=1;i<users.size();i++){
//				HSSFRow row=ExcelUtil.getRow(0, i);
//				Map<String, Field> fields=BeanUtil.getFieldsNoStatic(SelectedUser.class);
//				int j=0;
//				for(Map.Entry<String, Field> one:fields.entrySet()){
//					HSSFCell cell=ExcelUtil.getCell(0, i, j);
//					cell.setCellStyle(ExcelUtil.getCommonStyle());
//					Field field=one.getValue();
//					System.out.println(field.getName());
//					field.setAccessible(true);
//					cell.setCellValue(field.get(each)+"");
//				}
//			}
//		}
		ExcelUtil.generatorExcel("D:/");
		
	}
	
	@Test
	public void testWhat(){
		System.out.println(dao.queryActivity(null, Activity.ACTIVITY_STATE_READY, null, null));
	}
}
