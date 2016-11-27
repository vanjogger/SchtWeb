package com.scht.admin.dao;

import com.scht.admin.entity.SubShop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vanjoger on 2016/11/27.
 */
@Repository
public interface SubShopDao {
    List<SubShop> listByShopId(@Param("shopId")String shopId);
}
