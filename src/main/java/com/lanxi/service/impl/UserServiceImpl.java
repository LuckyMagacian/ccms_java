package com.lanxi.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lanxi.common.AppException;
import com.lanxi.common.AppMessage;
import com.lanxi.common.ConfigUtil;
import com.lanxi.common.ExcelUtil;
import com.lanxi.common.HttpUtil;
import com.lanxi.entity.Activity;
import com.lanxi.entity.SelectedUser;
import com.lanxi.service.DaoService;
import com.lanxi.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	DaoService daoService;
	@Override
	public Map<String, Object> autoFilter(HttpServletRequest req) {
		try{
			req.setCharacterEncoding("utf-8");
			String actv_no=req.getParameter("actv_no");
			String batch_no=req.getParameter("batch_no");
			Map<String, String> map=new HashMap<>();
			map.put("actv_no", actv_no);
			map.put("batch_no",batch_no);
			map.put("update_flag","0");
			// TODO 会等很久
			String rs=HttpUtil.postKeyValue(map,ConfigUtil.get(""),"utf-8");
			JSONObject jobj=JSONObject.parseObject(rs);
			String reCode=jobj.getString("result_code");
			if("0".equals(reCode)){
				String reMsg =jobj.getString("result_msg");
//				String suggestion=jobj.getString("suggesstion");
				Activity activity=daoService.getActivityByIdAndBatchNo(actv_no, Integer.parseInt(batch_no));
//				activity.setSuggestion(suggestion);
//				daoService.getActivityDao().updateActivity(activity);
				SelectedUser user=new SelectedUser();
				user.setActv_no(activity.getActv_no());
				user.setBatch_no(activity.getBatch_no());
				
				List<SelectedUser> users=daoService.getSelectedUserDao().selectSelectedUser(user);
				Map<String ,Object> map2=new HashMap<>();
				map2.put("suggestion", activity.getSuggestion());
				map2.put("users",users);
				return map2;
			}
			return null;
		}catch (Exception e) {
			throw new AppException("智能筛选异常",e);
		}
	}
	@Override
	public Map<String, Object> artificialFilter(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("utf-8");
			String actv_no=req.getParameter("actv_no");
			String batch_no=req.getParameter("batch_no");
			String condition=req.getParameter("");
			Map<String, String> map=new HashMap<>();
			map.put("actv_no", actv_no);
			map.put("batch_no",batch_no);
			map.put("condition", condition);
			String rs=HttpUtil.postKeyValue(map,ConfigUtil.get(""),"utf-8");
			JSONObject jobj=JSONObject.parseObject(rs);
			String reCode=jobj.getString("result_code");
			if("0".equals(reCode)){
				String reMsg =jobj.getString("result_msg");
//				String suggestion=jobj.getString("suggesstion");
				Activity activity=daoService.getActivityByIdAndBatchNo(actv_no, Integer.parseInt(batch_no));
//				activity.setSuggestion(suggestion);
//				daoService.getActivityDao().updateActivity(activity);
				SelectedUser user=new SelectedUser();
				user.setActv_no(activity.getActv_no());
				user.setBatch_no(activity.getBatch_no());
				
				List<SelectedUser> users=daoService.getSelectedUserDao().selectSelectedUser(user);
				Map<String ,Object> map2=new HashMap<>();
				map2.put("suggestion", activity.getSuggestion());
				map2.put("users",users);
				return map2;
			}
			return null;
		} catch (Exception e) {
			throw new AppException("手动筛选失败", e);
		}
	}
	@Override
	public HSSFWorkbook exportUser(HttpServletRequest req) {
		try{
			req.setCharacterEncoding("utf-8");
			SelectedUser user=new SelectedUser();
			user.setActv_no(req.getParameter("actv_no"));
			user.setBatch_no(Integer.parseInt(req.getParameter("batch_no")));
			List<SelectedUser> users=daoService.getSelectedUserDao().selectSelectedUser(user);
			return exportExcel(users);
		}catch (Exception e) {
			throw new AppException("导出所有用户异常", e);
		}
	}
	public HSSFWorkbook exportUsefulUser(HttpServletRequest req){
		try{
			SelectedUser user=new SelectedUser();
			user.setActv_no(req.getParameter("actv_no"));
			user.setBatch_no(Integer.parseInt(req.getParameter("batch_no")));
			user.setResult(SelectedUser.USER_STATE_USEFUL);
			List<SelectedUser> users=daoService.getSelectedUserDao().selectSelectedUser(user);
			return exportExcel(users);
		}catch (Exception e) {
			throw new AppException("导出有效用户异常", e);
		}
	}
	public HSSFWorkbook exportSuccessUser(HttpServletRequest req){
		try {
			Activity activity=new Activity();
			activity.setActv_no(req.getParameter("actv_no"));
			activity.setBatch_no(Integer.parseInt(req.getParameter("batch_no")));
			List<SelectedUser> users=daoService.querySuccessUser(activity);
			return exportExcel(users);
		} catch (Exception e) {
			throw new AppException("导出成功用户异常", e);
		}
	}
	/**
	 * 传入用户列表以excel形式导出
	 * @param users
	 * @return
	 */
	private HSSFWorkbook exportExcel(List<SelectedUser> users){
		try{
			String[] titleStr={"活动编号","活动批次","用户卡号","用户姓名","用户手机","报名方式","用户状态","活动结果"};
			//设置标题
			List<HSSFCell> titiles=new ArrayList<>();
			for(int i=0;i<8;i++){
				HSSFCell cell=ExcelUtil.getCell(0, 0, i);
				cell.setCellStyle(ExcelUtil.getTitleStyle());
				cell.setCellValue(titleStr[i]);
				titiles.add(cell);
			}
			//获取字段列表
			List<Field> fields=new ArrayList<>();
			Field[] arr=SelectedUser.class.getDeclaredFields();
			for(Field each:arr)
				if(!Modifier.isStatic(each.getModifiers()))
					fields.add(each);
			//设置单元格
			for(int i=0;i<users.size();i++)
				for(int j=0;j<fields.size();j++){
					HSSFCell cell=ExcelUtil.getCell(0, i+1, j);
					cell.setCellStyle(ExcelUtil.getCommonStyle());
					Field field=fields.get(j);
					field.setAccessible(true);
					cell.setCellValue(field.get(users.get(i))+"");
				}
			return ExcelUtil.getWorkBook();
		}catch (Exception e) {
			throw new AppException("导出excel文件异常",e);
		}
	}
	
}
