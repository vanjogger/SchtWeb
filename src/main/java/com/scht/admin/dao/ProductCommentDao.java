package com.scht.admin.dao;

import com.scht.admin.entity.ProductComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vanjoger on 2016/12/10.
 */
@Repository
public interface ProductCommentDao {
    List<ProductComment> list(@Param("productId")String productId, @Param("start")int start, @Param("size")int size);

    Integer count(@Param("productId")String productId);

    List<ProductComment> queryByMemberIdandProductId(@Param("memberId")String memberId, @Param("productId")String productId);
}
