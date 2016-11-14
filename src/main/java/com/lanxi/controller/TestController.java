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

import com.lanxi.common.AppMessage;

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
}
