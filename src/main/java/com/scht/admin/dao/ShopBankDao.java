package com.scht.admin.dao;

import com.scht.admin.entity.ShopBank;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by vanjoger on 2016/12/2.
 */
@Repository
public interface ShopBankDao {
    void insert(ShopBank bank);

    ShopBank findById(@Param("id")String id);

    void update(ShopBank bank);
}
