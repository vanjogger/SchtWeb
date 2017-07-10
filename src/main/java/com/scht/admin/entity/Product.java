package com.scht.admin.entity;

import com.scht.util.DateUtil;
import com.scht.util.StringUtil;

import java.util.Date;

/**
 * 商品
 * Created by Administrator on 2016/11/27.
 */
public class Product {


    private String id;
    private String title; //商品名称
    /**
     * @see com.scht.admin.bean.ProductTypeEnum
     */
    private String productType; //商品类型
    private String typeId; //分类id
    private String description; //简介
    private String price; //商品销售价格
    private String marketPrice;//市场价格
    private String agentId; //关联admin id
    private String images; //多图
    private int stock; //库存，  推广商品无库存
    private int saleCount; //销量
    private int virtualCount; //虚拟销量
    private int commentCount; //评论数量
    private String content; //详情
    private String status; //状态 NORMAL 上架  FROZEN 下架
    private long createTime;
    private int sort; //前台排序

    private String self; //0 自营商品 1 商家商品
    private String shopId; //商家id

    private boolean wb; //是否外卖商品  true 是 false 不是

    private String provinceId;//所在省id
    private String provinceName;//所在省名称
    private String cityId;//所在地市
    private String cityName;//
    private String districtId;//所在区域
    private String districtName;
    private String tc; //几人套餐

    private String score; //评分


    //以下不存数据库
    private String date; //时间
    private String typeName; //分类名称
    private String icon; //多图的第一个
    private String shopName; //商家名称
    private String juli ;//商家距离
    private String code; //商家code
    private String typeKey; //分类key
    private String shopTypeId; //商家分类ID

    public String getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(String shopTypeId) {
        this.shopTypeId = shopTypeId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJuli() {
        return juli;
    }

    public void setJuli(String juli) {
        this.juli = juli;
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

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public boolean isWb() {
        return wb;
    }

    public void setWb(boolean wb) {
        this.wb = wb;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public int getVirtualCount() {
        return virtualCount;
    }

    public void setVirtualCount(int virtualCount) {
        this.virtualCount = virtualCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
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

    public String getDate() {
        if(this.createTime > 0) {
            return DateUtil.getFormatDate(new Date(this.createTime), DateUtil.pattern_10);
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIcon() {
        if(!StringUtil.isNullOrEmpty(this.images)){
            return this.images.split("\\|")[0];
        }
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
