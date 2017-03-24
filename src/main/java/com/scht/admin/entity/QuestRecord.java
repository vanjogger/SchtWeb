package com.scht.admin.entity;

import com.scht.util.DateUtil;

import java.util.Date;

/**
 * 会员答题记录
 * Created by Administrator on 2017/3/1.
 */
public class QuestRecord {

    private String id;
    private String memberId; //会员ID
    private String questId; //问题ID
    private String questTitle; //问题标题
    private String money; //奖励金额
    private boolean suc; //是否回答正确， true 正确 false 错误
    private long createTime; //答题时间
    private String sucAnswer; //正确答案
    private String answer; //我的答案
    private String sucIds; //正确答案 ids，多个用，分割
    private String myIds; //我的答案ids，多个用，分割
    private String questJson; //问题元数据 json格式

    //会员账号
    private String memberAccount;
    private String dateStr;
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getSucIds() {
        return sucIds;
    }

    public void setSucIds(String sucIds) {
        this.sucIds = sucIds;
    }

    public String getMyIds() {
        return myIds;
    }

    public void setMyIds(String myIds) {
        this.myIds = myIds;
    }

    public String getQuestJson() {
        return questJson;
    }

    public void setQuestJson(String questJson) {
        this.questJson = questJson;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getQuestTitle() {
        return questTitle;
    }

    public void setQuestTitle(String questTitle) {
        this.questTitle = questTitle;
    }

    public boolean isSuc() {
        return suc;
    }

    public void setSuc(boolean suc) {
        this.suc = suc;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        if(createTime > 0) {
            this.dateStr = DateUtil.getFormatDate(new Date(createTime), DateUtil.pattern_16);
        }
        this.createTime = createTime;
    }

    public String getSucAnswer() {
        return sucAnswer;
    }

    public void setSucAnswer(String sucAnswer) {
        this.sucAnswer = sucAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
