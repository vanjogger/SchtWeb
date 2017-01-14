package com.scht.admin.dao;

import com.scht.admin.entity.Shop;
import com.scht.front.bean.RestShop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by vanjoger on 2016/11/26.
 */
@Repository
public interface ShopDao {
    List<Shop> listByAccount(@Param("account")String account);

    List<Shop> listByIds(String[] ids);

    List<Shop> listByName(@Param("name")String name);

    Integer countShop(Map params);


    List<RestShop> list(@Param("name")String name,@Param("shopTypeKey")String shopTypeKey, @Param("sortType")String sortType, @Param("type")String type, @Param("code")String code, @Param("start")int start, @Param("size")int size);

    Integer count(@Param("districtId")String districtId,@Param("name")String name,@Param("shopTypeKey")String shopTypeKey,@Param("sortType") String sortType,@Param("type")String type, @Param("code")String code);

    List<RestShop> juliList(@Param("code")String code,@Param("lat")String lat, @Param("lng")String lng,@Param("name")String name, @Param("shopTypeKey")String shopTypeKey, @Param("type")String type, @Param("start")int i, @Param("limit")int pageSize);
}
