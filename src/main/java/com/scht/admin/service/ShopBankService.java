package com.scht.admin.service;

import com.scht.admin.entity.ShopBank;
import com.scht.front.bean.RetResult;

/**
 * Created by wxh on 2016/12/2.
 */
public interface ShopBankService {

    ShopBank findById(String id);

    void update(ShopBank bank);

    RetResult list(String shopId, int pageNo, int pageSize);

    RetResult save(String shopId, String yhmc, String khh, String ckr, String kh);

    RetResult edit(String id, String yhmc, String khh, String ckr, String kh);

    RetResult delete(String id);
}
