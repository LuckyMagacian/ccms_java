package com.lanxi.service;

import com.lanxi.entity.Activity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 1 on 2016/11/7.
 */
public interface ActivityService {
    /**
     * 从客户端接收参数,并根据参数创建待审核活动
     * @param req   servlet请求
     * @param res   servlet响应
     * @return
     */
    public Activity generatorActivity(HttpServletRequest req);
    
    /**
     * 查询可以修改的活动
     * @return
     */
	public  List<Activity> queryCanModify();
    /**
     * 查看活动详情
     * @param req	servlet请求
     * @return
     */
	public Activity queryActivityDetail(HttpServletRequest req);
    /**
     * 修改活动
     * @param req
     * @return
     */
    public Activity modifyActivity(HttpServletRequest req);
    /**
     * 根据条件查询活动
     * @param req
     * @return
     */
    public List<Activity> queryActivity(HttpServletRequest req);
    /**
     * 查询待审核活动
     * @return
     */
    public List<Activity> queryNeedCheck();
    /**
     * 审核活动
     * @param req
     * @return
     */
    public Activity checkActivity(HttpServletRequest req);
    /**
     * 查询可以复制的活动(审核通过未开始,进行中,结束)
     * @return
     */
    public List<Activity> queryCanCopy();
    /**
     * 复制活动
     * @param req
     * @return
     */
    public Activity copyActivity(HttpServletRequest req);
    /**
     * 通过活动编号和活动批次号查询活动
     * @param req
     * @return
     */
    public Activity queryActivityByIdAndBatchNo(HttpServletRequest req);
   
}
