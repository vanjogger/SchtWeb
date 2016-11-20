package com.scht.admin.bean;

/**
 * Created by Administrator on 2016/4/1.
 */
public enum UserStatus {
    AUDIT("待审核"),
    NORMAL("正常"),
    FROZEN("已冻结");

    UserStatus(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
