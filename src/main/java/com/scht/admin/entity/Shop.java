package com.scht.admin.entity;

import com.scht.util.StringUtil;

import java.util.List;

/**
 * Created by vanjoger on 2016/11/26.
 */
public class Shop {

    private String id;
    private String name;//商家名称
    private String account;//商家账号
    private String password;
    private String shopTypeId;//分类
    private String type;//0：普通商家  1：连锁商家
    private String icon;//商家图标
    private String status;//Status
    private String linkName;//联系人
    private String linkMobile;
    private String linkAddress;
    private String agentId;//添加人 对应admin
    private String remark;
    private Long createTime;//
    private Float lng;//经度
    private Float lnt;//纬度


    private List<SubShop> list;
    private int subSize;

    public int getSubSize() {
        if(StringUtil.isNotEmpty(list))
            return list.size();
        return 0;
    }

    public void setSubSize(int subSize) {
        this.subSize = subSize;
    }

    public List<SubShop> getList() {
        return list;
    }

    public void setList(List<SubShop> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(String shopTypeId) {
        this.shopTypeId = shopTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkMobile() {
        return linkMobile;
    }

    public void setLinkMobile(String linkMobile) {
        this.linkMobile = linkMobile;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Float getLnt() {
        return lnt;
    }

    public void setLnt(Float lnt) {
        this.lnt = lnt;
    }
}
