package com.scht.admin.entity;

/**
 * 推送设置
 * 极光推送
 * Created by Administrator on 2016/11/24.
 */
public class PushSet {

    private String id;
    private String androidAppKey;//app key
    private String androidMasterSecret;  // Master secret

    private String iosAppKey;
    private String iosMasterSecret;

    private String status; //状态， 开启 关闭

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAndroidAppKey() {
        return androidAppKey;
    }

    public void setAndroidAppKey(String androidAppKey) {
        this.androidAppKey = androidAppKey;
    }

    public String getAndroidMasterSecret() {
        return androidMasterSecret;
    }

    public void setAndroidMasterSecret(String androidMasterSecret) {
        this.androidMasterSecret = androidMasterSecret;
    }

    public String getIosAppKey() {
        return iosAppKey;
    }

    public void setIosAppKey(String iosAppKey) {
        this.iosAppKey = iosAppKey;
    }

    public String getIosMasterSecret() {
        return iosMasterSecret;
    }

    public void setIosMasterSecret(String iosMasterSecret) {
        this.iosMasterSecret = iosMasterSecret;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
