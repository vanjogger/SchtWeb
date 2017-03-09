package com.scht.admin.service;

import com.scht.admin.entity.QuestAnswer;
import com.scht.admin.entity.Question;

import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
public interface QuestionService {

    void save(Question question);

    void update(Question question);

    void delete(String id);

    List<QuestAnswer> listAnswer(String questId);

    Question findForMember(String memberId);
}
