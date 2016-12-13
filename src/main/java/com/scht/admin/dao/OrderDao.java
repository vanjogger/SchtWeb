package com.scht.admin.dao;

import com.scht.admin.entity.Order;
import org.apache.ibatis.annotations.Param;
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

    List<Order> list(@Param("shopId")String shopId, @Param("status")String status,@Param("start") int start, @Param("size")int size);

    Integer count(@Param("shopId")String shopId, @Param("status")String status);
}
