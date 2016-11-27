package com.scht.admin.service.impl;

import com.scht.admin.dao.ShopTypeDao;
import com.scht.admin.entity.ShopType;
import com.scht.admin.service.ShopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by vanjoger on 2016/11/26.
 */
@Service
public class ShopTypeServiceImpl implements ShopTypeService {

    @Autowired
    ShopTypeDao shopTypeDao;

    @Override
    public List<ShopType> listBySort(int sort) {
        return shopTypeDao.listBySort(sort);
    }

    @Override
    public List<ShopType> listMap() {
        return this.shopTypeDao.listMap();
    }

    @Override
    public List<ShopType> listByKey(String key) {
        return this.shopTypeDao.listByKey(key);
    }
}
