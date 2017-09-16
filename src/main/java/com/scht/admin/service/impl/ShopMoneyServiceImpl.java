package com.scht.admin.service.impl;

import com.scht.admin.dao.ShopMoneyDao;
import com.scht.admin.entity.ShopMoney;
import com.scht.admin.service.ShopMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wxh on 2016/11/27.
 */
@Service
public class ShopMoneyServiceImpl implements ShopMoneyService {

    @Autowired
    ShopMoneyDao shopMoneyDao;


    @Override
    public List<ShopMoney> listByShopId(String shopId) {
        return shopMoneyDao.listByShopId(shopId);
    }
}
