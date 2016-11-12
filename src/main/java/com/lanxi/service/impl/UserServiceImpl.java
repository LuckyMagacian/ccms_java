package com.lanxi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.lanxi.common.AppException;
import com.lanxi.common.AppMessage;
import com.lanxi.common.ConfigUtil;
import com.lanxi.common.HttpUtil;
import com.lanxi.entity.Activity;
import com.lanxi.entity.SelectedUser;
import com.lanxi.service.DaoService;
import com.lanxi.service.UserService;

public class UserServiceImpl implements UserService {
	@Resource
	DaoService daoService;
	@Override
	public Map<String, Object> autoFilter(HttpServletRequest req) {
		try{
			req.setCharacterEncoding("utf-8");
			String actv_no=req.getParameter("actv_no");
			String batch_no=req.getParameter("batch_no");
			Map<String, String> map=new HashMap<>();
			map.put("actv_no", actv_no);
			map.put("batch_no",batch_no);
			map.put("update_flag","0");
			// TODO 会等很久
			String rs=HttpUtil.postKeyValue(map,ConfigUtil.get(""),"utf-8");
			JSONObject jobj=JSONObject.parseObject(rs);
			String reCode=jobj.getString("result_code");
			if("0".equals(reCode)){
				String reMsg =jobj.getString("result_msg");
				String suggestion=jobj.getString("suggesstion");
				Activity activity=daoService.getActivityByIdAndBatchNo(actv_no, Integer.parseInt(batch_no));
				activity.setSuggestion(suggestion);
				daoService.getActivityDao().updateActivity(activity);
				SelectedUser user=new SelectedUser();
				user.setActv_no(activity.getActv_no());
				user.setBatch_no(activity.getBatch_no());
				
				List<SelectedUser> users=daoService.getSelectedUserDao().selectSelectedUser(user);
				Map<String ,Object> map2=new HashMap<>();
				map2.put("suggestion", suggestion);
				map2.put("users",users);
				return map2;
			}
			return null;
		}catch (Exception e) {
			throw new AppException("智能筛选异常",e);
		}
	}
	@Override
	public Map<String, Object> artificialFilter(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("utf-8");
			String actv_no=req.getParameter("actv_no");
			String batch_no=req.getParameter("batch_no");
			String condition=req.getParameter("");
			Map<String, String> map=new HashMap<>();
			map.put("actv_no", actv_no);
			map.put("batch_no",batch_no);
			map.put("condition", condition);
			String rs=HttpUtil.postKeyValue(map,ConfigUtil.get(""),"utf-8");
			JSONObject jobj=JSONObject.parseObject(rs);
			String reCode=jobj.getString("result_code");
			if("0".equals(reCode)){
				String reMsg =jobj.getString("result_msg");
				String suggestion=jobj.getString("suggesstion");
				Activity activity=daoService.getActivityByIdAndBatchNo(actv_no, Integer.parseInt(batch_no));
				activity.setSuggestion(suggestion);
				daoService.getActivityDao().updateActivity(activity);
				SelectedUser user=new SelectedUser();
				user.setActv_no(activity.getActv_no());
				user.setBatch_no(activity.getBatch_no());
				
				List<SelectedUser> users=daoService.getSelectedUserDao().selectSelectedUser(user);
				Map<String ,Object> map2=new HashMap<>();
				map2.put("suggestion", suggestion);
				map2.put("users",users);
				return map2;
			}
			return null;
		} catch (Exception e) {
			throw new AppException("手动筛选失败", e);
		}
	}
	
	
}
