package com.scht.admin.entity;

/**
 * 手机短信发送记录
 * Created by Administrator on 2016/12/5.
 */
public class MessageRecord {

    private String id;
    private String telephone;//手机号码
    private String message;//短信内容
    private long createTime; //发送时间
    private String status; //发送状态 0 成功， 1 失败
    private String code; //如果是验证码，则保存验证码，否则为空
    private String errMsg; //发送失败信息
    private String ip; //请求发送短信的ip
    private String type;//短信类型   1：注册  2：找回密码


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
