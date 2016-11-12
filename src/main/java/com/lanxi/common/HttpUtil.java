package com.lanxi.common;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

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
			// TODO 超时时间10分钟
			conn.setConnectTimeout(600000);
			conn.setReadTimeout(600000);
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

	/**
	 * 发送键值对
	 * @param params	键值对
	 * @param url		目标url
	 * @param charset	编解码字符集
	 * @return
	 */
	public static String postKeyValue(Map<String, String> params,String url,String charset){
		try {
		HttpClient client=HttpClientBuilder.create().build();
			HttpPost   post  =new HttpPost(url);
			List<NameValuePair> keyValue=new ArrayList<>();
			for(Map.Entry<String, String> each:params.entrySet())
				keyValue.add(new BasicNameValuePair(each.getKey(), each.getValue()));
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(keyValue);
			entity.setContentEncoding(charset);
			post.setEntity(entity);
			HttpResponse res=client.execute(post);
			
			BufferedReader buffReader=new BufferedReader(new InputStreamReader(res.getEntity().getContent(),charset));
			StringBuffer strBuff=new StringBuffer();
			String temp=null;
			while((temp=buffReader.readLine())!=null)
				strBuff.append(temp);
			return strBuff.toString();

		} catch (Exception e) {
			throw new AppException("发送键值对异常", e);
		}
	}
}
