package com.scht.admin.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by vanjoger on 2016/11/27.
 */
@Repository
public interface ShopWithDrawalsDao {
    Integer countWithDrawals(Map params);
}
