package com.scht.admin.entity;

/**
 * 问答设置
 * Created by Administrator on 2017/3/1.
 */
public class QuestSet {

    private String id;
    private int dayCount; //每天可回答几道题

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }
}
