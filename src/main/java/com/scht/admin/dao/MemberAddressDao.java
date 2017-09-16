package com.scht.admin.dao;

import com.scht.admin.entity.MemberAddress;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wxh on 2016/12/2.
 */
@Repository
public interface MemberAddressDao {


    List<MemberAddress> listByMemberId(String memberId);

    MemberAddress findDefault(String memberId);

}
