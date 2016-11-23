package com.scht.admin.entity;

import com.scht.util.DateUtil;

import java.util.Date;

/**
 * 公告
 * Created by Administrator on 2016/11/23.
 */
public class Notice {

    private String id;
    private String typeId; //分类id
    private String title; //标题
    private String content; //内容
    private long createTime; //添加时间
    private int sort; //排序
    private int viewCount; //阅读次数

    //以下字段不存入数据库
    private String date; //时间字符串
    private String typeName; //关联分类name 查询时使用，不存数据库
    private String typeNo; //关联分类no,查询时使用

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getDate() {
        if(this.createTime > 0) {
            return DateUtil.getFormatDate(new Date(createTime),DateUtil.pattern_10);
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
