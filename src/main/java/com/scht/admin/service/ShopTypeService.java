package com.scht.admin.service;

import com.scht.admin.entity.ShopType;

import java.util.List;
import java.util.Map;

/**
 * Created by vanjoger on 2016/11/26.
 */
public interface ShopTypeService {
    List<ShopType> listBySort(int sort);

    List<ShopType> listMap();

    List<ShopType> listByKey(String key);
}
