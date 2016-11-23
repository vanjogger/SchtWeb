package com.scht.admin.dao;

import com.scht.admin.entity.NoticeType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */
@Repository
public interface NoticeTypeDao {

    void insert(NoticeType type);

    NoticeType findById(String id);

    void update(NoticeType type);

    List<NoticeType> findAll();

    //按照编号排序，生成编号时使用
    List<NoticeType> listOrderByNo();

    void delete(String[] ids);
}
