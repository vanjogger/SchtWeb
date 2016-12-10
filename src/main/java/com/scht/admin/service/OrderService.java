package com.scht.admin.service;

import com.scht.admin.entity.Order;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface OrderService {

    Order findByCode(String code);
    //验证成功订单，订单完成
    void successOrder(Order id);

    //定时任务
    void updateTask();

    Integer countOrder(Map params);
}
