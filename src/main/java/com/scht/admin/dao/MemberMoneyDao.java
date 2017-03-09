package com.scht.admin.dao;

import com.scht.admin.entity.MemberMoney;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/3.
 */
@Repository
public interface MemberMoneyDao {

    List<MemberMoney> listByMemberIds(String[] ids);
}
