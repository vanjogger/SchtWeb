package com.scht.admin.service.impl;

import com.scht.admin.bean.MemberFlowType;
import com.scht.admin.dao.*;
import com.scht.admin.entity.*;
import com.scht.admin.service.QuestRecordService;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.DateUtil;
import com.scht.util.StringNumber;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
@Service
public class QuestRecordServiceImpl implements QuestRecordService {

    @Autowired
    BaseMyBatisDao baseMyBatisDao;

    @Autowired
    QuestRecordDao questRecordDao;
    @Autowired
    QuestAnswerDao questAnswerDao;
    @Autowired
    QuestionDao questionDao;

    @Override
    public RetResult save(String memberId, String questId, String answerIds) {
        RetResult result = null;
        Member member= this.baseMyBatisDao.findById(MemberDao.class, memberId);
        if(member == null) {
            result = new RetResult(RetResult.RetCode.User_Not_Exist);
            return result;
        }
        Question question = this.baseMyBatisDao.findById(QuestionDao.class, questId);
        if(question == null) {
            result = new RetResult(RetResult.RetCode.Quest_Not_Exist);
            return result;
        }
        //判断是否回答了该问题
        if(questRecordDao.checkMemberForQuest(memberId, questId) > 0 ){
            result = new RetResult(RetResult.RetCode.Quest_Has_Answer);
            return result;
        }
        RetData data = new RetData(this.baseMyBatisDao.findById(ShopDao.class, question.getShopId()));

        //查询正确答案
        List<QuestAnswer> sucList = questAnswerDao.listByQuest(questId);
        List<String> sucIds = new ArrayList<>();
        List<String> errIds = new ArrayList<>();
        String sucStr = "", myStr = "";
        for(QuestAnswer answer : sucList) {
            if(answer.isAnswer()) {
                sucStr += answer.getContent()+";";
                sucIds.add(answer.getId());
            }else {
                errIds.add(answer.getId());
            }
            if(answerIds.contains(answer.getId())) {
                myStr += answer.getContent() + ";";
            }
        }
        String[] answer = answerIds.split(",");
        String money = "0";
        if(answer.length == sucIds.size() && sucStr.equals(myStr)) {
             //回答正确
            //判断问题
            if(question.getCount() < question.getSumCount()) {
                //会员资金增加
                money = question.getMoney();
                saveMemberMoney(question, member);
            }
            //回答正确，问题 +1
            questionDao.updateCount(questId);
            result = new RetResult(RetResult.RetCode.OK);
        }else{
            result = new RetResult(RetResult.RetCode.Quest_Answer_Error);
        }
        result.setData(data);
        //保存记录，回答正确，错误都保存记录
        QuestRecord record = new QuestRecord();
        record.setId(UUIDFactory.random());
        record.setMemberId(memberId);
        record.setQuestId(questId);
        record.setQuestTitle(question.getTitle());
        record.setSuc(RetResult.RetCode.OK.equals(result.getResCode()));
        record.setCreateTime(System.currentTimeMillis());
        record.setSucAnswer(sucStr);
        record.setAnswer(myStr);
        record.setMoney(money);
        this.baseMyBatisDao.insert(QuestRecordDao.class, record);
        return result;
    }

    @Override
    public int countForToday(String memberId) {
        long today = DateUtil.getCurrentDayStart();
        return questRecordDao.countForToday(memberId, today);
    }

    private void saveMemberMoney(Question question, Member member){
        MemberMoney memberMoney = this.baseMyBatisDao.findById(MemberMoneyDao.class, member.getId());
        if(memberMoney == null) {
            memberMoney = new MemberMoney(member.getId());
            memberMoney.setId(UUIDFactory.random());
        }
        String beforeMoney = memberMoney.getMoney();
        memberMoney.setMoney(StringNumber.add(memberMoney.getMoney(), question.getMoney()));
        memberMoney.setTotalMoney(StringNumber.add(memberMoney.getTotalMoney(), question.getMoney()));

        //资金流水
        MemberFlow flow = new MemberFlow();
        flow.setId(UUIDFactory.random());
        flow.setMemberId(member.getId());
        flow.setMemberAccount(member.getAccount());
        flow.setType(MemberFlowType.QUESTION.name());
       flow.setBeforeAmount(beforeMoney);
        flow.setAmount(question.getMoney());
        flow.setAfterAmount(memberMoney.getMoney());
        flow.setCreateTime(System.currentTimeMillis());
        this.baseMyBatisDao.insert(MemberFlowDao.class, flow);
        this.baseMyBatisDao.update(MemberMoneyDao.class, memberMoney);
    }
}
