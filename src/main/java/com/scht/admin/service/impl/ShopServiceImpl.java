package com.scht.admin.service.impl;

import com.scht.admin.dao.ShopDao;
import com.scht.admin.entity.Shop;
import com.scht.admin.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vanjoger on 2016/11/26.
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopDao shopDao;

    @Override
    public List<Shop> listByAccount(String account) {
        return this.shopDao.listByAccount(account);
    }
}
