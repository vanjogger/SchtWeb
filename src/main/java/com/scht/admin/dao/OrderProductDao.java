package com.scht.admin.dao;

import com.scht.admin.entity.OrderProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
@Repository
public interface OrderProductDao {

    List<OrderProduct> listByOrderId(String orderId);
}
