package com.scht.admin.bean;

/**
 * Created by Administrator on 2016/5/9.
 */
public enum RechargeType {

    Center("��������");

    RechargeType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
