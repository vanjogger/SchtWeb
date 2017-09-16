package com.scht.admin.service.impl;

import com.scht.admin.dao.BaseMyBatisDao;
import com.scht.admin.dao.QuestAnswerDao;
import com.scht.admin.dao.QuestionDao;
import com.scht.admin.dao.ShopDao;
import com.scht.admin.entity.QuestAnswer;
import com.scht.admin.entity.Question;
import com.scht.admin.entity.Shop;
import com.scht.admin.service.QuestionService;
import com.scht.common.ServiceException;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionDao questionDao;
    @Autowired
    BaseMyBatisDao baseMyBatisDao;
    @Autowired
    QuestAnswerDao questAnswerDao;
    @Override
    public void save(Question question) {
        question.setId(UUIDFactory.random());
        question.setCreateTime(System.currentTimeMillis());
        question.setCount(0);
        if(question.getList()!= null && question.getList().size() > 0) {
           for(QuestAnswer answer : question.getList()) {
               answer.setQuestId(question.getId());
               answer.setId(UUIDFactory.random());
           }
            baseMyBatisDao.saveBatch(QuestAnswerDao.class, question.getList());
        }
        baseMyBatisDao.insert(QuestionDao.class, question);
    }

    @Override
    public void update(Question question) {
        questAnswerDao.deleteByQuest(new String[]{question.getId()});
        if(question.getList()!= null && question.getList().size() > 0) {
            for(QuestAnswer answer : question.getList()) {
                answer.setQuestId(question.getId());
                answer.setId(UUIDFactory.random());
            }
            baseMyBatisDao.saveBatch(QuestAnswerDao.class, question.getList());
        }
        baseMyBatisDao.update(QuestionDao.class, question);
    }

    @Override
    public void delete(String id) {
        String[] ids = new String[]{id};
        this.questAnswerDao.deleteByQuest(ids);
        this.baseMyBatisDao.delete(QuestionDao.class, ids);
    }

    @Override
    public List<QuestAnswer> listAnswer(String questId) {
        return questAnswerDao.listByQuest(questId);
    }

    /**
     * rest
     */
    @Override
    public Question findForMember(String memberId, String region) {
       Question data =  questionDao.findForMember(memberId, region);
        if(data != null) {
            Shop shop = this.baseMyBatisDao.findById(ShopDao.class, data.getShopId());
            if(shop != null) {
                data.setShopName(shop.getName());
            }
            data.setList(questAnswerDao.listByQuest(data.getId()));
        }
        return data;
    }
}
