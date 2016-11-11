package com.lanxi.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

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
	 * 1活动名		->	actv_no
	 * 2批次			->	batch_no
	 * 3用户报名方式	->	apply
	 * 4活动结果		->	result
	 * 5用户状态		->	state
	 * @param args
	 * @return
	 */
	public List<SelectedUser> selectUserByState(Map<String, Object> args);
}
