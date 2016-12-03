package com.scht.admin.bean;

/**
 * 支付方式
 * Created by Administrator on 2016/11/28.
 */
public enum  PayType {

    ALIPAY("支付宝"),
    WEIXIN("微信支付");

    private String name;
    public String getName(){
        return name;
    }
    PayType(String name) {
        this.name = name;
    }
}
