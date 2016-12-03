package com.scht.admin.service.impl;

import com.scht.admin.dao.OrderProductDao;
import com.scht.admin.entity.OrderProduct;
import com.scht.admin.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    OrderProductDao orderProductDao;
    @Override
    public List<OrderProduct> listByOrderId(String orderId) {
        return orderProductDao.listByOrderId(orderId);
    }
}
