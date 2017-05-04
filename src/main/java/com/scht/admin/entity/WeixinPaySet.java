package com.scht.admin.entity;

/**
 * 微信支付设置
 * Created by Administrator on 2016/11/24.
 */
public class WeixinPaySet {

    private String id;
    private String appId; //应用id
    private String mchNo; //商户号
    private String gzAppId; //公众平台appid
    private String secret; //微信secret
    private String payKey; //支付密钥
    private String status; //状态
    private String cerPath; //证书

    public String getGzAppId() {
        return gzAppId;
    }

    public void setGzAppId(String gzAppId) {
        this.gzAppId = gzAppId;
    }

    public String getCerPath() {
        return cerPath;
    }

    public void setCerPath(String cerPath) {
        this.cerPath = cerPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }
}
