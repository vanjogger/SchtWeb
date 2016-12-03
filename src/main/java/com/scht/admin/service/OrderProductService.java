package com.scht.admin.service;

import com.scht.admin.entity.OrderProduct;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface OrderProductService {

    List<OrderProduct> listByOrderId(String orderId);
}
