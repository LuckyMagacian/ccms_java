package com.lanxi.service.impl;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanxi.common.AppException;
import com.lanxi.common.RandomUtil;
import com.lanxi.common.SmsUtil;
import com.lanxi.common.TimeUtil;
import com.lanxi.entity.Activity;
import com.lanxi.entity.Msg;
import com.lanxi.entity.TempSms;
import com.lanxi.service.MsgService;
import com.lanxi.service.SmsService;
@Service("msgService")
public class MsgServiceImpl implements MsgService{
	@Resource
	private SmsService smsService;
	@Override
	public String makeId() {
		return TimeUtil.getDateTime()+RandomUtil.getRandomNumber(6);
	}
	@Override
	public Msg makeMsg(Activity activity, String phone, String content) {
		Msg msg=new Msg();
		msg.setActv_no(activity.getActv_no());
		msg.setBatch_no(activity.getBatch_no());
		msg.setContent(content);
		msg.setPhone(phone);
		msg.setMsg_id(makeId());
		msg.setSend_state(Msg.MSG_SEND_STATE_READY);
		return msg;
	}

	@Override
	public TempSms toSms(Msg msg) {
		TempSms sms=new TempSms();
		sms.setContent(msg.getContent());
		sms.setMchtId(TempSms.SMS_MCHTID);
		sms.setMobile(msg.getPhone());
		sms.setOrderId(sms.getMchtId()+TimeUtil.getDateTime()+RandomUtil.getRandomNumber(4));
		sms.setTdId(AppException.getTestFlag()?TempSms.SMS_TDID_MOBILE:TempSms.SMS_TDID_WODONG);
		sms.setTradeDate(TimeUtil.getDate());
		sms.setTradeTime(TimeUtil.getTime());
		SmsUtil.signSms(sms);
		return sms;
	}

	@Override
	public String sendMsg(Msg msg) {
		return smsService.sendSms(msg.getPhone(), msg.getContent());
	}



}
