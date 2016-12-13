package com.scht.admin.dao;

import com.scht.admin.entity.Notice;
import com.scht.front.bean.RetResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
@Repository
public interface NoticeDao {

    void insert(Notice notice);

    Notice findById(String id);

    void update(Notice notice);

    List<Notice> searchByPage(Map<String,Object> map);
    long count4Page(Map<String,Object> map);

    void delete(String[] ids);
    //增加阅读量
    void addCount(@Param("id")String id);

    List<Notice> list(@Param("no")String no);
}
