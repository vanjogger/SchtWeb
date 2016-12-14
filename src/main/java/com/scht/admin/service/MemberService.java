package com.scht.admin.service;

import com.scht.admin.entity.Member;
import com.scht.front.bean.RetResult;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/25.
 */
public interface MemberService {

    Member findByAccount(String account);

    Integer countMember(Map params);
 //*************     APP 接口          ******************//
    RetResult restUpdate(String nick, String headIcon, String address, String qq, String weixin, String id);

    RetResult restUpdatePassword(String id, String password, String oldPassword);
}
