package com.scht.admin.dao;

import com.scht.admin.entity.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/25.
 */
@Repository
public interface MemberDao {

    Member findByAccount(String account);


    Integer countMember(Map params);

    List<Member> listByIds(String[] ids);

    void bindOpenId(@Param("openId")String openId, @Param("id")String id);

    //删除
    void updateOpenId(String openId);
}
