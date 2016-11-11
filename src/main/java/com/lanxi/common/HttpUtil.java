package com.lanxi.common;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

public class HttpUtil {
	public static final String defEnCharset="utf-8";/**默认编码字符集*/
	public static final String defDeCharset="utf-8";/**默认解码字符集*/
	/**
	 * 私有化构造方法 避免被实例化
	 */
	private HttpUtil(){
		
	};
	/**
	 * 发送请求
	 * @param content	请求内容
	 * @param outStream	输出流
	 * @param charset	编码字符集 默认utf-8
	 */
	private static void post(String content,OutputStream outStream,String charset){
			try {
				charset=charset==null?defEnCharset:charset;
				OutputStreamWriter writer;
				writer = new OutputStreamWriter(outStream, charset);
				PrintWriter printer=new PrintWriter(writer);
				printer.print(content);
				printer.close();
			} catch (Exception e) {
				throw new AppException("发送post请求异常", e);
			}
	}
	/**
	 * 接受请求
	 * @param inStream	输入流
	 * @param charset	解码字符集 默认utf-8
	 * @return			请求内容
	 */
	private static String receive(InputStream inStream,String charset){
		try {
			charset=charset==null?defDeCharset:charset;
			InputStreamReader reader=new InputStreamReader(inStream,charset);
			BufferedReader    buffReader=new BufferedReader(reader);
			String temp;
			StringBuffer reply=new StringBuffer();
			while((temp=buffReader.readLine())!=null)
				reply.append(temp);
			buffReader.close();
			return reply.toString();
		} catch (Exception e) {
			throw new AppException("接收数据异常", e);
		}
	}
	/**
	 * 通过给定的url发送内容,并返回接收方返回的内容
	 * @param content	发送的内容
	 * @param Url		接收方地址
	 * @param charset	编码解码字符集 默认utf-8
	 * @param type		发送内容格式
	 * @return			接收方返回的内容
	 */
	public static String post(String content,String Url,String charset,String type){
		try{
			charset=charset==null?defEnCharset:charset;
			URL url =new URL(Url);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(6000);
			conn.setReadTimeout(6000);
			if(type!=null)
				conn.setRequestProperty("Content-Type",type+";Charset="+charset);
			conn.connect();
			post(content, conn.getOutputStream(), charset);
			if(conn.getResponseCode()==200)
				return receive(conn.getInputStream(), charset);
		}catch (Exception e) {
			throw new AppException("发送post请求异常", e);
		}
		return null;
	}
	/**
	 * 通过给定的url发送内容,并返回接收方返回的内容
	 * @param content	发送的内容
	 * @param res		Servlet的响应
	 * @param charset	编码解码字符集 默认utf-8
	 * @param type		发送内容格式
	 * @return			接收方返回的内容
	 */
	public static String post(String content,HttpServletResponse res,String charset,String type){
		try {
			charset=charset==null?defEnCharset:charset;
			res.setCharacterEncoding(charset);
			res.setContentType(type+";charset="+charset);
			post(content, res.getOutputStream(), charset);
			if(res.getStatus()==200)
				return "0";
		} catch (Exception e) {
			throw new AppException("发送xml文档异常", e);
		}
		return "1";
	}
	/**
	 * 发送字符串信息
	 * @param str		字符串内容
	 * @param url		接收方地址
	 * @param charset	编码字符集 默认 utf-8
	 * @return			接收方返回的内容
	 * @throws AppException 
	 */
	public static String postStr(String str,String url,String charset){
		return post(str, url,charset, null);
	}
	/**
	 * 发送字符串信息
	 * @param str		字符串内容
	 * @param res		servelet的响应
	 * @param charset	编码字符集 默认 utf-8
	 * @return			接收方返回的内容
	 * @throws AppException 
	 */
	public static String postStr(String str,HttpServletResponse res,String charset){
		return post(str, res, charset, "txt/html");
	}
	
	/**
	 * 发送xml文档数据
	 * @param 	xml 	xml字符串
	 * @param 	url		接收方的地址
	 * @param 	charset	编码字符集 默认utf-8
	 * @return 
	 */
	public static String postXml(String xml,String url,String charset){
		return post(xml,url,charset,"txt/html");
	}
	/**
	 * 发送xml文档数据
	 * @param 	xml 	xml字符串
	 * @param 	res		servelet的响应
	 * @param 	charset	编码字符集 默认utf-8
	 * @return 	0->发送成功 
	 * 			1->发送失败
	 */
	public static String postXml(String xml,HttpServletResponse res,String charset){
		return post(xml, res, charset,"txt/html");
	}

}
