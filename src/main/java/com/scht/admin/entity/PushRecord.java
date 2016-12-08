package com.scht.admin.entity;

import com.scht.util.UUIDFactory;

/**
 * 推送记录
 * Created by Administrator on 2016/12/8.
 */
public class PushRecord {

    private String id;
    private String type; //推送类型  order 订单 message 消息
    private String targetType; //推送对象， MEMBER 会员，SHOP 商家 ALL 所有
    private String memberId; //会员id
    private String shopId; //商家id
    private String title; //推送内容
    private String content; //内容
    private String targetId; //目标id orderId
    private long createTime; //发送时间

    PushRecord (){}



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public static PushRecord createMemberOrder( String memberId, String title,String content,String targetId){
        PushRecord record = new PushRecord();
        record.setId(UUIDFactory.random());
        record.setType("order");
        record.setTargetType("MEMBER");
        record.setMemberId(memberId);
        record.setTitle(title);
        record.setContent(content);
        record.setTargetId(targetId);
        record.setCreateTime(System.currentTimeMillis());
        return record;
    }
    public static PushRecord createShopOrder( String shopId, String title,String content,String targetId){
        PushRecord record = new PushRecord();
        record.setId(UUIDFactory.random());
        record.setType("order");
        record.setTargetType("SHOP");
        record.setMemberId(shopId);
        record.setTitle(title);
        record.setContent(content);
        record.setTargetId(targetId);
        record.setCreateTime(System.currentTimeMillis());
        return record;
    }
}
