package com.scht.admin.dao;

import com.scht.admin.entity.ShopType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wxh on 2016/11/26.
 */
@Repository
public interface ShopTypeDao {
    List<ShopType> listBySort(@Param("sort")int sort);

    List<ShopType> listMap(Map map);

    List<ShopType> listByKey(@Param("key")String key);

}
