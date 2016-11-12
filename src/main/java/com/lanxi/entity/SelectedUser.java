package com.lanxi.entity;

public class SelectedUser {
	//---------------------------------常量区start-----------------------------------------
	/**用户选择-自动选择*/
	public static final String USER_APPLY_AUTO	="0";
	/**用户选择-主动报名*/
	public static final String USER_APPLY_ACTIVE="1";
	/**用户选择-未报名*/
	public static final String USER_APPLY_NO	="";
	
	
	/**用户状态-有效*/
	public static final String USER_STATE_USEFUL="0";	
	/**用户状态-无效*/
	public static final String USER_STATE_NOUSE	="1";
	
	
	
	/**活动结果-成功*/
	public static final String USER_RESULT_SUCCESS	="0";
	/**活动结果-失败*/
	public static final String USER_RESULT_FAIL		="1";
	/**活动状态-即将完成*/
	public static final String USER_RESULT_NEAR		="2";
	//---------------------------------常量区end-------------------------------------------
	/**活动编号*/
	private String 	actv_no;
	/**批次编号*/
	private int		batch_no;
	/**用户卡号*/
	private String	custr_nbr;
	/**用户姓名*/
	private String	name;
	/**用户手机*/
	private String 	phone;
	/**报名方式*/
	private String 	apply;
	/**用户状态*/
	private	String	state;
	/**活动结果*/
	private String	result;
	
	public String getActv_no() {
		return actv_no;
	}
	public void setActv_no(String actv_no) {
		this.actv_no = actv_no;
	}
	public int getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(int batch_no) {
		this.batch_no = batch_no;
	}
	public String getCustr_nbr() {
		return custr_nbr;
	}
	public void setCustr_nbr(String custr_nbr) {
		this.custr_nbr = custr_nbr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getApply() {
		return apply;
	}
	public void setApply(String apply) {
		this.apply = apply;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "SelectedUser [actv_no=" + actv_no + ", batch_no=" + batch_no + ", custr_nbr=" + custr_nbr + ", name="
				+ name + ", phone=" + phone + ", apply=" + apply + ", state=" + state + ", result=" + result + "]";
	}
	
}
