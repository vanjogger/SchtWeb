package com.scht.admin.service.impl;

import com.scht.admin.dao.MemberDao;
import com.scht.admin.entity.Member;
import com.scht.admin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/25.
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDao memberDao;

    @Override
    public Member findByAccount(String account) {
        return memberDao.findByAccount(account);
    }
}
