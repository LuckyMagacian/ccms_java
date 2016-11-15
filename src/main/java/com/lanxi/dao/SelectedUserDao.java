package com.lanxi.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lanxi.entity.Activity;
import com.lanxi.entity.SelectedUser;
@Repository
public interface SelectedUserDao {
	/**
	 * 选择用户
	 * @param user
	 * @return
	 */
	public List<SelectedUser> selectSelectedUser(SelectedUser user);
	/**
	 * 通过条件筛选用户
	 * 1活动编号		->	actv_no
	 * 2批次			->	batch_no
	 * 3用户报名方式	->	apply
	 * 4活动结果		->	result
	 * 5用户状态		->	state
	 * @param args
	 * @return
	 */
	public List<SelectedUser> selectUserByState(Map<String, Object> args);
	/**
	 * 结束活动
	 * 1活动编号
	 * 2批次号
	 * @param activity 
	 */
	public void failedUser(Activity activity);
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateUser(SelectedUser user);
	/**
	 * 查询活动完成用户
	 * @param map
	 * @return
	 */
	public List<SelectedUser> querySuccessUser(Map<String, Object> map);
}
