package com.scht.admin.entity;

import com.scht.util.DateUtil;

import java.util.List;

/**
 * 订单
 * Created by Administrator on 2016/11/28.
 */
public class Order {

    private String id;
    private String no; //订单流水号
    private String orderType; //订单类型， ProductTypeEnum

    private String memberId; //会员id
    private String memberAccount; //会员账号
    private String shopId; //商家id
    private String agentId; //商品时代理商添加的保存代理商id

    private long createTime; //下单时间
    private long payTime; //支付时间
    private long dispatchTime; //发货时间
    private long successTime; //完成时间
    private long overTime; //关闭时间
    private long limitTime; //下一时间节点，支付时限、收货时限，其他为0

    private String status; //OrderStatus
    private String totalMoney;//订单总金额
    private String remark; //订单备注

    private String express; //是否需要发货， 0 到店消费， 1 发货
    private String code; //到店消费的消费码，
    private String userName;//收货人
    private String address;//收货地址
    private String telephone; //联系电话

    private String payType; //支付类型  PayType
    private String expressName; //物流公司
    private String expressNo; //物流编号

    private String memberAssess; //会员是否评价， 0 未评价、 1 已评价
    private String shopAssess; //是否回评， 0 未回评 1 已回评


    //一下不存数据库
    private String shopName; //商家名称
    private String shopIcon; //商家图标
    private List<OrderProduct> list; //订单商品列表
    private String createDate;
    private String payDate;
    private String dispatchDate;
    private String successDate;
    private String overDate;

    public String getShopIcon() {
        return shopIcon;
    }

    public void setShopIcon(String shopIcon) {
        this.shopIcon = shopIcon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    public long getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(long dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public long getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(long successTime) {
        this.successTime = successTime;
    }

    public long getOverTime() {
        return overTime;
    }

    public void setOverTime(long overTime) {
        this.overTime = overTime;
    }

    public long getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(long limitTime) {
        this.limitTime = limitTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getMemberAssess() {
        return memberAssess;
    }

    public void setMemberAssess(String memberAssess) {
        this.memberAssess = memberAssess;
    }

    public String getShopAssess() {
        return shopAssess;
    }

    public void setShopAssess(String shopAssess) {
        this.shopAssess = shopAssess;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<OrderProduct> getList() {
        return list;
    }

    public void setList(List<OrderProduct> list) {
        this.list = list;
    }

    public String getCreateDate() {
        if(this.createTime > 0) {
            return DateUtil.getDateFromLong(this.createTime);
        }
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPayDate() {
        if(this.payTime > 0){
            return DateUtil.getDateFromLong(this.payTime);
        }
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getDispatchDate() {
        if(this.dispatchTime > 0) {
            return DateUtil.getDateFromLong(this.dispatchTime);
        }
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public String getSuccessDate() {
        if(this.successTime > 0) {
            return DateUtil.getDateFromLong(this.successTime);
        }
        return successDate;
    }

    public void setSuccessDate(String successDate) {
        this.successDate = successDate;
    }

    public String getOverDate() {
        if(this.overTime > 0) return DateUtil.getDateFromLong(this.overTime);
        return overDate;
    }

    public void setOverDate(String overDate) {
        this.overDate = overDate;
    }
}
