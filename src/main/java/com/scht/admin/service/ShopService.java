package com.scht.admin.service;

import com.scht.admin.entity.Shop;

import java.util.List;

/**
 * Created by vanjoger on 2016/11/26.
 */
public interface ShopService {
    List<Shop> listByAccount(String account);
}
