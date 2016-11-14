package com.lanxi.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.lanxi.common.AppException;
import com.lanxi.common.ConfigUtil;
import com.lanxi.common.HttpUtil;
import com.lanxi.entity.Activity;
import com.lanxi.entity.SelectedUser;
import com.lanxi.entity.TempSms;
import com.lanxi.service.DaoService;
import com.lanxi.service.QuartzTaskService;
import com.lanxi.service.SmsService;
@Service("quartzTaskService")
public class QuartzTaskServiceImpl implements QuartzTaskService {
	@Resource
	DaoService daoService;
	@Resource
	SmsService smsService;
	/**
	 * 活动控制
	 * @param req
	 */
	public Object controlActivity(HttpServletRequest req){
		try {
			String actv_no=req.getParameter("actv_no");
			String batch=req.getParameter("batch_no");
			String actv_state=req.getParameter("actv_state");
			Integer batch_no=Integer.parseInt(batch);
			Activity activity=daoService.getActivityByIdAndBatchNo(actv_no, batch_no);
			String oldState=activity.getActv_state();
			activity.setActv_state(actv_state);
			if(!"3".equals(oldState)&&"3".equals(activity.getActv_state())){
				//有效用户 短信提醒
				Map<String, Object>map=new HashMap<>();
				map.put("actv_no", activity.getActv_no());
				map.put("batch_no", activity.getBatch_no());
				map.put("state",SelectedUser.USER_STATE_USEFUL);
				List<SelectedUser> users=daoService.getSelectedUserDao().selectUserByState(map);
				for(SelectedUser each:users)
					smsService.sendSms(each.getPhone(), activity.getMsg_tplt());
			}
			daoService.getActivityDao().updateActivity(activity);
			return activity;
		} catch (Exception e) {
			new AppException("操作活动异常",e);
		}
		return null;
	}
	
	
	/**
	 * 活动开始
	 * 定时任务
	 */
	public void startActivity(){
		List<Activity> activities=daoService.queryActivity(null,Activity.ACTIVITY_STATE_READY,null,null);
		for(Activity each:activities){
			Date startDay=each.getStart_date();
			Date today=new Date();
			if(today.getYear()==startDay.getYear()&&today.getMonth()==startDay.getMonth()&&today.getDate()==startDay.getDate()){
				//活动状态更新
				each.setActv_state(Activity.ACTIVITY_STATE_INING);
				daoService.getActivityDao().updateActivity(each);
				//有效用户 短信提醒
				Map<String, Object>map=new HashMap<>();
				map.put("actv_no", each.getActv_no());
				map.put("batch_no", each.getBatch_no());
				map.put("state",SelectedUser.USER_STATE_USEFUL);
				List<SelectedUser> users=daoService.getSelectedUserDao().selectUserByState(map);
				for(SelectedUser one:users){
					String content=each.getMsg_tplt();
					String phone=one.getPhone();
					smsService.sendSms(phone, content);
				}
			}
		}
	}
	/**
	 * 活动结束
	 * 定时任务
	 */
	public void endActivity(){
		List<Activity> activities=daoService.queryActivity(null,Activity.ACTIVITY_STATE_INING,null,null);
		for(Activity each:activities){
			Date endDay=each.getStop_date();
			Date today=new Date();
			if(today.getYear()==endDay.getYear()&&today.getMonth()==endDay.getMonth()&&today.getDate()==endDay.getDate()){
				//活动状态更新
				each.setActv_state(Activity.ACTIVITY_STATE_END);
				daoService.getActivityDao().updateActivity(each);
				//未成功用户标记为失败
				daoService.getSelectedUserDao().failedUser(each);
			}
		}
	}
	
}
