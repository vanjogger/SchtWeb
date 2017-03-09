package com.scht.admin.bean;

/**
 * 会员资金变动类型
 * Created by Administrator on 2017/3/3.
 */
public enum  MemberFlowType {

    QUESTION("有奖问答奖励"),
    ORDER("订单支付");

    private String name;
    public String getName(){
        return this.name;
    }

    MemberFlowType(String name) {
        this.name = name;
    }
}
