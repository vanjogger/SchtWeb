package com.scht.admin.dao;

import com.scht.admin.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/29.
 */
@Repository
public interface OrderDao {

    Order findByNo(String no);

    List<Order> listForTask(long time);

    void update(Order order);

    Order findByCode(String code);

    Integer countOrder(Map params);
}
