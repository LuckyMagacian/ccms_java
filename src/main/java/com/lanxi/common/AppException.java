package com.lanxi.common;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class AppException extends RuntimeException{
	private static Logger  logger=Logger.getLogger(AppException.class);
	private static boolean testFlag=true;
	@SuppressWarnings("unused")
	private AppException() {
		super();
		logger.error("异常信息:"+getMessage());
		logger.error("异常原因:"+getStackInfo());
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
		logger.error("异常信息:"+getMessage());
		logger.error("异常原因:"+getStackInfo());
	}
	
	public AppException(String message) {
		super(message);
		logger.error("异常信息:"+getMessage());
	}
	/**
	 * 获取堆栈信息
	 * @return 
	 */
	public String getStackInfo(){
		StringWriter stringWriter=new StringWriter();
		PrintWriter  printWriter =new PrintWriter(stringWriter);
		getCause().printStackTrace(printWriter);
		printWriter.close();
		return stringWriter.toString();
	}
	/**
	 * 启用测试模式
	 * 测试模式:会直接在控制台输出堆栈信息
	 */
	public static void enTest(){
		testFlag=true;
	}
	/**
	 * 关闭测试模式
	 * 测试模式:会直接在控制台输出堆栈信息
	 */
	public static void unTest(){
		testFlag=false;
	}
	/**
	 * 查看测试模式状态
	 * @return 	true ->测试模式开启
	 * 			false->测试模式关闭
	 */
	public static boolean getTestFlag(){
		return testFlag;
	}
	
}
