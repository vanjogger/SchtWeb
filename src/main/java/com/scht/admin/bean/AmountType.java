package com.scht.admin.bean;

/**
 * Created by Administrator on 2016/5/10.
 */
public enum AmountType {

        ZtFee("直推奖"),
        PointFee("点奖"),
        LevelFee("层奖"),
        ManageFee("管理费"),
        FhFee("直荐分红"),
        MemberFee("报单扣费"),
        UpdateFee("升级扣费"),
        TransferCashDesFee("转账电子币扣费"),
        TransferCashAddFee("转账电子币增费"),
        TransferBonusDesFee("转账购物币扣费"),
        TransferBonusAddFee("转账购物币增费"),
        TxFee("提现扣费"),
        Recharge("充值"),
        TxBack("提现退回"),
        InnerFee("账内转账");


    AmountType(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }
}
