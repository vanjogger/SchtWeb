/**
 * @(#) TyZf.java    2014/10/27 16:36
 *
 * 鐗堟潈鎵?鏈? (c) 鍖椾含閾惰蒋缃戠粶鎶?鏈湁闄愬叕鍙?
 * 鍖椾含甯傛捣娣?鍖轰笂鍦板浗闄呭垱涓氬洯瑗垮尯1鍙?
 * 淇濈暀鎵?鏈夋潈鍒?
 */
package com.scht.util.PayUtil;

/**
 * 类的功能，目的，描述等写在此处
 *  微信支付  统一支付接口
 * @author 张战军
 * @version 1.0
 */
public class TyZf {
    private String appid;//公众账号id
    private String mch_id;//商户号
    private String device_info;//设备号 不是必填
    private String nonce_str;//随机字符串
    private String sign;//签名
    private String body;//商品描述
    private String attach;//附加数据    不是必填
    private String out_trade_no;//商户订单号
    private int total_fee;//总金额
    private String spbill_create_ip;//终端ip
    private String time_start;//交易起始时间  不是必填
    private String time_expire;//交易结束时间  不是必填
    private String goods_tag;//商品标记     不是必填
    private String notify_url;//通知地址
    private String trade_type;//交易类型  JSAPI  NATIVE   APP
    private String openid;//用户标示  不是必填
    private String product_id;//商品id   不是必填 只有trade_type 为native的时候需要填写。。

    //返回参数
    private String return_code;//返回状态吗     SUCCESS/FAIL
    private String return_msg;//返回信息  如非空  为错误原因 签名失败 参数格式校验错误     不是必填
    //以下字段在return_code 为SUCCESS的时候有返回
    //          appid   mch_id   device_info 不是必填  nonce_str  sign
    private String result_code;//业务结果  SUCCESS//FAIL
    private String err_code;//错误代码   不是必填
    private String err_code_des;//错误代码描述   不是必填
    //以下字段在return_code 和 result_code都为SUCCESS的时候有返回
    //         trade_type
    private String prepay_id;//预支付id
    private String code_url;//二维码链接     不是必填

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }
}
