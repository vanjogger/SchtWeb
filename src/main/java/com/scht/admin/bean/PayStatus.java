package com.scht.admin.bean;

/**
 * Created by Administrator on 2016/12/5.
 */
public enum  PayStatus {

    WAIT("待支付"),
    SUCCESS("支付成功"),
    FAIL("支付失败");

    private String name;
    public String getName(){
        return this.name;
    }
    PayStatus(String name) {
        this.name = name;
    }
}
