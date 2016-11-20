package com.scht.admin.bean;

/**
 * Created by Administrator on 2016/6/24.
 */
public enum LoginType {

    Admin("����Ա"),
    Member("��Ա");


    LoginType(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }
}
