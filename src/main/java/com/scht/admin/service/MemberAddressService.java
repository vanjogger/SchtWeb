package com.scht.admin.service;

import com.scht.admin.entity.MemberAddress;
import com.scht.front.bean.RetResult;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
public interface MemberAddressService  {

    List<MemberAddress> listByMemberId(String memberId);

    MemberAddress findDefault(String memberId);

    RetResult RestSave( String memberId,String name,String mobile,String address,String details, String isDefault);
    RetResult restUpdate(String id,String memberId,String name,String mobile,String address,String details, String isDefault);
}
