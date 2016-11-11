package com.lanxi.entity;

/**
 * Created by Administrator on 2016/11/7.
 */
//规则属性关联表
public class Prop {
    //属性id
    private String prop_id;
    //活动编号
    private String actv_no;
    //批次号
    private int batch_no;
    //操作类型
    private String operation;
    //数据源表名
    private String source_table;
    //积分对象主键
    private String cust_pk;
    //时间字段
    private String date_key;
    //数据库字段
    private String prop_key;
    //比对符号
    private String compare;
    //比对值
    private String prop_value;

    public String getProp_value() {
        return prop_value;
    }

    public void setProp_value(String prop_value) {
        this.prop_value = prop_value;
    }

    public String getProp_id() {
        return prop_id;
    }

    public void setProp_id(String prop_id) {
        this.prop_id = prop_id;
    }

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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getSource_table() {
        return source_table;
    }

    public void setSource_table(String source_table) {
        this.source_table = source_table;
    }

    public String getCust_pk() {
        return cust_pk;
    }

    public void setCust_pk(String cust_pk) {
        this.cust_pk = cust_pk;
    }

    public String getDate_key() {
        return date_key;
    }

    public void setDate_key(String date_key) {
        this.date_key = date_key;
    }

    public String getProp_key() {
        return prop_key;
    }

    public void setProp_key(String prop_key) {
        this.prop_key = prop_key;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }


}
