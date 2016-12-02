package com.scht.admin.service.impl;

import com.scht.admin.dao.ShopBankDao;
import com.scht.admin.service.ShopBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vanjoger on 2016/12/2.
 */
@Service
public class ShopBankServiceImpl implements ShopBankService {

    @Autowired
    ShopBankDao shopBankDao;

}
