package com.scht.admin.service.impl;

import com.scht.admin.dao.SubShopDao;
import com.scht.admin.entity.SubShop;
import com.scht.admin.service.SubShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wxh on 2016/11/27.
 */
@Service
public class SubShopServiceImpl implements SubShopService {

    @Autowired
    SubShopDao subShopDao;


    @Override
    public List<SubShop> listByShopId(String shopId) {
        return this.subShopDao.listByShopId(shopId);
    }
}
