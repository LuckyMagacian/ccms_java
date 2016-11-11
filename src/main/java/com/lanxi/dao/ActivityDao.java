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
    public void addActivity(Activity activity);
    public void deleteActivity(Activity activity);
    public void updateActivity(Activity activity);
    public List<Activity> 	queryActivity(Activity activity);
    public List<Activity>   queryActivityByMap(Map<String,Object> map);
    public List<Activity>   queryActivityCanModify();
    public List<Activity> 	queryActivityByStatus(Map<String, String> map);
}
