package com.scht.admin.entity;

/**
 * 答案
 * Created by Administrator on 2017/3/1.
 */
public class QuestAnswer {

    private String id;
    private String questId; //问题ID
    private String content;//答案
    private boolean answer; //是否正确答案， true 正确， false 错误
    private String sort; //序号，如： ABCD
    private String icon; //

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
