package com.scht.admin.entity;

import com.scht.util.DateUtil;

/**
 * Created by vanjoger on 2016/12/10.
 */
public class ProductComment {

    private String id;
    private String productId;
    private String productName;
    private String orderId;
    private String shopId;
    private String grade;
    private String images;
    private String content;
    private String memberId;
    private Long createTime;
    private String status;//是否回复 0:否 1：是

    private String replyId;//回复人id
    private String replyContent;
    private Long replyTime; //

    //不关联数据库
    private String memberName;
    private String memberImg;
    private String replayName;
    private String dateStr;
    private String replayDateStr;

    public String getMemberImg() {
        return memberImg;
    }

    public void setMemberImg(String memberImg) {
        this.memberImg = memberImg;
    }

    public String getReplayName() {
        return replayName;
    }

    public void setReplayName(String replayName) {
        this.replayName = replayName;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getReplayDateStr() {
        if(replyTime!=null)
            return DateUtil.getDateFromLong(replyTime);
        return replayDateStr;
    }

    public void setReplayDateStr(String replayDateStr) {
        this.replayDateStr = replayDateStr;
    }

    public String getDateStr() {
        if(createTime!=null)
            return DateUtil.getDateFromLong(createTime);
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Long getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Long replyTime) {
        this.replyTime = replyTime;
    }
}
