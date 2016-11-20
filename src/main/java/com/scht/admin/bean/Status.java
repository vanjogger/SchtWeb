package com.scht.admin.bean;

/**
 * Created by Administrator on 2016/5/5.
 */
public enum Status {

    AUDIT("待审核"),
    NORMAL("正常"),
    FROZEN("冻结"), ;

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName(){
       return name;
   }
}
