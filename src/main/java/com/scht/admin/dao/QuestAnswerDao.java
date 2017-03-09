package com.scht.admin.dao;

import com.scht.admin.entity.QuestAnswer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
@Repository
public interface QuestAnswerDao {
    //查询正确的
    List<QuestAnswer> listForSuc(String questId);

    List<QuestAnswer> listByQuest(String id);

    //根据问题ID，删除
    void deleteByQuest(String[] ids);
}
