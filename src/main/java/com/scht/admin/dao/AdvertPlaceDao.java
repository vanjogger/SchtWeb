package com.scht.admin.dao;

import com.scht.admin.entity.AdvertPlace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
@Repository
public interface AdvertPlaceDao {

    void save(AdvertPlace data);

    AdvertPlace find(String id );

    void update(AdvertPlace data);

    void delete(String id);

    List<AdvertPlace> list(@Param("code")String code,@Param("index") int index, @Param("size")int size);

    int count(@Param("code")String code);

    AdvertPlace findByCode(@Param("code")String code);

    List<AdvertPlace> findAll();
}
