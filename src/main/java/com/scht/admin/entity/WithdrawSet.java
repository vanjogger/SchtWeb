package com.scht.admin.entity;

/**
 * 提现设置
 * Created by Administrator on 2016/11/25.
 */
public class WithdrawSet {

    private String id;
    private String agentMin; //代理商最小提现金额
    private String agentRate; //代理商手续费，单位：%
    private String shopMin; //商家最小提现金额
    private String shopRate; //商家提现手续费,单位：%

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgentMin() {
        return agentMin;
    }

    public void setAgentMin(String agentMin) {
        this.agentMin = agentMin;
    }

    public String getAgentRate() {
        return agentRate;
    }

    public void setAgentRate(String agentRate) {
        this.agentRate = agentRate;
    }

    public String getShopMin() {
        return shopMin;
    }

    public void setShopMin(String shopMin) {
        this.shopMin = shopMin;
    }

    public String getShopRate() {
        return shopRate;
    }

    public void setShopRate(String shopRate) {
        this.shopRate = shopRate;
    }
}
