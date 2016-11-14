package com.lanxi.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanxi.common.AppMessage;
import com.lanxi.entity.Activity;
import com.lanxi.entity.Prop;
import com.lanxi.service.ActivityService;
import com.lanxi.service.PropService;
import com.lanxi.service.QuartzTaskService;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	private static Logger logger=Logger.getLogger(ActivityController.class);
	@Resource
	ActivityService service;
    @Resource
	PropService	propService;
    @Resource
    QuartzTaskService taskService;
	@SuppressWarnings("finally")
	@RequestMapping("/generatActivity.do")
	@ResponseBody
	public AppMessage generatorActivity(HttpServletRequest req){
		logger.info("请求创建活动");
		AppMessage message=new AppMessage();
		try{
			Activity activity=service.generatorActivity(req);
			List<Prop>     props=propService.generatorProp(req, activity);
			if(null!=activity&&null!=activity.getActv_no()){
				message.setErrCode("0000");
				message.setErrMsg("创建活动成功");
				message.setContent(activity);
				logger.info("活动创建成功");
			}else{
				message.setErrCode("9998");
				message.setErrMsg("创建活动失败");
				logger.info("活动创建失败");
			}
		}catch (Exception e) {
			message.setErrCode("9999");
			message.setErrMsg("系统错误,创建活动失败");
			logger.error("发生错误,创建活动失败");
		}finally {
			return message;
		}
	}			

	
	@SuppressWarnings("finally")
	@RequestMapping("/queryCanModify.do")
	@ResponseBody
	public AppMessage queryCanModify(){
		logger.info("查询可以修改的活动");
		AppMessage message=new AppMessage();
		try{
			List<Activity> list=service.queryCanModify();
			if(null!=list){
				message.setErrCode("0000");
				message.setErrMsg("查询成功");
				message.setContent(list);
				logger.info("查询可以修改的活动成功");
			}else{
				message.setErrCode("9998");
				message.setErrMsg("查询失败");
				logger.info("查询可以修改的活动失败");
			}
		}catch (Exception e) {
			message.setErrCode("9999");
			message.setErrMsg("系统错误,查询失败");
			logger.error("发生错误,查询可以修改的活动失败");
		}finally{
			return message;
		}
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/queryDetail.do")
	@ResponseBody
	public AppMessage queryActivityDetail(HttpServletRequest req){
		logger.info("查询活动详情");
		AppMessage message=new AppMessage();
		try{
			Activity activity=service.queryActivityDetail(req);
			List<Prop> list=propService.queryProp(activity);
			if(activity!=null&&list!=null){
				message.setErrCode("0000");
				message.setErrMsg("查询成功");
				message.setContent(new Object[]{activity,list});
				logger.info("查询活动详情成功");
			}else{
				message.setErrCode("9998");
				message.setErrMsg("查询失败");
				logger.info("查询活动详情失败");
			}
		}catch (Exception e) {
			message.setErrCode("9999");
			message.setErrMsg("系统错误,查询失败");
			logger.error("发生错误,查询活动详情失败");
		}finally{
			return message;
		}
	}
	@SuppressWarnings("finally")
	@RequestMapping("/modifyActivity.do")
	@ResponseBody
	public AppMessage modifyActivity(HttpServletRequest req){
		logger.info("修改活动");
		AppMessage message=new AppMessage();
		try{
			Activity activity=service.modifyActivity(req);
			propService.modifyProp(req, activity);
			if(activity!=null){
				message.setErrCode("0000");
				message.setErrMsg("修改成功");
				logger.info("修改活动成功");
			}else{
				message.setErrCode("9998");
				message.setErrMsg("修改失败");
				logger.info("修改活动失败");
			}
		}catch (Exception e) {
			message.setErrCode("9999");
			message.setErrMsg("系统错误,修改失败");
		}finally{
			return message;
		}
	}
	
	@RequestMapping("/queryActivityByIdAndBatch.do")
	@ResponseBody
	public AppMessage queryActivityByIdAndBatch(HttpServletRequest req){
		AppMessage message=new AppMessage();
		Activity activity=service.queryActivityByIdAndBatchNo(req);
		if(activity!=null)
		message.setErrCode("0000");
		else
		message.setErrCode("9999");
		message.setErrMsg("12334");
		message.setContent(activity);
		return message;
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/queryActivity.do")
	@ResponseBody
	public AppMessage queryActivity(HttpServletRequest req){
		logger.info("根据条件查询活动");
		AppMessage message=new AppMessage();
		try{
			List<Activity> list=service.queryActivity(req);
			if(null!=list){
				message.setErrCode("0000");
				message.setErrMsg("查询成功");
				message.setContent(list);
				logger.info("查询活动成功");
			}else{
				message.setErrCode("9998");
				message.setErrMsg("查询失败");
				logger.info("查询活动失败");
			}
		}catch (Exception e) {
			message.setErrCode("9999");
			message.setErrMsg("系统错误,查询失败");
			logger.error("发生错误,查询活动失败");
		}finally{
			return message;
		}
	}
	@SuppressWarnings("finally")
	@RequestMapping("/queryNeedCheck.do")
	@ResponseBody
	public AppMessage queryNeedCheck(){
		logger.info("查询待审核活动");
		AppMessage message=new AppMessage();
		try{
			List<Activity> list=service.queryNeedCheck();
			if(null!=list){
				message.setErrCode("0000");
				message.setErrMsg("查询成功");
				message.setContent(list);
				logger.info("查询待审核活动成功");
			}else{
				message.setErrCode("9998");
				message.setErrMsg("查询失败");
				logger.info("查询待审核活动失败");
			}
		}catch (Exception e) {
			message.setErrCode("9999");
			message.setErrMsg("系统错误,查询失败");
			logger.error("发生错误,查询待审核活动失败");
		}finally{
			return message;
		}
	}
	@SuppressWarnings("finally")
	@RequestMapping("/checkActivity.do")
	@ResponseBody
	public AppMessage checkActivity(HttpServletRequest req){
		logger.info("审核活动");
		AppMessage message=new AppMessage();
		try{
			Activity activity=service.checkActivity(req);
			if(activity!=null){
				message.setErrCode("0000");
				message.setErrMsg("修改成功");
				logger.info("审核完成");
				
			}else{
				message.setErrCode("9998");
				message.setErrMsg("修改失败");
				logger.info("审核异常");
			}
		}catch (Exception e) {
			message.setErrCode("9999");
			message.setErrMsg("系统错误,修改失败");
			logger.error("发生错误,审核活动异常");
		}finally{
			return message;
		}
	}
	@SuppressWarnings("finally")
	@RequestMapping("/queryCanCopy.do")
	@ResponseBody
	public AppMessage queryCanCopy(){
		logger.info("查询可以复制的活动");
		AppMessage message=new AppMessage();
		try{
			List<Activity> list=service.queryNeedCheck();
			if(null!=list){
				message.setErrCode("0000");
				message.setErrMsg("查询成功");
				message.setContent(list);
				logger.info("查询可以复制的活动成功");
			}else{
				message.setErrCode("9998");
				message.setErrMsg("查询失败");
				logger.info("查询可以复制的活动失败");
			}
		}catch (Exception e) {
			message.setErrCode("9999");
			message.setErrMsg("系统错误,查询失败");
			logger.error("发生了错误查询可以复制的活动失败");
		}finally{
			return message;
		}
	}
	@SuppressWarnings("finally")
	@RequestMapping("/copyActivity.do")
	@ResponseBody
	public AppMessage copyActivity(HttpServletRequest req){
		logger.info("复制活动");
		AppMessage message=new AppMessage();
		try{
			Activity activity=service.copyActivity(req);
			if(activity!=null){
				message.setErrCode("0000");
				message.setErrMsg("修改成功");
				logger.info("复制活动成功");
			}else{
				message.setErrCode("9998");
				message.setErrMsg("修改失败");
				logger.info("复制活动失败");
			}
		}catch (Exception e) {
			message.setErrCode("9999");
			message.setErrMsg("系统错误,修改失败");
			logger.error("发生了错误,复制活动失败");
		}finally{
			return message;
		}
	}
	
	
	@SuppressWarnings("finally")
	@RequestMapping("/controllerActivity.do")
	@ResponseBody
	public AppMessage controllerActivity(HttpServletRequest req){
		AppMessage message=new AppMessage();
		try {
			Object obj=taskService.controlActivity(req);
			if(obj!=null){
				message.setErrCode("0000");
				message.setErrMsg("操作成功");
				message.setContent(obj);
			}else{
				message.setErrCode("9998");
				message.setErrMsg("操作失败");
			}
		} catch (Exception e) {
			message.setErrCode("9999");
			message.setErrMsg("系统错误,操作失败");
		}finally {
			return message;
		}
	}
	
}
