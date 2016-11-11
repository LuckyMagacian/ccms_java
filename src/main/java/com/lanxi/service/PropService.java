package com.lanxi.service;

import com.lanxi.entity.Activity;
import com.lanxi.entity.Prop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 2016/11/8.
 */
public interface PropService {
    /**
     * 从客户端接收参数,并根据参数创建关联规则
     * @param req
     * @param res
     * @return
     */
    public List<Prop> generatorProp(HttpServletRequest req, Activity activity);
    /**
     * 修改关联规则属性
     * @param req
     * @return
     */
    public List<Prop> modifyProp(HttpServletRequest req);
    /**
     * 查询活动关联规则
     * @param activity
     * @return
     */
    public List<Prop> queryProp(Activity activity);
}
