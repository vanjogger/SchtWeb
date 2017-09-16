package com.scht.admin.entity;

/**
 * Created by wxh on 2016/11/26.
 */
public class AgentMoney {

    private String id;
    private String agentId;//代理商id
    private String agentName;
    private String availAmount;//可用余额  小数点后保留2位
    private String frozenAmount;//冻结金额
    private String totalAmount;//累计金额

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAvailAmount() {
        return availAmount;
    }

    public void setAvailAmount(String availAmount) {
        this.availAmount = availAmount;
    }

    public String getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(String frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
