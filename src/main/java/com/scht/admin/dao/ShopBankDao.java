package com.scht.admin.dao;

import com.scht.admin.entity.ShopBank;
import com.scht.front.bean.RestProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wxh on 2016/12/2.
 */
@Repository
public interface ShopBankDao {
    void insert(ShopBank bank);

    ShopBank findById(@Param("id")String id);

    void update(ShopBank bank);

    List<RestProduct> list(@Param("shopId")String shopId, @Param("start")int start, @Param("size")int size);

    Integer count(@Param("shopId")String shopId);
}
