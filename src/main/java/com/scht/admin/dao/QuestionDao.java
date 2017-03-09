package com.scht.admin.dao;

import com.scht.admin.entity.Question;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/1.
 */
@Repository
public interface QuestionDao {

    Question findForMember(String memberId);

    void updateCount(String id);
}
