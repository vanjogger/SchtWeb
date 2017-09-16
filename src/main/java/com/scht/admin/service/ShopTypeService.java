package com.scht.admin.service;

import com.scht.admin.entity.ShopType;
import com.scht.common.PageInfo;
import com.scht.front.bean.RetResult;

import java.util.List;
import java.util.Map;

/**
 * Created by wxh on 2016/11/26.
 */
public interface ShopTypeService {
    List<ShopType> listBySort(int sort);

    List<ShopType> listMap(Map params);

    List<ShopType> listByKey(String key);

    List<ShopType> listAll(Map params);


    RetResult list();
}
