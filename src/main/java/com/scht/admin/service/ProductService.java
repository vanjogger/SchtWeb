package com.scht.admin.service;

import com.scht.front.bean.RetResult;

import java.util.List;
import java.util.Map;

/**
 * Created by wxh on 2016/12/11.
 */
public interface ProductService {
    RetResult list(String id, String productName, int pageNo, int pageSize);

    List regionList(Map<String, Object> map);
}
