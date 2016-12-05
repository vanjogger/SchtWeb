package com.scht.admin.service.impl;

import com.scht.admin.dao.BaseMyBatisDao;
import com.scht.admin.dao.MemberDao;
import com.scht.admin.dao.ShopBankDao;
import com.scht.admin.entity.ShopBank;
import com.scht.admin.service.ShopBankService;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vanjoger on 2016/12/2.
 */
@Service
@Transactional
public class ShopBankServiceImpl implements ShopBankService {

    @Autowired
    ShopBankDao shopBankDao;
    @Autowired
    BaseMyBatisDao baseMyBatisDao;



    @Override
    public ShopBank findById(String id) {
        return shopBankDao.findById(id);
    }

    @Override
    public void update(ShopBank bank){
            this.shopBankDao.update(bank);
    }
}
