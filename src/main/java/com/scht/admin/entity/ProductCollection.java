package com.scht.admin.entity;

import com.scht.util.DateUtil;

/**
 * 商品收藏
 * Created by Administrator on 2016/12/15.
 */
public class ProductCollection {

    private String id;
    private String memberId;
    private String productId;
    private String productName;
    private String productIcon;
    private String productPrice;
    private Long createTime;

    private String date;

    public String getDate() {
        if(this.createTime > 0) {
            return DateUtil.getDateFromLong(this.createTime);
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
