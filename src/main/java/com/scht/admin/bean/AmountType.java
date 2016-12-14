package com.scht.admin.bean;

/**
 * Created by Administrator on 2016/5/10.
 */
public enum AmountType {

        OrderFee("订单金额"),
        HandFee("手动调整"),
        TxFee("提现扣费"),
        Recharge("充值");



    AmountType(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }
}
