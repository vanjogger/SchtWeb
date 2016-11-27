package com.scht.admin.service.impl;

import com.scht.admin.dao.ProductTypeDao;
import com.scht.admin.entity.ProductType;
import com.scht.admin.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/27.
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeDao productTypeDao;
    @Override
    public ProductType findByKey(String key) {
        return productTypeDao.findByKey(key);
    }
}
