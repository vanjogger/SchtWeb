package com.scht.admin.service;

import com.scht.admin.entity.Member;
import com.scht.admin.entity.MemberMoney;
import com.scht.front.bean.RetResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/25.
 */
public interface MemberService {

    Member findByAccount(String account);

    Integer countMember(Map params);
    List<Member> listByIds(String[] strings);

    List<MemberMoney> listMoney(String[] strings);


 //*************     APP 接口          ******************//
    RetResult restUpdate(String nick, String headIcon, String address, String qq, String weixin, String id);

    RetResult restUpdatePassword(String id, String password, String oldPassword);

}
