package com.scht.admin.bean;

/**
 * Created by Administrator on 2016/11/28.
 */
public enum  OrderStatus {

    CREATE("订单创建，待支付"),
    PAY("支付完成"),
    DISPATCH("已发货，待收货"),
    SUCCESS("订单完成"),
    CLOSE("订单关闭");

    private String name;
    public String getName(){
        return this.name;
    }
    OrderStatus(String name) {
        this.name = name;
    }
}
