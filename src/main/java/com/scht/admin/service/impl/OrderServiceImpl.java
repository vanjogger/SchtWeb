package com.scht.admin.service.impl;

import com.scht.admin.bean.OrderStatus;
import com.scht.admin.dao.OrderDao;
import com.scht.admin.dao.OrderProductDao;
import com.scht.admin.dao.ProductDao;
import com.scht.admin.entity.Order;
import com.scht.admin.entity.OrderProduct;
import com.scht.admin.entity.Product;
import com.scht.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderProductDao orderProductDao;

    @Autowired
    ProductDao productDao;
    /**
     * 订单定时任务
     */
    @Override
    public void updateTask() {
        long time = System.currentTimeMillis();
        //查询limitTime < time 的订单，并且订单状态是create / dispatch的
        List<Order> list = orderDao.listForTask(time);
        if(list != null && list.size() > 0) {
            List<String> orderIds = new ArrayList<>();
            for(Order order : list){
                if(OrderStatus.CREATE.name().equals(order.getStatus())){
                    //待支付的，关闭
                    order.setStatus(OrderStatus.CLOSE.name());
                    order.setOverTime(System.currentTimeMillis());
                    //todo：商品库存返回
                    orderIds.add(order.getId());
                }else if(OrderStatus.DISPATCH.name().equals(order.getStatus())) {
                    order.setStatus(OrderStatus.SUCCESS.name());
                    order.setSuccessTime(System.currentTimeMillis());
                }
                orderDao.update(order);
            }
            if(orderIds.size() > 0) {
                updateProductStock(orderIds);
            }
        }

    }

    private void updateProductStock(List<String> orderIds){
        List<OrderProduct> list = orderProductDao.listByOrderIds(orderIds.toArray(new String[0]));
        if(list != null && list.size() > 0) {
            Map<String,Integer> map = new HashMap<>();
            List<String> productIds = new ArrayList<>();
            int count = 0;
            for(OrderProduct orderProduct : list){
                productIds.add(orderProduct.getProductId());
                if(map.get(orderProduct.getProductId()) != null) {
                    count = map.get(orderProduct.getProductId());
                }else{
                    count = 0;
                }
                count += orderProduct.getAmount();
                map.put(orderProduct.getProductId(), count);
            }
            //查询商品
            List<Product> products = productDao.listByIds(productIds.toArray(new String[0]));
            if(products != null && products.size() > 0){
                for(Product product : products) {
                    if(map.get(product.getId()) != null) {
                        //更改商品库存
                        productDao.updateStock(product.getId(), map.get(product.getId()));
                    }
                }
            }
        }
    }
}
