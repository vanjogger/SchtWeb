package com.scht.admin.service.impl;

import com.scht.admin.dao.ShopWithDrawalsDao;
import com.scht.admin.entity.ShopWithDrawals;
import com.scht.admin.service.ShopWithDrawalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by wxh on 2016/11/27.
 */
@Service
public class ShopWithDrawalsServiceImpl implements ShopWithDrawalsService {

    @Autowired
    ShopWithDrawalsDao shopWithDrawalsDao;


    @Override
    public Integer countWithDrawals(Map params) {
        return shopWithDrawalsDao.countWithDrawals(params);
    }
}
