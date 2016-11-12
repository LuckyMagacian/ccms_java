package com.lanxi.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
	/**
	 * 只能筛选用户
	 * @param req
	 * @return
	 */
	public Map<String, Object> autoFilter(HttpServletRequest req);
	
	public Map<String, Object> artificialFilter(HttpServletRequest req);
}
