package com.lanxi.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lanxi.common.AppException;
import com.lanxi.dao.MsgDao;
import com.lanxi.entity.Activity;
import com.lanxi.entity.Msg;
import com.lanxi.entity.SelectedUser;
import com.lanxi.service.DaoService;
import com.lanxi.service.MsgService;
import com.lanxi.service.QuartzTaskService;
import com.lanxi.service.SmsService;
@Service("quartzTaskService")
public class QuartzTaskServiceImpl implements QuartzTaskService {
	@Resource
	DaoService daoService;
	@Resource
	SmsService smsService;
	@Resource
	MsgService msgService;
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
			if(Activity.ACTIVITY_STATE_READY.equals(oldState)&&Activity.ACTIVITY_STATE_INING.equals(activity.getActv_state())){
				//有效用户 短信提醒
				Map<String, Object>map=new HashMap<>();
				map.put("actv_no", activity.getActv_no());
				map.put("batch_no", activity.getBatch_no());
				map.put("state",SelectedUser.USER_STATE_USEFUL);
				List<SelectedUser> users=daoService.getSelectedUserDao().selectUserByState(map);
				MsgDao msgDao=daoService.getMsgDao();
				for(SelectedUser each:users){
					Msg msg=msgService.makeMsg(activity,each.getPhone(),activity.getMsg_tplt());
					msg.setMsg_type(Msg.MSG_TYPE_START);
					msgDao.addMsg(msg);
				}
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
				MsgDao msgDao=daoService.getMsgDao();
				for(SelectedUser one:users){
					// TODO 短信直接是模版
					Msg msg=msgService.makeMsg(each,one.getPhone(),each.getMsg_tplt());
					msg.setMsg_type(Msg.MSG_TYPE_START);
					msgDao.addMsg(msg);
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
	
	/**
	 * 立即发送短信
	 * 指定活动则只发送改活动的短信
	 * 指定手机号则只发送该手机号短信
	 * @param req
	 * @return
	 */
	public String sendMsg(HttpServletRequest req){
		try{
			req.setCharacterEncoding("utf-8");
			String phone=req.getParameter("phone");
			String actv_no=req.getParameter("actv_no");
			String batch=req.getParameter("batch_no");
			Integer batch_no=batch==null?0:Integer.parseInt(batch);
			Msg temp=new Msg();
			temp.setActv_no(actv_no);
			temp.setBatch_no(batch_no);
			temp.setPhone(phone);
			MsgDao msgDao=daoService.getMsgDao();
			List<Msg> msgs=msgDao.queryNeedSend(temp);
			int flag=0;
			
			for(Msg each:msgs){
				String rs=msgService.sendMsg(each);
				JSONObject jobj=JSONObject.parseObject(rs);
				if("0000".equals(jobj.get("retCode"))){
					each.setSend_state(Msg.MSG_SEND_STATE_SEND);
					flag|=1;
				}
				else{
					each.setSend_state(Msg.MSG_SEND_STATE_FAIL);
					flag|=2;
				}
				msgDao.updateMsg(each);
			}
			return flag+"";
		}catch (Exception e) {
			throw new AppException("手动发送短信失败", e);
		}
	}
	/**
	 * 发送待发与失败短信
	 * 定时任务
	 */
	public void sendMsg(){
		MsgDao msgDao=daoService.getMsgDao();
		List<Msg> msgs=msgDao.queryNeedSend(null);
		for(Msg each:msgs){
			String rs=msgService.sendMsg(each);
			JSONObject jobj=JSONObject.parseObject(rs);
			if("0000".equals(jobj.get("retCode")))
				each.setSend_state(Msg.MSG_SEND_STATE_SEND);
			else
				each.setSend_state(Msg.MSG_SEND_STATE_FAIL);
			msgDao.updateMsg(each);
		}
	}
}
