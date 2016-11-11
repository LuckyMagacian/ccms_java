package com.lanxi.common;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;


public class SignUtil {
	//--------------------------------baseStart-------------------------------------------
	/**
	 * md5摘要算法 
	 * @param src 明文字节码
	 * @return
	 */
	public static byte[] md5En(byte[] src){
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes=md5.digest(src);
			return bytes;
		} catch (Exception e) {
			throw new AppException("md5摘要算法异常",e);
		}
	}
	/**
	 * md5摘要算法 补0
	 * @param src
	 * @return
	 * @throws AppException 
	 */
	public static String md5LowerCase(String src,String charset) {
		
		try {
			if(src==null || src.length()<1)
				throw new AppException("md5摘要时,明文信息为null或为空");
			byte b[] = md5En(src.getBytes(charset));
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();// 32位的加密
		} catch (Exception e) {
			throw new AppException("md5摘要算法异常",e);
		}
	}
	/**
	 * SHA-1摘要算法 
	 * @param src 	明文字节
	 * @return
	 */
	public static byte[] sha1En(byte[] src){
		try{
			MessageDigest sha1=MessageDigest.getInstance("SHA-1");
			return sha1.digest(src);
		}catch (Exception e) {
			throw new AppException("sha1摘要异常",e);
		}
	}
	/**
	 * SHA-1加密 
	 * @param src 		明文
	 * @param charset	字符集
	 * @return
	 */
	public static String sha1En(String src,String charset){
		try{
			byte[] bytes=sha1En(src.getBytes(charset));
			return byteToHex(bytes);
		}catch (Exception e) {
			throw new AppException("sha1摘要异常",e);
		}
	}
	/**
	 * base64加密
	 * @param src		明文
	 * @param charset	字符集
	 * @return
	 */
	public static String base64En(String src,String charset){
		try{
			if(src==null || src.length()<1)
				throw new AppException("bas64加密时,明文信息为null或为空");
			byte[] bytes=base64En(src.getBytes(charset));
			return new String(bytes,charset);
		}catch (Exception e) {
			throw new AppException("bas64加密异常",e);
		}
	}
	/**
	 * base64加密
	 * @param src		明文字节
	 * @return
	 */
	public static byte[] base64En(byte[] src){
		try{
			if(src==null || src.length<1)
				throw new AppException("bas64加密时,明文信息为null或为空");
			byte[] bytes=Base64.encodeBase64(src);
			return bytes;
		}catch (Exception e) {
			throw new AppException("bas64加密异常",e);
		}
	}
	/**
	 * base64解密
	 * @param src		明文
	 * @param charset	字符集
	 * @return
	 */
	public static String base64De(String src,String charset){
		try{
			if(src==null || src.length()<1)
				throw new AppException("bas64解密时,明文信息为null或为空");
			byte[] bytes=base64De(src.getBytes(charset));
			return new String(bytes, charset);
		}catch (Exception e) {
			throw new AppException("bas64解密异常",e);
		}
	}
	/**
	 * base64解密
	 * @param src		明文字节
	 * @return
	 */
	public static byte[] base64De(byte[] src){
		try{
			if(src==null || src.length<1)
				throw new AppException("bas64解密时,明文信息为null或为空");
			byte[] bytes=Base64.decodeBase64(src);
			return bytes;
		}catch (Exception e) {
			throw new AppException("bas64解密异常",e);
		}
	}
	/**
	 * byte数组转hex字符串
	 * @param bytes
	 * @return
	 */
	public static String byteToHex(byte[] bytes){
		try{
			if(bytes==null||bytes.length<1)
				throw new AppException("byte转Hex时,byte数组为null或为空");
			return new String(Hex.encodeHex(bytes));
		}catch (Exception e) {
			throw new AppException("byte转hex时异常",e);
		}
	}

//------------------------------------baseEnd--------------------------------------------	
	
//------------------------------------signStart------------------------------------------
	/**
	 * map转String  key=value&key2=value2 不排序 无签名
	 * @param map
	 * @return
	 */
	public static String mapToStringNoSign(Map<String, String> map){
		StringBuffer rs=new StringBuffer();
		for(Map.Entry<String, String> each:map.entrySet()){
			if(each.getKey().equals("sign"))
				continue;
			rs.append(each.getKey());
			rs.append("=");
			rs.append(each.getValue());
			rs.append("&");
		}
		return rs.toString().substring(0, rs.length()-1);
	}
	/**
	 * map转String  key=value&key2=value2 不排序  有签名
	 * @param map
	 * @return
	 */
	public static String mapToString(Map<String, String> map){
		StringBuffer rs=new StringBuffer();
		for(Map.Entry<String, String> each:map.entrySet()){
			rs.append(each.getKey());
			rs.append("=");
			rs.append(each.getValue());
			rs.append("&");
		}
		return rs.toString().substring(0, rs.length()-1);
	}
	
	/**
	 * map转String  key=value&key2=value2 key按字典排序 无签名
	 * @param map
	 * @return
	 */
	public static String mapSortToStringNoSign(Map<String, String> map){
		List<String> list=new ArrayList<String>(map.keySet());
		Collections.sort(list);
		StringBuffer rs=new StringBuffer();
		for(String each:list){
			if(each.equals("sign"))
				continue;
			rs.append(each);
			rs.append("=");
			rs.append(map.get(each));
			rs.append("&");
		}
		return rs.toString().substring(0, rs.length()-1);
	}
	
	/**
	 * map转String  key=value&key2=value2 key按字典排序 有签名
	 * @param map
	 * @return
	 */
	public static String mapSortToString(Map<String, String> map){
		List<String> list=new ArrayList<String>(map.keySet());
		Collections.sort(list);
		StringBuffer rs=new StringBuffer();
		for(String each:list){
			rs.append(each);
			rs.append("=");
			rs.append(map.get(each));
			rs.append("&");
		}
		return rs.toString().substring(0, rs.length()-1);
	}
	/**
	 * map转String 仅仅返回key拼接成的字符串
	 * @param map
	 * @return
	 */
	public static String mapToKeyString(Map<String, String> map){
		List<String> list=new ArrayList<String>(map.keySet());
		StringBuffer rs=new StringBuffer();
		for(String each:list){
			if(each.equals("sign"))
				continue;
			rs.append(each);
		}
		return rs.toString();
	}
	/**
	 * map转String 仅仅返回value拼接成的字符串
	 * @param map
	 * @return
	 */
	public static String mapToValueString(Map<String, String> map){
		StringBuffer rs=new StringBuffer();
		for(Map.Entry<String, String> each:map.entrySet()){
			if(each.getKey().equals("sign"))
				continue;
			rs.append(each.getValue());
		}
		return rs.toString();
	}
	/**
	 * 从对象中获取字段以及其对应的属性,封装成map并返回
	 * @param obj
	 * @return
	 */
	public static Map<String, String> objToMap(Object obj){
		Class<?> clazz=obj.getClass();
		Map<String, Field> fields=BeanUtil.getFieldsNoStatic(clazz);
		Map<String, String>map=new LinkedHashMap<String, String>();
		try{
			for(Map.Entry<String, Field> each:fields.entrySet()){
				String name=each.getKey();
				Field  field=each.getValue();
				field.setAccessible(true);
				map.put(name,field.get(obj)+"");
			}
			return map;
		}catch (Exception e) {
			throw new AppException("从对象中获取map异常",e);
		}
	}
//------------------------------------signEnd--------------------------------------------

}
