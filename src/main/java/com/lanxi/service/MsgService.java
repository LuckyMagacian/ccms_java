package com.lanxi.service;

import com.lanxi.entity.Activity;
import com.lanxi.entity.Msg;
import com.lanxi.entity.TempSms;

public interface MsgService {
	/**
	 * 生成短信id
	 * @return
	 */
	public String 	makeId();
	/**
	 * 生成短信实体
	 * 生成的实体没有发送时间,没有短信类型
	 * @param activity
	 * @param phone
	 * @param content
	 * @return
	 */
	public Msg 		makeMsg(Activity activity,String phone,String content);
	/**
	 * 转临时短信
	 * @param msg
	 * @return
	 */
	public TempSms 	toSms(Msg msg);
	/**
	 * 发送短信
	 * @param msg
	 * @return
	 */
	public String  	sendMsg(Msg msg);
}
