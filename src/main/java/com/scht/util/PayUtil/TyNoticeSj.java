/**
 * @(#) TyNoticeSj.java    2014/10/29 08:57
 *
 * 鐗堟潈鎵?鏈? (c) 鍖椾含閾惰蒋缃戠粶鎶?鏈湁闄愬叕鍙?
 * 鍖椾含甯傛捣娣?鍖轰笂鍦板浗闄呭垱涓氬洯瑗垮尯1鍙?
 * 淇濈暀鎵?鏈夋潈鍒?
 */
package com.scht.util.PayUtil;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 张战军
 * @version 1.0
 */
public class TyNoticeSj {
    private String return_code;//返回状态码  SUCCESS/FAIL
    private String return_msg;//返回信息                     不是必填
    //以下字段在return_code为success的时候有返回
    private String appid;//公众账号id
    private String mch_id;//商户号
    private String device_info;//设备号                     不是必填
    private String nonce_str;//随机字符串
    private String sign;//签名
    private String result_code;//   SUCCESS/FAIL
    private String err_code;//错误代码               不是必填
    private String err_code_des;//错误代码描述     不是必填
    //以下字段在return_code he result_code都为success的时候有返回
    private String openid;//用户标示
    private String is_subscribe;//是否关注公众账号   Y 关注  N  未关注
    private String trade_type;//交易类型     JSAPI  NATIVE   MICROPAY   APP
    private String bank_type;//付款银行    银行类型 采用字符串类型的银行标示
    private int total_fee;  //单位为分
    private String coupon_fee;//现金券金额   现金券支付金额<=订单总金额  订单总金额-现金券金额=现金支付金额
    private String fee_type;//货币类型  符合ISO 4217标准的三位字母代码 默认为人民币：CNY   不是必填
    private String transaction_id;//微信支付订单号
    private String out_trade_no;// 商户订单号
    private String attach;//商家数据包   商家数据包 原样返回    不是必填
    private String time_end;//支付完成时间

    //商户处理后同步返回给微信的数据
    //return_code   return_msg

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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(String coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    @Override
    public String toString() {
        return "TyNoticeSj{" +
                "return_code='" + return_code + '\'' + "\n" +
                ", return_msg='" + return_msg + '\'' + "\n" +
                ", appid='" + appid + '\'' +     "\n" +
                ", mch_id='" + mch_id + '\'' +    "\n" +
                ", device_info='" + device_info + '\'' +   "\n" +
                ", nonce_str='" + nonce_str + '\'' +   "\n" +
                ", sign='" + sign + '\'' +           "\n" +
                ", result_code='" + result_code + '\'' +  "\n" +
                ", err_code='" + err_code + '\'' +        "\n" +
                ", err_code_des='" + err_code_des + '\'' +  "\n" +
                ", openid='" + openid + '\'' +              "\n" +
                ", is_subscribe='" + is_subscribe + '\'' +  "\n" +
                ", trade_type='" + trade_type + '\'' +    "\n" +
                ", bank_type='" + bank_type + '\'' +      "\n" +
                ", total_fee=" + total_fee +              "\n" +
                ", coupon_fee='" + coupon_fee + '\'' +     "\n" +
                ", fee_type='" + fee_type + '\'' +           "\n" +
                ", transaction_id='" + transaction_id + '\'' +   "\n" +
                ", out_trade_no='" + out_trade_no + '\'' +   "\n" +
                ", attach='" + attach + '\'' +           "\n" +
                ", time_end='" + time_end + '\'' +     "\n" +
                '}';
    }
}































