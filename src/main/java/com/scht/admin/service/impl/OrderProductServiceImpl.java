package com.scht.admin.service.impl;

import com.scht.admin.dao.OrderProductDao;
import com.scht.admin.dao.ProductDao;
import com.scht.admin.entity.OrderProduct;
import com.scht.admin.entity.Product;
import com.scht.admin.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1.
 */
@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    OrderProductDao orderProductDao;
    @Autowired
    ProductDao productDao;
    @Override
    public List<OrderProduct> listByOrderId(String orderId) {
        List<OrderProduct> list = orderProductDao.listByOrderId(orderId);
        if(list != null && list.size() > 0) {
            List<String> ids = new ArrayList<>();
            for(OrderProduct product : list) {
                ids.add(product.getProductId());
            }
            List<Product> products = productDao.listByIds(ids.toArray(new String[0]));
            if(products != null && products.size() > 0) {
                Map<String, Product> map = new HashMap<>();
                for (Product product : products) {
                    map.put(product.getId(), product);
                }
                for(OrderProduct product : list) {
                    Product p = map.get(product.getProductId());
                    if(p != null) {
                        product.setProductDesc(p.getDescription());
                    }
                }
            }
        }
        return list;
    }
}
