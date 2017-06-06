package com.scht.admin.entity;

import java.util.List;

/**
 * 商品分类
 * Created by Administrator on 2016/11/27.
 */
public class ProductType {

    private String id;
    private String name; //分类名称
    private String key;//唯一标示
    private int sort; //排序
    private String icon; //图标
    private String status;//NORMAL 正常  Frozen删除

    private String type; //类型  0  外卖商品分类， 1 普通商品分类

    //改分分类下商品列表
    private List<Product> productList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

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
}
