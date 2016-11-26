package com.scht.admin.dao;

import com.scht.admin.entity.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vanjoger on 2016/11/26.
 */
@Repository
public interface ShopDao {
    List<Shop> listByAccount(@Param("account")String account);
}
