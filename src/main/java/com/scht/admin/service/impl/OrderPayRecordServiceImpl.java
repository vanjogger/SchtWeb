package com.scht.admin.service.impl;

import com.scht.admin.dao.OrderPayRecordDao;
import com.scht.admin.entity.OrderPayRecord;
import com.scht.admin.service.OrderPayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/13.
 */
@Service
public class OrderPayRecordServiceImpl implements OrderPayRecordService {

    @Autowired
    OrderPayRecordDao orderPayRecordDao;
    @Override
    public OrderPayRecord findByNo(String no) {
        return orderPayRecordDao.findByNo(no);
    }
}
