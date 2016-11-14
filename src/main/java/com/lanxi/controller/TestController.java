package com.lanxi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@RequestMapping("/test.do")
	@ResponseBody
	public List<String> testReturn(){
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		return list;
	}
	@RequestMapping("/testStr.do")
	@ResponseBody
	public String getStr(HttpServletRequest req) throws Exception{
		req.setCharacterEncoding("utf-8");
		InputStream in=req.getInputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(in, "utf-8"));
		String temp=99999+"";
		StringBuffer stringBuffer=new StringBuffer();
		while((temp=reader.readLine())!=null){
			System.out.println(temp);
			stringBuffer.append(temp);
		}
		System.out.println(stringBuffer);
		return stringBuffer.toString();
	}
}
