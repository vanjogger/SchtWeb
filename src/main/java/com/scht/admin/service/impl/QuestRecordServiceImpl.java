package com.scht.admin.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.scht.admin.bean.MemberFlowType;
import com.scht.admin.bean.WeixinUser;
import com.scht.admin.dao.*;
import com.scht.admin.entity.*;
import com.scht.admin.service.QuestRecordService;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.*;
import com.scht.util.PayUtil.WeixinHbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

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

        //查询正确答案
        List<QuestAnswer> sucList = questAnswerDao.listByQuest(questId);
        List<String> sucIds = new ArrayList<>();
        List<String> errIds = new ArrayList<>();
        String sucStr = "", myStr = "";
        String successIds = "";
        for(QuestAnswer answer : sucList) {
            if(answer.isAnswer()) {
                sucStr += answer.getContent()+";";
                sucIds.add(answer.getId());
                successIds += answer.getId()+",";
            }else {
                errIds.add(answer.getId());
            }
            if(answerIds.contains(answer.getId())) {
                myStr += answer.getContent() + ";";
            }
        }
        String[] answer = answerIds.split(",");
        String money = "0";
        //保存记录，回答正确，错误都保存记录
        QuestRecord record = new QuestRecord();
        if(answer.length == sucIds.size() && sucStr.equals(myStr)) {
             //回答正确
            //判断问题
            if(question.getCount() < question.getSumCount()) {
                //会员资金增加
//                money = question.getMoney();
//                saveMemberMoney(question, member);
                record.setNeedPush(true); //需要奖励
                if(!StringUtil.isNullOrEmpty(question.getCouponId())) {
                    //奖励优惠券
                    record.setCouponId(question.getCouponId());
                    //发放优惠券
                    String couponRecordId = sendCouponRecord(question.getCouponId(),memberId, member.getAccount());
                    if(couponRecordId == null) {
                        result = new RetResult(RetResult.RetCode.Illegal_Request);
                        result.setResMsg("优惠券已经发放完毕了，请联系管理员");
                        return result;
                    }
                    record.setCouponRecordId(couponRecordId);
                    record.setPushMoney(true); //已经发放
                }else{
                    record.setMoney(question.getMoney());
                    record.setPushMoney(false); //未发放
                }

            }else{
                //问题奖励发放完毕
                record.setNeedPush(false); //没有奖励了
            }
            //回答正确，问题 +1
            questionDao.updateCount(questId);
            result = new RetResult(RetResult.RetCode.OK);
        }else{
            //回答错误
            record.setNeedPush(false); //回答错误，不奖励
            result = new RetResult(RetResult.RetCode.Quest_Answer_Error);
        }

        record.setId(UUIDFactory.random());
        record.setMemberId(memberId);
        record.setQuestId(questId);
        record.setQuestTitle(question.getTitle());
        record.setSuc(RetResult.RetCode.OK.equals(result.getResCode()));
        record.setCreateTime(System.currentTimeMillis());
        record.setSucAnswer(sucStr);
        record.setAnswer(myStr);
        record.setSucIds(successIds);
        record.setMyIds(answerIds);
        record.setQuestJson(JSON.toJSONString(question));
        record.setMoney(money);
        this.baseMyBatisDao.insert(QuestRecordDao.class, record);

        Shop shop = this.baseMyBatisDao.findById(ShopDao.class, question.getShopId());
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("shop", shop);
        record.setQuestion(question);
        dataMap.put("record", record);
        RetData data = new RetData(dataMap);
        result.setData(data);
        return result;
    }

    @Override
    public synchronized RetResult sendHb(String recordId, String ip,String rootPath) {
        RetResult result = null;
        result = new RetResult(RetResult.RetCode.Illegal_Request);
        QuestRecord record = this.baseMyBatisDao.findById(QuestRecordDao.class, recordId);
        if(record == null) {
            result.setResMsg("问题记录不存在");
            return result;
        }
        if(!record.isSuc() || !record.isNeedPush()) {
            result.setResMsg("问题回答错误或不需要发送红包");
            return result;
        }
        //回答正确并且未奖励
        if(record.isSuc() && record.isNeedPush() && !record.isPushMoney()) {
            //todo:调用发送红包接口
            WeixinPaySet setting = this.baseMyBatisDao.findById(WeixinPaySetDao.class,"");
            if(setting == null || StringUtil.isNullOrEmpty(setting.getCerPath())) {
                result.setResMsg("微信红包未设置");
                return result;
            }
            Member member = this.baseMyBatisDao.findById(MemberDao.class, record.getMemberId());
            if(member == null || StringUtil.isNullOrEmpty(member.getOpenId())) {
                result.setResMsg("您未绑定微信账号");
                return result;
            }
            WeixinUser user = this.baseMyBatisDao.findById(WeixinUserDao.class, member.getOpenId());
            if(user == null) {
                //重新拉去openId
                user = findUser(setting, member.getOpenId());
            }else{
                //判断是否关注
                try {
                    WeixinUser temp = WeixinUtil.getUser(setting,user.getOpenId());
                    if(temp == null) {
                        result.setResMsg("您未关注公众号");
                        return result;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    result.setResMsg("微信端错误");
                    return result;
                }
            }
            if(user == null) {
                result.setResMsg("您未关注公众号");
                return result;
            }
            String no = OrderUtil.createNo();
            boolean flag = WeixinHbUtil.sendHb(setting, no,user.getOpenId(),StringNumber.mul(record.getMoney(),"100"),ip,rootPath);
            if(flag) {
                record.setHbNo(no);
                record.setPushMoney(true);
                questRecordDao.successHb(no, record.getId());
                result = new RetResult(RetResult.RetCode.OK);
                return  result;
            }

        }
        result.setResMsg("红包发放失败");
        return result;
    }

    private String sendCouponRecord(String couponId, String memberId, String memberAccount){
        Coupon coupon = this.baseMyBatisDao.findById(CouponDao.class, couponId);
        if(coupon == null || coupon.getCount() <= coupon.getPushCount()) {
            return null;
        }
        CouponRecord couponRecord = new CouponRecord();
        couponRecord.setId(UUIDFactory.random());
        couponRecord.setCouponId(couponId);
        couponRecord.setCouponName(coupon.getName());
        couponRecord.setCouponMoney(coupon.getCouponMoney());
        couponRecord.setMemberId(memberId);
        couponRecord.setMemberAccount(memberAccount);
        couponRecord.setCreateTime(System.currentTimeMillis());
        couponRecord.setStatus(false);
        this.baseMyBatisDao.insert(CouponRecordDao.class, couponRecord);
        coupon.setPushCount(coupon.getPushCount() + 1);
        this.baseMyBatisDao.update(CouponDao.class, coupon);
        return couponRecord.getId();
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

    private WeixinUser findUser(WeixinPaySet set,String unionId){
        List<WeixinUser> list = this.baseMyBatisDao.findAll(WeixinUserDao.class);
        Map<String,String> map = new HashMap<>();
        WeixinUser temp = null;
        if(list != null && list.size() > 0) {
            for(WeixinUser user : list) {
                map.put(user.getOpenId(), user.getOpenId());
            }
        }
        try {
            List<String> openIdList = WeixinUtil.getUserList(set);
            List<WeixinUser> saveList = new ArrayList<>();
            if(openIdList != null && openIdList.size() > 0) {
                for(String id : openIdList) {
                    if(map.get(id) == null) {//新用户
                        WeixinUser user = WeixinUtil.getUser(set,id);
                        if(user != null) {
                            user.setId(UUIDFactory.random());
                            saveList.add(user);
                            if(user.getUnionId().equals(unionId)){
                                temp = user;
                            }
                        }
                    }
                }
                if(saveList.size() > 0) {
                    int len = saveList.size();
                    int end =10;
                    int size = len%end > 0 ? len/end+1 : len/end;
                    for(int i=0; i<size;i++){
                        if(i != size-1) {
                            this.baseMyBatisDao.saveBatch(WeixinUserDao.class, saveList.subList(i * end, (i + 1) * end));
                        }else{
                            this.baseMyBatisDao.saveBatch(WeixinUserDao.class, saveList.subList(i * end, len));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
