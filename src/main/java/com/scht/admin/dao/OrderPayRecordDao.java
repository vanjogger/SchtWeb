package com.scht.admin.dao;

import com.scht.admin.entity.OrderPayRecord;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/12/5.
 */
@Repository
public interface OrderPayRecordDao {

    //根据流水号查询
    OrderPayRecord findByNo(String no);

    //查询支付成功的支付记录，
    OrderPayRecord findByOrderId(String orderId);
}
