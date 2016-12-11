package com.scht.admin.service;

import com.scht.front.bean.RetResult;

/**
 * Created by vanjoger on 2016/12/11.
 */
public interface ProductService {
    RetResult list(String id, String productName, int pageNo, int pageSize);
}
