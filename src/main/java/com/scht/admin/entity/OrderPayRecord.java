package com.scht.admin.entity;

/**
 * 订单支付记录
 * 向第三方支付平台支付一次，生成一条新的记录
 * Created by Administrator on 2016/12/5.
 */
public class OrderPayRecord {

    private String id;
    private String no; //支付流水号，每次提交生成一个
    private String orderId; //对应的订单id
    private String orderNo; //对应的订单编号
    private String memberId; //会员id

    private String totalMoney;//总金额
    private String balance; //使用余额
    private String money; //支付金额
    private String payType; //支付方式

    /**
     * @see com.scht.admin.bean.PayStatus
     */
    private String status; //支付状态 ，
    private Long createTime; //支付时间
    private Long payTime; //支付返回时间

    private String tradeNo; //第三方支付平台的交易号码，支付成功后返回
    private String buyer; //买家账号或openId， 支付成功后返回

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}
