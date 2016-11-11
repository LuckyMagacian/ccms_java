package com.lanxi.service.impl;

import com.lanxi.dao.ActivityDao;
import com.lanxi.dao.AdminDao;
import com.lanxi.dao.ChartDao;
import com.lanxi.dao.PropDao;
import com.lanxi.dao.SelectedUserDao;
import com.lanxi.entity.Activity;
import com.lanxi.entity.SelectedUser;
import com.lanxi.service.DaoService;
import java.lang.*;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by 1 on 2016/11/8.
 */
@Component("daoService")
public class DaoServiceImpl implements DaoService{
    @Resource
    private ActivityDao activityDao;
    @Resource
    private AdminDao    adminDao;
    @Resource
    private ChartDao    chartDao;
    @Resource
    private PropDao     propDao;
    @Resource
    private SelectedUserDao userDao;
    public ActivityDao  getActivityDao(){
        return activityDao;
    }
    
    @Override
    public Activity getActivityByIdAndBatchNo(String actv_no,Integer batch_no){
        Activity activity=new Activity();
        activity.setActv_no(actv_no);
        activity.setBatch_no(batch_no==null?0:batch_no);
        return activityDao.queryActivity(activity).get(0);
    }


    @Override
    public List<Activity> queryActivity(String actv_name, String actv_state, Date start1, Date start2) {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("actv_name",actv_name);
        map.put("actv_stat",actv_state);
        map.put("start1",start1);
        map.put("start2",start2);
        return activityDao.queryActivityByMap(map);
    }

    
 
    
    
    
    
    
    public AdminDao     getAdminDao(){
        return  adminDao;
    }




    public ChartDao     getChartDao(){
        return chartDao;
    }






    public PropDao      getPropDao(){
        return propDao;
    }

    
    
	@Override
	public SelectedUserDao getSelectedUserDao() {
		return userDao;
	}
	
	public List<SelectedUser> querySuccessUser(Activity activity){
		Map<String,	Object> map=new HashMap<>();
		map.put("actv_no", activity.getActv_no());
		map.put("batch_no", activity.getBatch_no());
		map.put("success", SelectedUser.USER_RESULT_SUCCESS);
		return userDao.selectUserByState(map);
	}

	public List<SelectedUser> queryApplyUser(Activity activity){
		Map<String,	Object> map=new HashMap<>();
		map.put("actv_no", activity.getActv_no());
		map.put("batch_no", activity.getBatch_no());
		map.put("success", SelectedUser.USER_STATE_USEFUL);
		return userDao.selectUserByState(map);
	}

}