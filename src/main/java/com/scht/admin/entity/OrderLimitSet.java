package com.scht.admin.entity;

/**
 * 定时时限设置
 * Created by Administrator on 2016/11/28.
 */
public class OrderLimitSet {

    private String id;

    private int payLimit; //支付时限  天
    private int successLimit; //确认收货时限 天

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPayLimit() {
        return payLimit;
    }

    public void setPayLimit(int payLimit) {
        this.payLimit = payLimit;
    }

    public int getSuccessLimit() {
        return successLimit;
    }

    public void setSuccessLimit(int successLimit) {
        this.successLimit = successLimit;
    }
}
