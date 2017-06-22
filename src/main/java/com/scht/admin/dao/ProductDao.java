package com.scht.admin.dao;

import com.scht.admin.entity.Product;
import com.scht.front.bean.RestProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/27.
 */
@Repository
public interface ProductDao {
    //更改销量
    void updateSaleCount(@Param("id")String id,@Param("count")int count);

    //更改库存
    void updateStock(@Param("id")String id,@Param("count")int count);

    //更改评论数
    void updateCommentCount(@Param("id")String id,@Param("count")int count);


    List<Product> listByIds(String[] ids);

    List<RestProduct> list(@Param("shopId")String id, @Param("title")String productName, @Param("start")int start, @Param("size")int size);

    Integer count(@Param("shopId")String id, @Param("title")String productName);

    List<Product> searchSaleCountGroupByShopId(String[] shopIds);

    List<Product> searchExtendProductByShopIds(String[] shopIds);

    List<Product> regionList(Map<String, Object> map);
}
