package com.scht.admin.dao;

import com.scht.admin.entity.ProductType;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/11/27.
 */
@Repository
public interface ProductTypeDao {

    ProductType findByKey(String key);
}
