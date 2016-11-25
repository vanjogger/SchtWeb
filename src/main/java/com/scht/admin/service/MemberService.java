package com.scht.admin.service;

import com.scht.admin.entity.Member;

/**
 * Created by Administrator on 2016/11/25.
 */
public interface MemberService {

    Member findByAccount(String account);
}
