package com.scht.front.bean;

import com.scht.common.DictionaryConfigHelper;

import java.io.Serializable;


/**
 * Created by Administrator on 2014/12/5.
 */
public class RetResult implements Serializable {

	private String resCode;

	private String resMsg;

	private RetData data;

	public RetResult() {

	}

	public RetData getData() {
		return data;
	}

	public void setData(RetData data) {
		this.data = data;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public RetResult(String code) {
		this.resCode = code;
		this.resMsg = DictionaryConfigHelper.getDictionaryValue("RetCode", code, "");
	}

	public static class RetCode {

        //请求正确
        public static final String OK = "0";
        //错误的请求格式
        public static final String BadRequest = "400";
        //鉴权失败
        public static final String AuFail = "401";
        //请求参数错误
        public static final String Execute_Error = "402";
        //系统错误
        public static final String System_Error = "500";
        //操作失败
        public static final String Operate_Error = "406";
        //非法操作
        public static final String Illegal_Request = "10000";

		//用户接口返回码以 100 打头
        //用户不存在
        public static final String User_Not_Exist = "10001";
        //用户登录密码错误
        public static final String User_UnCorrect_Pwd = "10002";
        //当前用户处于冻结状态 
        public static final String User_Frozen = "10003";
        //短信发送失败
        public static final String Sms_Fail = "10004";
        //短信验证码错误
        public static final String ValidateCode_Error = "10005";

        //手机号码已注册
        public static final String User_Already_Reg = "10011";
        //短信发送频繁
        public static final String Sms_Frequent = "10012";
        //短信验证码失效
        public static final String Sms_InValid = "10013";
        //用户旧密码错误
        public static final String User_Old_Pwd_Error = "10014";
		//收货地址不存在
		public static final String User_Address_Not_Exist = "10015";

		// 商家接口返回码以 200打头
		//商家不存在
		public static final String Shop_Not_Exist = "20001";
		//商家密码错误
		public static final String Shop_Pwd_Error = "20002";
		//银行卡不存在
		public static final String Shop_Bank_Not_Exist = "20003";

		//订单接口返回 以 300 开头
		public static final String Order_Not_exist = "30001"; //订单不存在
		public static final String Order_Status_Error = "30002"; //订单状态不允许该操作
		public static final String Order_Product_Error = "30003"; //商品不存在或库存不足
		
	}
}
