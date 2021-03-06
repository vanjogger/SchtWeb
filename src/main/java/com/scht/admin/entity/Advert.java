package com.scht.admin.entity;

import com.scht.util.DateUtil;

import java.util.Date;

/**
 * 广告
 * Created by Administrator on 2016/11/22.
 */
public class Advert {

    private String id;
    private String title; //广告名称
    private String url; //广告跳转路径
    private String image; //图片路径
    private String status;//状态
    private String placeId; //广告位置 id
    private long startTime; //开始时间
    private long endTime; //关闭时间
    private long createTime; //添加时间
    private int sort; //paixu
    private String remark ; //备注
    private String agentId; //代理商ID
    private String provinceId;//所在省id
    private String provinceName;//所在省名称
    private String cityId;//所在地市
    private String cityName;//
    private String districtId;//所在区域
    private String districtName;



    private String startDate;//开始时间字符串，不存入数据库
    private String endDate; //结束时间字符串，不存入数据库

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStartDate() {
        if(this.startTime > 0) {
          return   DateUtil.getFormatDate(new Date(this.startTime),DateUtil.pattern_10);
        }
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        if(this.endTime > 0) {
            return DateUtil.getFormatDate(new Date(this.endTime), DateUtil.pattern_10);
        }
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
