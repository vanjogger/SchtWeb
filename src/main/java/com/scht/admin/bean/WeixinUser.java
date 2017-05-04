package com.scht.admin.bean;

/**
 * Created by wxh on 2017/5/3.
 */
public class WeixinUser {

    private String id;
    private String openId; //公众平台openid
    private String unionId; //

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
