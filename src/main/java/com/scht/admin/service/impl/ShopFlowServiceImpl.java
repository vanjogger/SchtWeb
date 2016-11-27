package com.scht.admin.service.impl;

import com.scht.admin.dao.ShopFlowDao;
import com.scht.admin.service.ShopFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vanjoger on 2016/11/27.
 */
@Service
public class ShopFlowServiceImpl implements ShopFlowService {

    @Autowired
    ShopFlowDao shopFlowDao;

}
