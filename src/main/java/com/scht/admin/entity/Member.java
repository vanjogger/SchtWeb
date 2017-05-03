package com.scht.admin.entity;

import com.scht.util.DateUtil;

/**
 * 会员
 * Created by Administrator on 2016/11/25.
 */
public class Member {

    private String id;
    private String account; //账号
    private String password;//登录密码

    private String nick; //昵称
    private String headIcon; //头像
    private String address; //地址
    private String telephone; //联系电话
    private String qq; //联系QQ
    private String weixin; //联系微信

    private String status; //状态
    private long createTime; //注册时间

    private String openId; //微信关联

    //一下字段不存入数据库
    private String date; //时间字符串，不存数据库
    private String money; //会员余额

    private long shopCollects; //收藏的商家数量
    private long productColects; //收藏的商品数量

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public long getShopCollects() {
        return shopCollects;
    }

    public void setShopCollects(long shopCollects) {
        this.shopCollects = shopCollects;
    }

    public long getProductColects() {
        return productColects;
    }

    public void setProductColects(long productColects) {
        this.productColects = productColects;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDate() {
        if(this.createTime > 0){
            return DateUtil.getDateFromLong(this.createTime);
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
