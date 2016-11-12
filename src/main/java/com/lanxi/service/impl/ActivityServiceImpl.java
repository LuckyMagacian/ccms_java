package com.lanxi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lanxi.common.AppException;
import com.lanxi.common.AppMessage;
import com.lanxi.common.ConfigUtil;
import com.lanxi.common.HttpUtil;
import com.lanxi.common.RandomUtil;
import com.lanxi.common.TimeUtil;
import com.lanxi.entity.Activity;
import com.lanxi.entity.SelectedUser;
import com.lanxi.service.ActivityService;
import com.lanxi.service.DaoService;


/**
 * Created by 1 on 2016/11/7.
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService{
    @Resource
    private DaoService daoService;
	private SimpleDateFormat tempFormat=new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 构建活动
     * @param req		servlet请求
     * @param actv_no	活动编号
     * @param batch_no	活动批次
     * @return
     */
    private Activity makeActivity(HttpServletRequest req,String actv_no,Integer batch_no){
    	try{
    		 actv_no= null==actv_no?req.getParameter("actv_no"):actv_no;
	    	 batch_no= null==batch_no?Integer.parseInt(req.getParameter("batch_no")):batch_no;

	    	 String actv_name    =req.getParameter("actv_name");
	         String actv_target  =req.getParameter("actv_target");
	         String actv_style   =req.getParameter("actv_style");
	         String actv_type    =req.getParameter("actv_type");
	         Date   start_date   =tempFormat.parse(req.getParameter("start_date"));
	         Date   stop_date    =tempFormat.parse(req.getParameter("stop_date"));
	         String rule_type    =req.getParameter("rule_type");
	
	         String gift         =req.getParameter("gift");
	         String msg_tplt     =req.getParameter("msg_tplt");
	         String join_type    =req.getParameter("join_type");
	         int people_limit    =Integer.parseInt(req.getParameter("people_limit"));
	         String actv_stat    =Activity.ACTIVITY_STATE_WAIT;
	         int success_rate    =Integer.parseInt(req.getParameter("success_rate"));
	         String desp         =req.getParameter("desp");
	         
	         Activity activity   =new Activity();
             activity.setActv_no(actv_no);
             activity.setBatch_no(batch_no);
             activity.setActv_name(actv_name);
             activity.setActv_target(actv_target);
             activity.setActv_style(actv_style);
             activity.setActv_type(actv_type);
             activity.setStart_date(start_date);
             activity.setStop_date(stop_date);
             activity.setRule_type(rule_type);

             activity.setGift(gift);
             activity.setMsg_tplt(msg_tplt);
             activity.setJoin_type(join_type);
             activity.setPeople_limit(people_limit);
             activity.setActv_state(actv_stat);
             activity.setSuccess_rate(success_rate);
             activity.setDesp(desp);
             activity.setCreate_time(new Date());
             return activity;
    	}
    	catch (Exception e) {
    		throw new AppException("构建活动失败",e);
    	}
    }
    
    /**
     * 从客户端接收参数,并根据参数创建待审核活动
     * @param req   servlet请求
     * @param res   servlet响应
     * @return
     */
    @Override
    public Activity generatorActivity(HttpServletRequest req) {
        try {
        	String actv_no=TimeUtil.getDateTime()+RandomUtil.getRandomNumber(6);
        	int    batch_no=0;
            Activity activity=makeActivity(req, actv_no, batch_no);
            daoService.getActivityDao().addActivity(activity);
            return activity;
        }catch (Exception e){
            throw  new AppException("创建活动异常",e);
        }
    }

    /**
     * 查询可以修改的活动
     * @return
     */
	public  List<Activity> queryCanModify() {
		try{
			List<Activity> list=daoService.getActivityDao().queryActivityCanModify();
			return list;
		}catch (Exception e) {
			throw new AppException("查询可修改活动异常");
		}
	}
    /**
     * 查看活动详情
     * @param req	servlet请求
     * @return
     */
	public Activity queryActivityDetail(HttpServletRequest req){
		try{
			req.setCharacterEncoding("utf-8");
			String actv_no	=	req.getParameter("actv_no");
			int batch_no	=	Integer.parseInt(req.getParameter("batch_no"));
			return daoService.getActivityByIdAndBatchNo(actv_no, batch_no);
		}catch (Exception e) {
			throw new AppException("查询活动详情异常",e);
		}
	}
	
    /**
     * 修改活动
     * @param req	servlet请求
     * @return
     */
    public Activity modifyActivity(HttpServletRequest req) {
    	try{
    		Activity activity=makeActivity(req, null, null);
    		activity.setUpdate_time(new Date());
    		daoService.getActivityDao().updateActivity(activity);
    		Map<String, String> map=new HashMap<>();
			map.put("actv_no", activity.getActv_no());
			map.put("batch_no",activity.getBatch_no()+"");
			map.put("update_flag","1");
			HttpUtil.postKeyValue(map, ConfigUtil.get(""),"utf-8");
    		return activity;
    	}catch (Exception e) {
    		throw new AppException("修改活动异常",e);
    	}
    }
	
    /**
     * 根据条件查询活动
     * @param req
     * @return
     */
    @Override
    public List<Activity> queryActivity(HttpServletRequest req) {
    	try{
    		req.setCharacterEncoding("utf-8");
    		String actv_name    =req.getParameter("actv_name");
	        String actv_state   =req.getParameter("actv_state");
	        String date1		=req.getParameter("start1");
	        String date2		=req.getParameter("start2");
	        Date   start1		=null;
	        Date   start2		=null;
	        if(null!=date1&&!date1.trim().isEmpty())
	        	start1		=tempFormat.parse(date1);
	        if(null!=date2&&!date2.trim().isEmpty())
	        	start2		=tempFormat.parse(date2);
	        Map<String, Object> map=new LinkedHashMap<String,Object>();
	        map.put("actv_name", actv_name);
	        map.put("actv_state", actv_state);
	        map.put("start1", start1);
	        map.put("start2", start2);
	        System.out.println(map);
	        return daoService.getActivityDao().queryActivityByMap(map);
    	}catch (Exception e) {
			throw new AppException("查询活动异常",e);
		}
    }
    /**
     * 查询待审核活动
     * @return
     */
    public List<Activity> queryNeedCheck(){
    	try{
    		Map<String, String> map=new LinkedHashMap<>();
    		map.put("status0",Activity.ACTIVITY_STATE_WAIT);
    		return daoService.getActivityDao().queryActivityByStatus(map);
    	}catch (Exception e) {
    		throw new AppException("查询待审核活动异常",e);
    	}
    }
    /**
     * 审核活动
     * @param req
     * @return
     */
    public Activity checkActivity(HttpServletRequest req) {
    	try{
    		String actv_no= req.getParameter("actv_no");
    		int	   batch_no=Integer.parseInt(req.getParameter("batch_no"));
    		String status = req.getParameter("actv_state");
    		String check_opinion=req.getParameter("check_opinion");
    		Activity activity=daoService.getActivityByIdAndBatchNo(actv_no, batch_no);
    		activity.setCheck_opinion(check_opinion);
    		activity.setActv_state(status);
    		daoService.getActivityDao().updateActivity(activity);
    		return activity;
    	}catch (Exception e) {
    		throw new AppException("审核活动异常",e);
    	}
    }
    /**
     * 查询可以复制的活动(审核通过未开始,进行中,结束)
     * @return
     */
    public List<Activity> queryCanCopy(){
    	try{
	    	Map<String, String> map=new LinkedHashMap<>();
	    	map.put("status0", Activity.ACTIVITY_STATE_READY);
	    	map.put("status1", Activity.ACTIVITY_STATE_INING);
	    	map.put("status2", Activity.ACTIVITY_STATE_END);
	    	return daoService.getActivityDao().queryActivityByStatus(map);
    	}catch (Exception e) {
    		throw new AppException("查询可复制活动异常",e);
    	}
	 }
    /**
     * 复制活动
     * @param req
     * @return
     */
    @Override
    public Activity copyActivity(HttpServletRequest req) {
    	try{
    		Activity activity=makeActivity(req, null, null);
    		activity.setBatch_no(activity.getBatch_no()+1);
    		daoService.getActivityDao().addActivity(activity);
        	return activity;
    	}catch (Exception e) {
    		throw new AppException("复制活动异常",e);
    	}
    }


}
