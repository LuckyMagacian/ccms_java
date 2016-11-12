package com.lanxi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanxi.entity.Activity;
import com.lanxi.service.DaoService;
import com.lanxi.service.QuartzTaskService;
@Service("quartzTaskService")
public class QuartzTaskServiceImpl implements QuartzTaskService {
	@Resource
	DaoService daoService;
	public void startActivity(){
		Activity activity=new Activity();
		activity.setActv_state(Activity.ACTIVITY_STATE_READY);

		List<Activity> activities=daoService.queryActivity(null,Activity.ACTIVITY_STATE_READY,null,null);
	}
	public void endActivity(){
		
	}
	
}
