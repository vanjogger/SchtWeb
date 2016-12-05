package com.scht.admin.dao;

import com.scht.admin.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
