package com.lanxi.dao;

import java.util.List;

import com.lanxi.entity.Msg;

public interface MsgDao {
	public void 		addMsg(Msg msg);
	public void 		deleteMsg(Msg msg);
	public void 		updateMsg(Msg msg);
	public List<Msg>	selectMsg(Msg msg);
	public List<Msg>    queryNeedSend(Msg msg);
}
