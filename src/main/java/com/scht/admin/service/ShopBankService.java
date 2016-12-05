package com.scht.admin.service;

import com.scht.admin.entity.ShopBank;

/**
 * Created by vanjoger on 2016/12/2.
 */
public interface ShopBankService {

    ShopBank findById(String id);

    void update(ShopBank bank);
}
