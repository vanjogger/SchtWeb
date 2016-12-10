package com.scht.admin.entity;

import java.util.List;

/**
 * Created by vanjoger on 2016/11/26.
 */
public class ShopType {
    private String id;
    private String name;
    private String key;
    private int sort;
    private String icon;
    private String parentId;
    private String status;//NORMAL 正常  Frozen删除
    private String isProduct;//是否拉取商品套餐  0:否  1：是
    //下级列表
    private List<ShopType> subs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<ShopType> getSubs() {
        return subs;
    }

    public void setSubs(List<ShopType> subs) {
        this.subs = subs;
    }

    public String getIsProduct() {
        return isProduct;
    }

    public void setIsProduct(String isProduct) {
        this.isProduct = isProduct;
    }
}
