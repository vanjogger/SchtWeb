package com.scht.admin.entity;

/**
 * 会员资金
 * Created by Administrator on 2017/3/3.
 */
public class MemberMoney {

    private String id;
    private String memberId;
    private String money; //余额
    private String frozenMoney; //冻结资金
    private String totalMoney; //总资金

    public MemberMoney(){}

    public MemberMoney(String memberId){
        this.memberId = memberId;
        this.money = "0";
        this.frozenMoney = "0";
        this.totalMoney = "0";
    }

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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFrozenMoney() {
        return frozenMoney;
    }

    public void setFrozenMoney(String frozenMoney) {
        this.frozenMoney = frozenMoney;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }
}
