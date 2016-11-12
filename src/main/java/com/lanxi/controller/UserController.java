package com.lanxi.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanxi.common.AppException;
import com.lanxi.common.AppMessage;
import com.lanxi.service.UserService;
@RequestMapping("/user")
@Controller
public class UserController {
	@Resource
	private UserService userService;
	@SuppressWarnings("finally")
	@RequestMapping("/autoFilter.do")
	@ResponseBody
	public AppMessage autoFilter(HttpServletRequest req){
		AppMessage message=new AppMessage();
		try{
			Map<String, Object> map=userService.autoFilter(req);
			if(map!=null){
				message.setErrCode("0000");
				message.setErrMsg("自动筛选成功");
				message.setContent(map);
			}else{
				message.setErrCode("9998");
				message.setErrMsg("自动筛选失败");
			}
		}catch (Exception e) {
			message.setErrCode("9999");
			message.setErrMsg("系统错误,自动筛选失败");
		}finally {
			return message;
		}
	}
	@SuppressWarnings("finally")
	@RequestMapping("/artificialFilter.do")
	@ResponseBody
	public AppMessage artificialFilter(HttpServletRequest req){
		AppMessage message=new AppMessage();
		try{
			Map<String, Object> map=userService.artificialFilter(req);
			if(map!=null){
				message.setErrCode("0000");
				message.setErrMsg("手动筛选成功");
				message.setContent(map);
			}else{
				message.setErrCode("9998");
				message.setErrMsg("手动筛选失败");
			}
		}catch (Exception e) {
			message.setErrCode("9999");
			message.setErrMsg("系统错误,手筛选失败");
		}finally {
			return message;
		}
	}
	@RequestMapping("/exportUser.do")
	public void exportUser(HttpServletRequest req,HttpServletResponse res){
		try {
			HSSFWorkbook workbook=userService.exportUser(req);
			File file=new File("successUser.xls");
			workbook.write(file);
			FileInputStream fin=new FileInputStream(file);
			OutputStream os=res.getOutputStream();
			int temp;
			while((temp=fin.read())!=-1)
			os.write(temp);
			os.close();
		} catch (Exception e) {
			throw new AppException("导出所有用户异常", e);
		}
	}
	@RequestMapping("/exportUsefulUser.xls")
	public void exportUsefulUser(HttpServletRequest req,HttpServletResponse res){
		try {
			HSSFWorkbook workbook=userService.exportUsefulUser(req);
			File file=new File("successUser.xls");
			workbook.write(file);
			FileInputStream fin=new FileInputStream(file);
			OutputStream os=res.getOutputStream();
			int temp;
			while((temp=fin.read())!=-1)
			os.write(temp);
			os.close();
		} catch (Exception e) {
			throw new AppException("导出有效用户异常", e);
		}
	}
	@RequestMapping("/exportSuccessUser.xls")
	public void exportSuccessUser(HttpServletRequest req,HttpServletResponse res){
		try {
			HSSFWorkbook workbook=userService.exportSuccessUser(req);
			File file=new File("successUser.xls");
			workbook.write(file);
			FileInputStream fin=new FileInputStream(file);
			OutputStream os=res.getOutputStream();
			int temp;
			while((temp=fin.read())!=-1)
			os.write(temp);
			os.close();
		} catch (Exception e) {
			throw new AppException("导出成功用户异常", e);
		}
	}
	@RequestMapping("/testExportSuccessUser.xls")
	public void testExportSuccessUser(HttpServletRequest req,HttpServletResponse res){
		try {
			HSSFWorkbook workbook=userService.exportSuccessUser(req);
			File file=new File("successUser.xls");
			workbook.write(file);
			FileInputStream fin=new FileInputStream(file);
			OutputStream os=res.getOutputStream();
			int temp;
			while((temp=fin.read())!=-1)
			os.write(temp);
			os.close();
		} catch (Exception e) {
			throw new AppException("导出成功用户异常", e);
		}
	}
}
