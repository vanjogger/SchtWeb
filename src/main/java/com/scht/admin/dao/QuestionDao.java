package com.scht.admin.dao;

import com.scht.admin.entity.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/1.
 */
@Repository
public interface QuestionDao {

    Question findForMember(@Param("memberId")String memberId, @Param("region")String region);

    void updateCount(String id);
}
