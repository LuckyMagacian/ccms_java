package com.lanxi.entity;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/7.
 */
//活动类
public class Activity {
    //-----------------------------------------------------------常量区start---------------------------------------------------------------------------------------

	/**活动目的-唤醒睡眠卡*/
    public static final String ACTIVITY_TARGET_AWAKEN                ="1";
    /**活动目的-提升业务质量*/
    public static final String ACTIVITY_TARGET_IMPROVE               ="2";
//    /**活动目的-维护忠诚度*/
//    public static final String ACTIVITY_TARGET_PRESERVE              ="3";

    /**活动形式-首刷激活*/
    public static final String ACTIVITY_STYLE_FIRST_BRUSH_ACTIVATE   ="1";
    /**活动形式-冻卡激活*/
    public static final String ACTIVITY_STYLE_FROZEN_CARD_ACTIVATE   ="2";
    /**活动形式-分期*/
    public static final String ACTIVITY_STYLE_BY_STAGES              ="3";
    /**活动形式-刷卡*/
    public static final String ACTIVITY_STYLE_BRUSH                  ="4";
//    /**活动形式-回馈客户*/
//    public static final String ACTIVITY_STYLE_FEEDBACK               ="5";

    /**活动类型-赠送积分*/
    public static final String ACTIVITY_TYPE_GIVE_POINTS             ="1";
    /**活动类型-手续费折扣*/
    public static final String ACTIVITY_TYPE_DISCOUNT                ="2";
    /**活动类型-赠送礼品*/
    public static final String ACTIVITY_TYPE_GIVE_GIFT               ="3";
    /**活动类型-分期抽奖*/
    public static final String ACTIVITY_TYPE_DRAW                    ="4";
    /**活动类型-首刷*/
    public static final String ACTIVITY_TYPE_FIRST_BRUSH             ="5";
    /**活动类型-激活*/
    public static final String ACTIVITY_TYPE_ACTIVATE                ="6";
    /**活动类型-满减*/
    public static final String ACTIVITY_TYPE_FULL_REDUCTION          ="7";
    /**活动类型-满送*/
    public static final String ACTIVITY_TYPE_FULL_GIVE               ="8";
    /**活动类型-满额返现*/
    public static final String ACTIVITY_TYPE_FULL_FEEDBACK           ="9";

    /**活动状态-等待审核*/
    public static final String ACTIVITY_STATE_WAIT                   ="1";
    /**活动状态-尚未开始*/
    public static final String ACTIVITY_STATE_READY                  ="2";
    /**活动状态-正在进行*/
    public static final String ACTIVITY_STATE_INING                  ="3";
    /**活动状态-活动结束*/
    public static final String ACTIVITY_STATE_END                    ="4";
    /**活动状态-审核未过*/
    public static final String ACTIVITY_STATE_FAIL                   ="5";

    /**规则组合条件-任一*/
    public static final String RULE_TYPE_ONE                         ="0";
    /**规则组合条件-全部*/
    public static final String RULE_TYPE_ALL                         ="1";

    //-----------------------------------------------------------常量区end---------------------------------------------------------------------------------------




    //活动id
    private String  actv_no;
    //批次号
    private Integer batch_no;
    //活动名称
    private String  actv_name;
    //活动目的
    private String  actv_target;
    //活动形式
    private String  actv_style;
    //活动类型
    private String  actv_type;
    //开始日期
    private Date    start_date;
    //结束日期
    private Date    stop_date;
    //礼品
    private String  gift;
    //短信模板
    private String  msg_tplt;
    //参与类型
    private String  join_type;
    //人数限制
    private Integer people_limit;
    //活动状态
    private String  actv_state;
    //更新时间
    private Date    update_time;
    //创新时间
    private Date    create_time;
    //规则组合条件
    private String  rule_type;
    //成功比例
    private Integer success_rate;
    //描述
    private String  desp;
    //审核意见
    private String 	check_opinion;
    //营销建议
    private String  suggestion;
    public Integer getSuccess_rate() {
        return success_rate;
    }

    public void setSuccess_rate(Integer success_rate) {
        this.success_rate = success_rate;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getRule_type() {
        return rule_type;
    }

    public void setRule_type(String rule_type) {
        this.rule_type = rule_type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
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

    public String getActv_name() {
        return actv_name;
    }

    public void setActv_name(String actv_name) {
        this.actv_name = actv_name;
    }

    public String getActv_target() {
        return actv_target;
    }

    public void setActv_target(String actv_target) {
        this.actv_target = actv_target;
    }

    public String getActv_style() {
        return actv_style;
    }

    public void setActv_style(String actv_style) {
        this.actv_style = actv_style;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getStop_date() {
        return stop_date;
    }

    public void setStop_date(Date stop_date) {
        this.stop_date = stop_date;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getMsg_tplt() {
        return msg_tplt;
    }

    public void setMsg_tplt(String msg_tplt) {
        this.msg_tplt = msg_tplt;
    }

    public String getJoin_type() {
        return join_type;
    }

    public void setJoin_type(String join_type) {
        this.join_type = join_type;
    }

    public Integer getPeople_limit() {
        return people_limit;
    }

    public void setPeople_limit(Integer people_limit) {
        this.people_limit = people_limit;
    }

    public String getActv_state() {
        return actv_state;
    }

    public void setActv_state(String actv_stat) {
        this.actv_state = actv_stat;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

	public String getActv_type() {
		return actv_type;
	}

	public void setActv_type(String actv_type) {
		this.actv_type = actv_type;
	}

	public String getCheck_opinion() {
		return check_opinion;
	}

	public void setCheck_opinion(String check_opinion) {
		this.check_opinion = check_opinion;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	@Override
	public String toString() {
		return "Activity [actv_no=" + actv_no + ", batch_no=" + batch_no + ", actv_name=" + actv_name + ", actv_target="
				+ actv_target + ", actv_style=" + actv_style + ", actv_type=" + actv_type + ", start_date=" + start_date
				+ ", stop_date=" + stop_date + ", gift=" + gift + ", msg_tplt=" + msg_tplt + ", join_type=" + join_type
				+ ", people_limit=" + people_limit + ", actv_state=" + actv_state + ", update_time=" + update_time
				+ ", create_time=" + create_time + ", rule_type=" + rule_type + ", success_rate=" + success_rate
				+ ", desp=" + desp + ", check_opinion=" + check_opinion + ", suggesttion=" + suggestion + "]";
	}
	
	

}
