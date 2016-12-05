/**
 * @(#) PayRequest.java    2014/11/05 10:56
 *
 * 鐗堟潈鎵?鏈? (c) 鍖椾含閾惰蒋缃戠粶鎶?鏈湁闄愬叕鍙?
 * 鍖椾含甯傛捣娣?鍖轰笂鍦板浗闄呭垱涓氬洯瑗垮尯1鍙?
 * 淇濈暀鎵?鏈夋潈鍒?
 */
package com.scht.util.PayUtil;

/**
 * 类的功能，目的，描述等写在此处
 *    微信浏览器页面中支付时所需要的数据
 * @author 张战军
 * @version 1.0
 */
public class PayRequest {
    private String appId;
    private String timeStamp;
    private String nonceStr;
    private String prepay_id;
    private String signType; //填入MD5
    private String paySign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}













