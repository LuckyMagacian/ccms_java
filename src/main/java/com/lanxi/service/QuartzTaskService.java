package com.lanxi.service;

import javax.servlet.http.HttpServletRequest;

public interface QuartzTaskService {
	/**
	 * 活动控制
	 * @param req
	 */
	public Object controlActivity(HttpServletRequest req);
	/**
	 * 活动开始
	 * 定时任务
	 */
	public void startActivity();
	/**
	 * 活动结束
	 * 定时任务
	 */
	public void endActivity();
	/**
	 * 手动发送短信
	 * @param req
	 * @return
	 */
	public String sendMsg(HttpServletRequest req);
	/**
	 * 定时发送短信
	 */
	public void  sendMsg();
}
