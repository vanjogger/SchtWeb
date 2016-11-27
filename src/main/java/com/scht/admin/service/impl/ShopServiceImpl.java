package com.scht.admin.service.impl;

import com.scht.admin.bean.Status;
import com.scht.admin.dao.BaseMyBatisDao;
import com.scht.admin.dao.ShopDao;
import com.scht.admin.dao.ShopMoneyDao;
import com.scht.admin.entity.Admin;
import com.scht.admin.entity.Shop;
import com.scht.admin.entity.ShopMoney;
import com.scht.admin.service.ShopService;
import com.scht.util.MD5Util;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by vanjoger on 2016/11/26.
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopDao shopDao;
    @Autowired
    BaseMyBatisDao baseMyBatisDao;
    @Autowired
    ShopMoneyDao shopMoneyDao;

    @Override
    public List<Shop> listByAccount(String account) {
        return this.shopDao.listByAccount(account);
    }

    @Override
    public String  save(Shop data, Admin admin) {
        try {
            List<Shop> list = this.shopDao.listByAccount(data.getAccount());
            if (StringUtil.isNotNull(data.getId())) {
                if (!list.isEmpty()) {
                    Shop shop = list.get(0);
                    if (!shop.getId().equals(data.getId()))
                        return "-1";
                }
                this.baseMyBatisDao.update(ShopDao.class, data);

                List<ShopMoney> moneys = this.shopMoneyDao.listByShopId(data.getId());
                if(!moneys.isEmpty()){
                    ShopMoney money = moneys.get(0);
                    money.setShopName(data.getName());
                    this.baseMyBatisDao.update(ShopMoneyDao.class,money);
                }else{
                    saveShopMoney(data);
                }
                return "0";
            } else {
                if (!list.isEmpty())
                    return "-1";

                data.setId(UUIDFactory.random());
                data.setStatus(Status.NORMAL.name());
                data.setCreateTime(new Date().getTime());
                data.setPassword(MD5Util.getMD5ofStr(data.getPassword()));
                data.setAgentId(admin.getId());
                this.baseMyBatisDao.insert(ShopDao.class, data);
                //保存商家资金表
                saveShopMoney(data);

               return "0";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "1";
    }

    private void saveShopMoney(Shop data) {
        ShopMoney money = new ShopMoney();
        money.setId(UUIDFactory.random());
        money.setShopId(data.getId());
        money.setShopName(data.getName());
        money.setAvailAmount("0");
        money.setFrozenAmount("0");
        money.setTotalAmount("0");
        this.baseMyBatisDao.insert(ShopMoneyDao.class, money);
    }

    @Override
    public List<Shop> listByIds(String[] strings) {
        return shopDao.listByIds(strings);
    }

    @Override
    public List<Shop> listByName(String name) {
        return shopDao.listByName(name);
    }
}
