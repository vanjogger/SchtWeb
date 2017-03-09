package com.scht.admin.service.impl;

import com.scht.admin.dao.BaseMyBatisDao;
import com.scht.admin.dao.MemberDao;
import com.scht.admin.dao.MemberMoneyDao;
import com.scht.admin.entity.Member;
import com.scht.admin.entity.MemberMoney;
import com.scht.admin.service.MemberService;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.MD5Util;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/25.
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDao memberDao;
    @Autowired
    BaseMyBatisDao baseMyBatisDao;
    @Autowired
    MemberMoneyDao memberMoneyDao;

    @Override
    public Member findByAccount(String account) {
        return memberDao.findByAccount(account);
    }

    @Override
    public Integer countMember(Map params) {
        return this.memberDao.countMember(params);
    }

    @Override
    public List<Member> listByIds(String[] strings) {
        return memberDao.listByIds(strings);
    }

    @Override
    public List<MemberMoney> listMoney(String[] ids) {
        List<MemberMoney> list = memberMoneyDao.listByMemberIds(ids);
        Map<String,MemberMoney> map = new HashMap<>();
        if(list != null && list.size() > 0) {
            for(MemberMoney money : list) {
                map.put(money.getMemberId(), money);
            }
        }
        List<MemberMoney> reList = new ArrayList<>();
        for(String id : ids) {
            if(map.get(id) != null) {
                reList.add(map.get(id));
            }else{
                MemberMoney money = new MemberMoney(id);
                money.setId(UUIDFactory.random());
                this.baseMyBatisDao.insert(MemberMoneyDao.class, money);
                reList.add(money);
            }
        }
        return reList;
    }

    @Override
    public RetResult restUpdate(String nick, String headIcon, String address, String qq, String weixin, String id) {
        RetResult result = null;
        Member member = this.baseMyBatisDao.findById(MemberDao.class,id);
        if(member == null) {
            result = new RetResult(RetResult.RetCode.User_Not_Exist);
        }else{
            member.setNick(nick);
            member.setHeadIcon(headIcon);
            member.setAddress(address);
            member.setQq(qq);
            member.setWeixin(weixin);
            this.baseMyBatisDao.update(MemberDao.class, member);
            result = new RetResult(RetResult.RetCode.OK);
            RetData data = new RetData(member);
            result.setData(data);
        }
        return result;
    }

    @Override
    public RetResult restUpdatePassword(String id, String password, String oldPassword) {
        RetResult result = null;
        Member member = this.baseMyBatisDao.findById(MemberDao.class,id);
        if(member == null) {
            result = new RetResult(RetResult.RetCode.User_Not_Exist);
        }else if(!member.getPassword().equals(MD5Util.getMD5ofStr(oldPassword))){
            result = new RetResult(RetResult.RetCode.User_Old_Pwd_Error);
        }else{
            member.setPassword(MD5Util.getMD5ofStr(password));
            this.baseMyBatisDao.update(MemberDao.class, member);
            result = new RetResult(RetResult.RetCode.OK);
        }
        return result;
    }
}
