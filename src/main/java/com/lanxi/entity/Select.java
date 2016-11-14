package com.lanxi.entity;

/**
 * Created by Administrator on 2016/11/10.
 */
//用户筛选表
public class Select {
    //活动id
    private String actv_No;
    //批次号
    private int batch_No;
    //客户证件号
    private String custr_Nbr;
    //客户姓名
    private String name;
    //手机号码
    private String phone;
    //主动报名
    private String apply;
    //客户状态
    private String state;
    //活动结果
    private String result;

    public String getActv_No() {
        return actv_No;
    }

    public void setActv_No(String actv_No) {
        this.actv_No = actv_No;
    }

    public int getBatch_No() {
        return batch_No;
    }

    public void setBatch_No(int batch_No) {
        this.batch_No = batch_No;
    }

    public String getCustr_Nbr() {
        return custr_Nbr;
    }

    public void setCustr_Nbr(String custr_Nbr) {
        this.custr_Nbr = custr_Nbr;
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
}