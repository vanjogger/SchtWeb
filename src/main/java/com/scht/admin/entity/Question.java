package com.scht.admin.entity;

import com.scht.util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * 问答
 * Created by Administrator on 2017/2/28.
 */
public class Question {

    private String id;
    private String shopId; //关联商家

    private String title; //题目
    private int sumCount; //共发多少题
    private String money; //单体多少钱
    private int count; //已经回答了多少题
    private long createTime; //添加时间
    private String status; //状态 Status
    private String icon; // 图片
    private String content; //内容

    //not save
    private List<QuestAnswer> list; //答案列表
    private String shopName; //商家名称
    private String dateStr; //时间

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public List<QuestAnswer> getList() {
        return list;
    }

    public void setList(List<QuestAnswer> list) {
        this.list = list;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSumCount() {
        return sumCount;
    }

    public void setSumCount(int sumCount) {
        this.sumCount = sumCount;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        if(createTime > 0) {
            setDateStr(DateUtil.getFormatDate(new Date(createTime),DateUtil.pattern_10));
        }
        this.createTime = createTime;
    }
}
