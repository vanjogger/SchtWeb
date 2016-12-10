package com.scht.admin.dao;

import com.scht.admin.entity.Nation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vanjoger on 2016/12/10.
 */
@Repository
public interface NationDao {
    List<Nation> listByParentId(@Param("lx")String lx,@Param("id")String id);
}
