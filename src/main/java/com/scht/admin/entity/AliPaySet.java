package com.scht.admin.entity;

/**
 * 支付宝支付设置
 * Created by Administrator on 2016/11/24.
 */
public class AliPaySet {

    private String id;
    private String account; //支付宝账号
    private String appKey;// 支付秘钥
    private String mchNo; //商户号
    private String rsaKey; //APP支付私钥
    private String status; //状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
    }

    public String getRsaKey() {
        return rsaKey;
    }

    public void setRsaKey(String rsaKey) {
        this.rsaKey = rsaKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
