package com.lanxi.service;
import com.lanxi.dao.ActivityDao;
import com.lanxi.dao.AdminDao;
import com.lanxi.dao.ChartDao;
import com.lanxi.dao.MsgDao;
import com.lanxi.dao.PropDao;
import com.lanxi.dao.SelectedUserDao;
import com.lanxi.entity.Activity;
import com.lanxi.entity.SelectedUser;

import java.lang.String;
import java.util.Date;
import java.util.List;

/**
 * Created by 1 on 2016/11/8.
 */
public interface DaoService {
	/**
	 * 获取活动dao
	 * @return
	 */
    public ActivityDao      getActivityDao();

    /**
     * 通过活动id与批次号来查询活动,批次号默认为0
     * @param actv_no   活动编号
     * @param batch_no  批次编号 若批次为null将被设置为0
     * @return
     */
    public Activity         getActivityByIdAndBatchNo(String actv_no, Integer batch_no);
    /**
     * 通过活动名称 活动状态 活动起始时间范围来查找活动
     * @param actv_name		活动名称
     * @param actv_state 	活动状态
     * @param start1		活动开始时间1
     * @param start2		活动开始时间2
     * @return
     */
    public List<Activity>   queryActivity(String actv_name, String actv_state, Date start1, Date start2);


    /**
     * 活动用户dao
     * @return
     */
    public AdminDao     getAdminDao();




    /**
     * 获取图表dao
     * @return
     */
    public ChartDao     getChartDao();


    /**
     * 获取关联属性dao
     * @return
     */
    public PropDao      getPropDao();
    
    
    /**
     * 获取用户dao
     * @return
     */
    public SelectedUserDao getSelectedUserDao();
    /**
     * 查询成功参加活动的用户列表
     * @param activity
     * @return
     */
    public List<SelectedUser> querySuccessUser(Activity activity);
	/**
	 * 查询参与用户 
	 * @param activity
	 * @return
	 */
    public List<SelectedUser> queryApplyUser(Activity activity);
    
    /**
     * 获取短信dao
     * @return
     */
    public MsgDao  		getMsgDao();
}
