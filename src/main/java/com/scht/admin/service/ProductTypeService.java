package com.scht.admin.service;

import com.scht.admin.entity.ProductType;

/**
 * Created by Administrator on 2016/11/27.
 */

public interface ProductTypeService {

    ProductType findByKey(String key, String type);
}
