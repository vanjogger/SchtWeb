package com.scht.admin.entity;

/**
 * Created by wxh on 2016/12/2.
 */
public class ShopBank {
    private String id;
    private String shopId;
    private String shopAccount;
    private String shopName;
    private String yhmc;//银行名称
    private String khh;//开户行
    private String ckr;//持卡人名称
    private String kh;//卡号

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

    public String getShopAccount() {
        return shopAccount;
    }

    public void setShopAccount(String shopAccount) {
        this.shopAccount = shopAccount;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public String getKhh() {
        return khh;
    }

    public void setKhh(String khh) {
        this.khh = khh;
    }

    public String getCkr() {
        return ckr;
    }

    public void setCkr(String ckr) {
        this.ckr = ckr;
    }

    public String getKh() {
        return kh;
    }

    public void setKh(String kh) {
        this.kh = kh;
    }
}
