package com.lanxi.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lanxi.entity.Activity;

public interface UserService {
	/**
	 * 智能筛选用户
	 * @param req
	 * @return
	 */
	public Map<String, Object> autoFilter(HttpServletRequest req);
	/**
	 * 手动筛选用户
	 * @param req
	 * @return
	 */
	public Map<String, Object> artificialFilter(HttpServletRequest req);
	/**
	 * 导出活动所有用户
	 * @param req
	 * @return
	 */
	public HSSFWorkbook exportUser(HttpServletRequest req);
	/**
	 * 导出活动有效用户
	 * @param req
	 * @return
	 */
	public HSSFWorkbook exportUsefulUser(HttpServletRequest req);
	/**
	 * 导出活动成功用户
	 * @param req
	 * @return
	 */
	public HSSFWorkbook exportSuccessUser(HttpServletRequest req);
}
