package com.scht.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/1.
 */
@Repository
public interface QuestRecordDao {


    //今天参与数量
    int countForToday(@Param("memberId")String memberId, @Param("time")long today);

    long checkMemberForQuest(@Param("memberId")String memberId, @Param("questId")String questId);
}
