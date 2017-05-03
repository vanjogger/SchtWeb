package com.scht.admin.entity;

import com.scht.util.DateUtil;

import java.util.Date;

/**
 * 优惠券发放记录
 * Created by wxh on 2017/4/24.
 */
public class CouponRecord {

    private String id;
    private String couponId; //优惠券ID
    private String couponName; //优惠券名称
    private String couponMoney; //优惠券金额
    private String memberId; //会员ID
    private String memberAccount; //会员账号
    private long createTime; //领取时间
    private boolean status; //使用状态， true 已使用 false 未使用
    private long useTime; //使用时间


    private String dateStr;
    private String useDate;

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(String couponMoney) {
        this.couponMoney = couponMoney;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        if(createTime > 0) {
            this.setDateStr(DateUtil.getFormatDate(new Date(createTime),DateUtil.pattern_16));
        }
        this.createTime = createTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        if(useTime > 0)
            this.useDate = DateUtil.getFormatDate(new Date(useTime), DateUtil.pattern_16);
        this.useTime = useTime;
    }
}
