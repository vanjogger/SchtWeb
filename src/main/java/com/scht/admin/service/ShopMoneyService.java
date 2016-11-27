package com.scht.admin.service;

import com.scht.admin.entity.ShopMoney;

import java.util.List;

/**
 * Created by vanjoger on 2016/11/27.
 */
public interface ShopMoneyService {

    List<ShopMoney> listByShopId(String shopId);
}
