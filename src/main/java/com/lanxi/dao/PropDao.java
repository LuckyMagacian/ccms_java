package com.lanxi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lanxi.entity.Prop;

/**
 * Created by 1 on 2016/11/8.
 */
@Repository
public interface PropDao {
	public void 		addProp(Prop prop);
	public void 		deleteProp(Prop prop);
	public void 		updateProp(Prop prop);
	public List<Prop> 	selectProp(Prop prop);
}
