package com.lanxi.dao;

import com.lanxi.entity.Activity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 2016/11/7.
 */
@Repository
public interface ActivityDao {
	/**
	 * 增加活动
	 * @param activity
	 */
    public void addActivity(Activity activity);
    /**
     * 删除活动
     * @param activity
     */
    public void deleteActivity(Activity activity);
    /**
     * 更新活动
     * @param activity
     */
    public void updateActivity(Activity activity);
    /**
     * 通过条件筛选活动
     * @param activity
     * @return
     */
    public List<Activity> 	queryActivity(Activity activity);
    /**
     * 通过 开始时间起止 活动名称 活动状态 筛选活动
     * @param map
     * @return
     */
    public List<Activity>   queryActivityByMap(Map<String,Object> map);
    /**
     * 查询可以修改的活动
     * @return
     */
    public List<Activity>   queryActivityCanModify();
    /**
     * 通过活动状态来筛选活动
     * 活动状态为或的关系 可以叠加
     * @param map
     * @return
     */
    public List<Activity> 	queryActivityByStatus(Map<String, String> map);
}
