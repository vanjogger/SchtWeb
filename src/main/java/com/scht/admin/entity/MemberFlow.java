package com.scht.admin.entity;

import com.scht.util.DateUtil;

import java.util.Date;

/**
 * 会员资金流水
 * Created by Administrator on 2017/3/3.
 */
public class MemberFlow {

    private String id;
    private String memberId;
    private String memberAccount;
    private String type;  //MemberFlowType
    private String beforeAmount;//变动前金额
    private String amount;//变动金额
    private String afterAmount;//变动后金额
    private Long createTime;

    //not save
    private String dateStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBeforeAmount() {
        return beforeAmount;
    }

    public void setBeforeAmount(String beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAfterAmount() {
        return afterAmount;
    }

    public void setAfterAmount(String afterAmount) {
        this.afterAmount = afterAmount;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        if(createTime > 0) {
            this.dateStr = DateUtil.getFormatDate(new Date(createTime), DateUtil.pattern_16);
        }
        this.createTime = createTime;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}
