package com.scht.admin.bean;

/**
 * Created by Administrator on 2016/7/8.
 */
public enum MemberType {

    Member("普通会员"),
    Stocker("股东会员"),
    Director("公司董事");


    private String name;

    MemberType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
