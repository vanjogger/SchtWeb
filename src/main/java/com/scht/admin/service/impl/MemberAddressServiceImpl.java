package com.scht.admin.service.impl;

import com.scht.admin.dao.BaseMyBatisDao;
import com.scht.admin.dao.MemberAddressDao;
import com.scht.admin.dao.MemberDao;
import com.scht.admin.entity.Member;
import com.scht.admin.entity.MemberAddress;
import com.scht.admin.service.MemberAddressService;
import com.scht.front.bean.RetResult;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
@Service
public class MemberAddressServiceImpl implements MemberAddressService {
    @Autowired
    MemberAddressDao memberAddressDao;
    @Autowired
    BaseMyBatisDao baseMyBatisDao;
    @Override
    public List<MemberAddress> listByMemberId(String memberId) {
        return memberAddressDao.listByMemberId(memberId);
    }

    @Override
    public MemberAddress findDefault(String memberId) {
        return memberAddressDao.findDefault(memberId);
    }

    @Override
    public RetResult RestSave(String memberId, String name, String mobile, String address, String details, String isDefault) {

        RetResult result = null;
        Member member  = this.baseMyBatisDao.findById(MemberDao.class, memberId);
        if(member == null) {
            result = new RetResult(RetResult.RetCode.User_Not_Exist);
            return result;
        }
        if("1".equals(isDefault)) {
            MemberAddress memberAddress = findDefault(memberId);
            if(memberAddress != null) {
                memberAddress.setIsDefault("0");
                this.baseMyBatisDao.update(MemberAddressDao.class, memberAddress);
            }
        }
        MemberAddress data = new MemberAddress();
        data.setId(UUIDFactory.random());
        data.setMemberId(memberId);
        data.setName(name);
        data.setMobile(mobile);
        data.setAddress(address);
        data.setDetails(details);
        data.setIsDefault(isDefault);
        this.baseMyBatisDao.insert(MemberAddressDao.class, data);
        result = new RetResult(RetResult.RetCode.OK);
        return result;
    }

    @Override
    public RetResult restUpdate(String id, String memberId, String name, String mobile, String address, String details, String isDefault) {
        RetResult result = null;
        MemberAddress data = this.baseMyBatisDao.findById(MemberAddressDao.class, id);
        if(data == null) {
            result = new RetResult(RetResult.RetCode.User_Address_Not_Exist);
            return result;
        }
        Member member  = this.baseMyBatisDao.findById(MemberDao.class, memberId);
        if(member == null) {
            result = new RetResult(RetResult.RetCode.User_Not_Exist);
            return result;
        }
        if("1".equals(isDefault)) {
            MemberAddress memberAddress = findDefault(memberId);
            if(memberAddress != null && !memberAddress.getId().equals(id)) {
                memberAddress.setIsDefault("0");
                this.baseMyBatisDao.update(MemberAddressDao.class, memberAddress);
            }
        }
        data.setName(name);
        data.setMobile(mobile);
        data.setAddress(address);
        data.setDetails(details);
        data.setIsDefault(isDefault);
        this.baseMyBatisDao.update(MemberAddressDao.class, data);
        result = new RetResult(RetResult.RetCode.OK);
        return result;
    }
}
