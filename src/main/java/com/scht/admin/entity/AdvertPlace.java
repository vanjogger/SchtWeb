package com.scht.admin.entity;

/**
 * 广告位
 * Created by Administrator on 2016/11/21.
 */
public class AdvertPlace {

    private String id;
    private String title; //广告位名称
    private int width; //广告位宽度
    private int height; //广告位高度
    private String code; //广告位调用代码，唯一

    private long createTime; //添加时间

    private String remark; //备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
