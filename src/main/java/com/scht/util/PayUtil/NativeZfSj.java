/**
 * @(#) NativeZfSj.java    2014/10/27 15:32
 *
 * 鐗堟潈鎵?鏈? (c) 鍖椾含閾惰蒋缃戠粶鎶?鏈湁闄愬叕鍙?
 * 鍖椾含甯傛捣娣?鍖轰笂鍦板浗闄呭垱涓氬洯瑗垮尯1鍙?
 * 淇濈暀鎵?鏈夋潈鍒?
 */
package com.scht.util.PayUtil;

/**
 * 类的功能，目的，描述等写在此处
 * native支付 请求商家获取商品信息内容    以及返回信息内容
 * @author 张战军
 * @version 1.0
 */
public class NativeZfSj {
    private String appid;//公众账号id
    private String openid;//用户标示
    private String mch_id;//商户号
    private String is_subscribe;//是否关注公众号  Y关注 N未关注
    private String nonce_str;//随机字符串
    private String product_id;//商品id
    private String sign;//签名

    //商户返回微信的结果
    private String return_code;//返回状态码  SUCCESS/FAIL
    private String return_msg;//返回信息 不是必填  如非空  为错误原因  签名失败  参数格式校验错误
    //以下字段在return_code为SUCCESS的时候有返回
    //appid  mch_id nonce_str  sign
    private String prepay_id;//预支付id
    private String result_code;//业务结果  SUCCESS/FAIL
    private String err_code_des;//错误描述 不是必填  当result_code 为fail时，返回错误信息，微信直接展示给用户，例如：订单失效 无效订单

    private String code_url; //二维码

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }
}
