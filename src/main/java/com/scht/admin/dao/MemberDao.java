package com.scht.admin.dao;

import com.scht.admin.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/25.
 */
@Repository
public interface MemberDao {

    Member findByAccount(String account);


    Integer countMember(Map params);
}
