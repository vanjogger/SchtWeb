package com.scht.admin.dao;

import com.scht.admin.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/11/29.
 */
@Repository
public interface OrderDao {

    Order findByNo(String no);

}
