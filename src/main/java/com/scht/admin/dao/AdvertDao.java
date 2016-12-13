package com.scht.admin.dao;

import com.scht.admin.entity.Advert;
import com.scht.front.bean.RestAdvert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/22.
 */
@Repository
public interface AdvertDao {

    List<Advert> searchByPage(Map<String,Object> map );
    int count4Page(Map<String,Object> map);

    Advert findById(String id);

    void insert(Advert advert);

    void update(Advert advert);

    void delete(String[] ids);

    List<RestAdvert> list(Map map);
}
