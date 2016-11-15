package com.lanxi.entity;

import com.lanxi.common.RandomUtil;
import com.lanxi.common.TimeUtil;

public class Msg {
	//-------------------------------------常量区start-----------------------------------------------------
	/**短信类型-活动开始*/
	public static final String MSG_TYPE_START			="1";
	/**短信类型-进度提醒*/
	public static final String MSG_TYPE_PROGRESS		="2";
	/**短信类型-活动完成*/
	public static final String MSG_TYPE_SUCCESS			="3";
	/**短信类型-报名成功*/
	public static final String MSG_TYPE_APPLY_SUCCESS	="4";
	/**短信类型-报名失败*/
	public static final String MSG_TYPE_APPLY_FAIL		="5";	
	
	/**短信发送状态-等待发送*/
	public static final String MSG_SEND_STATE_READY			="1"; 
	/**短信发送状态-发送成功*/
	public static final String MSG_SEND_STATE_SEND			="2"; 
	/**短信发送状态-发送失败*/
	public static final String MSG_SEND_STATE_FAIL			="3"; 
	//--------------------------------------常量区end-------------------------------------------------------
	
	private String 		msg_id;
	private String 		actv_no;
	private Integer 	batch_no;
	private String 		phone;
	private String 		msg_type;
	private String 		content;
	private String 		send_state;
	private String 		send_time;
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	public String getActv_no() {
		return actv_no;
	}
	public void setActv_no(String actv_no) {
		this.actv_no = actv_no;
	}
	public Integer getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(Integer batch_no) {
		this.batch_no = batch_no;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSend_state() {
		return send_state;
	}
	public void setSend_state(String send_state) {
		this.send_state = send_state;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	@Override
	public String toString() {
		return "Msg [msg_id=" + msg_id + ", actv_no=" + actv_no + ", batch_no=" + batch_no + ", phone=" + phone
				+ ", msg_type=" + msg_type + ", content=" + content + ", send_state=" + send_state + ", send_time="
				+ send_time + "]";
	}
	
}
