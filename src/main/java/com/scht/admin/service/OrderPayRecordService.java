package com.scht.admin.service;

import com.scht.admin.entity.OrderPayRecord;

/**
 * Created by Administrator on 2016/12/13.
 */
public interface OrderPayRecordService {

    OrderPayRecord findByNo(String no);
}
