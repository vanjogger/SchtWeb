package com.scht.admin.service;

import com.scht.admin.entity.SubShop;

import java.util.List;

/**
 * Created by wxh on 2016/11/27.
 */
public interface SubShopService {
    List<SubShop> listByShopId(String shopId);
}
