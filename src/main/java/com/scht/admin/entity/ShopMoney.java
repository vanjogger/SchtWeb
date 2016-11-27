package com.scht.admin.entity;

/**
 * Created by vanjoger on 2016/11/27.
 */
public class ShopMoney {

    private String id;
    private String shopId;
    private String shopName;
    private String availAmount;
    private String frozenAmount;
    private String totalAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAvailAmount() {
        return availAmount;
    }

    public void setAvailAmount(String availAmount) {
        this.availAmount = availAmount;
    }

    public String getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(String frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
