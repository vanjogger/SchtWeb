package com.scht.admin.entity;

/**
 * 推送设置
 * 极光推送
 * Created by Administrator on 2016/11/24.
 */
public class PushSet {

    private String id;
    private String AppKey;//app key
    private String masterSecret;  // Master secret


    private String status; //状态， 开启 关闭

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppKey() {
        return AppKey;
    }

    public void setAppKey(String appKey) {
        AppKey = appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
