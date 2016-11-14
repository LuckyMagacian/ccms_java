package com.lanxi.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lanxi.common.AppException;
import com.lanxi.common.RandomUtil;
import com.lanxi.common.TimeUtil;
import com.lanxi.entity.Activity;
import com.lanxi.entity.Prop;
import com.lanxi.service.DaoService;
import com.lanxi.service.PropService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 1 on 2016/11/8.
 */
@Service("propService")
public class PropServiceImpl implements PropService {
	@Resource
	private DaoService daoService;
    /**
     * 构建关联规则
     * @param req
     * @param activity	若活动不为空,为创建规则
     * 					若活动为空,为修改规则
     * @return
     */
	private List<Prop> makeProp(HttpServletRequest req, Activity activity){
		 try{
	            req.setCharacterEncoding("utf-8");
	            String jsonStr=req.getParameter("prop");
	            JSONArray jArray=JSONArray.parseArray(jsonStr);
	            System.out.println(jsonStr);
	            List<Prop> props=new ArrayList<>();
	            for(int index=0;index<jArray.size();index++){
	            	JSONObject temp=jArray.getJSONObject(index);
	                String  prop_id;
	                String  actv_no;
	                int     batch_no;
	                
	                if(activity==null){
	                	prop_id=temp.getString("prop_id");
	                	actv_no=temp.getString("actv_no");
	                	batch_no=Integer.parseInt(temp.getString("batch_no"));
	                }else{
	                	prop_id= TimeUtil.getDateTime()+ RandomUtil.getRandomNumber(6);
	                	actv_no         =activity.getActv_no();
	                	batch_no        =activity.getBatch_no();
	                }
	                String  operation		=temp.getString("operation");
	                String  source_table	=temp.getString("source_table");
	                String  cust_pk			=temp.getString("cust_pk");		
	                String  date_key		=temp.getString("date_key");
	                String  prop_key		=temp.getString("prop_key");		
	                String  compare			=temp.getString("compare");	
	                String 	prop_value		=temp.getString("prop_value");
	                
	                
	                Prop newProp=new Prop();
	                newProp.setActv_no(actv_no);
	                newProp.setBatch_no(batch_no);
	                newProp.setCompare(compare);
	                newProp.setCust_pk(cust_pk);
	                newProp.setDate_key(date_key);
	                newProp.setOperation(operation);
	                newProp.setProp_id(prop_id);
	                newProp.setProp_key(prop_key);
	                newProp.setProp_value(prop_value);
	                newProp.setSource_table(source_table);
	                props.add(newProp);
	            }
	            return props;
	        }catch (Exception e){
	            throw  new AppException("创建关联规则异常",e);
	        }
	}
	
	/**
     * 从客户端接收参数,并根据参数创建关联规则
     *
     * @param req
     * @param activity
     * @return
     */
    @Override
    public List<Prop> generatorProp(HttpServletRequest req, Activity activity) {
        try{
        	Prop temp=new Prop();
        	
        	List<Prop> props=makeProp(req, activity);
        	for(Prop each:props)
                daoService.getPropDao().addProp(each);
        	return props;
        }catch (Exception e){
            throw  new AppException("创建关联规则异常",e);
        }
    }
    
    
    
    /**
     * 修改关联规则属性
     * @param req
     * @return
     */
	@Override
	public List<Prop> modifyProp(HttpServletRequest req,Activity activity) {
		try {
			Prop temp=new Prop();
			temp.setActv_no(activity.getActv_no());
			temp.setBatch_no(activity.getBatch_no());
			daoService.getPropDao().deleteProp(temp);
			List<Prop> props=generatorProp(req, activity);
			for(Prop each:props)
                daoService.getPropDao().addProp(each);
			return props;
		} catch (Exception e) {
			throw new AppException("修改关联规则异常",e);
		}
	}
    /**
     * 查询活动关联规则
     * @param activity
     * @return
     */
	@Override
	public List<Prop> queryProp(Activity activity) {
		List<Prop> props;
		Prop prop=new Prop();
		prop.setActv_no(activity.getActv_no());
		prop.setBatch_no(activity.getBatch_no());
		props=daoService.getPropDao().selectProp(prop);
		return props;
	}
}
