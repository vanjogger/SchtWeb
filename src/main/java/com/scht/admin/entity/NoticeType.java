package com.scht.admin.entity;

/**
 * 公告分类
 * Created by Administrator on 2016/11/23.
 */
public class NoticeType {

    private String id;
    private String name; //分类名称
    private String no; //分类编号
    private int sort; //排序
    private long createTime; //添加时间

    private String date; //时间字符串

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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
