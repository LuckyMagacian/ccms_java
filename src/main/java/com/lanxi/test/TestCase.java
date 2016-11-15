package com.lanxi.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.lanxi.common.ExcelUtil;
import com.lanxi.common.SqlUtil;
import com.lanxi.entity.Activity;
import com.lanxi.entity.Admin;
import com.lanxi.entity.Chart;
import com.lanxi.entity.Msg;
import com.lanxi.entity.Prop;
import com.lanxi.entity.SelectedUser;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

/**
 * Created by 1 on 2016/11/8.
 */
public class TestCase {
    @SuppressWarnings("rawtypes")
	@Test
    public void testTest(){
        Class[] classes=new Class[]{Activity.class, Prop.class, Admin.class, Chart.class};

        Class   clazz=SelectedUser.class;
        String  tableName="T_CCMS_"+clazz.getSimpleName().toUpperCase();

        String sql=SqlUtil.createInsert(clazz,"T_CCMS_"+clazz.getSimpleName().toUpperCase());
        System.out.println(sql);
        System.out.println(sql.toUpperCase());

        sql=SqlUtil.createDelete(clazz,tableName);
        System.out.println(sql);
        System.out.println(sql.toUpperCase());

        sql=SqlUtil.createUpdate(clazz,tableName);
        System.out.println(sql);
        System.out.println(sql.toUpperCase());

        sql=SqlUtil.createSelect(clazz,tableName);
        System.out.println(sql);
        System.out.println(sql.toUpperCase());

        sql=SqlUtil.createResultMap(clazz);
        System.out.println(sql);
        System.out.println(sql.toUpperCase());
    }

    @Test
    public void testJson() throws Exception {
    	File file =new File("C:/Users/1/Documents/Tencent Files/1224194091/FileRecv/分期.json");
    	StringBuffer strBuff=new StringBuffer();
    	InputStreamReader reader=new InputStreamReader(new FileInputStream(file),"utf-8");
    	BufferedReader buffReader=new BufferedReader(reader);
    	String temp=null;
    	while((temp=buffReader.readLine())!=null)
    		strBuff.append(temp);
    	System.out.println(strBuff.toString());
    	
    	JSONObject jObj=JSONObject.parseObject(strBuff.toString());
//    	System.out.println(jObj);
//    	System.out.println(jObj.getClass());
//    	System.out.println(jObj.get("prop").getClass());
//    	System.out.println(jObj.get("ACTV_NO").getClass());
    	JSONArray array=(JSONArray) jObj.get("prop");
//    	System.out.println(array.size());
//    	System.out.println(array.get(0).getClass());
    	String propStr=array.toJSONString();
    	System.out.println(propStr);
    	JSONObject prop=JSONObject.parseObject(propStr);
    	System.out.println(prop.getClass());
    }
    
    @Test
    public void testToJson(){
    	List<String> list=new ArrayList<>();
    	list.add("1");
    	list.add("2");
    	list.add("3");
    	System.out.println(JSONObject.toJSONString(list));
    }
    @Test
    public void testStrR(){
    	String string="1234567891011121";
    	System.out.println(string.substring(0,6)+string.substring(0,string.length()-10).replaceAll("\\w","*")+string.substring(string.length()-4));
    }
    @Test
    public void testOr(){
    	Integer i=0;
    	i|=1;
    	System.out.println(i);
    	i|=2;
    	System.out.println(i);
    }
}
