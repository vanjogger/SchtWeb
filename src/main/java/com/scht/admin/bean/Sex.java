package com.scht.admin.bean;

/**
 * Created by Administrator on 2016/5/6.
 */
public enum  Sex {

    MAN("��"),
    WOMAN("Ů");


    Sex(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }
}
