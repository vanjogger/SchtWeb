package com.scht.admin.bean;

/**
 * Created by Administrator on 2016/11/27.
 */
public enum ProductTypeEnum {

    NORMAL("普通商品"),
    DISCOUNT("五折商品"),
    EXTEND("推广商品");

    private String name;
    public String getName(){
        return this.name;
    }
    ProductTypeEnum(String name) {
        this.name = name;
    }
}
