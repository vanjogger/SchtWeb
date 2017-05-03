package com.scht.admin.service;

import com.scht.admin.entity.QuestRecord;
import com.scht.front.bean.RetResult;

/**
 * Created by Administrator on 2017/3/1.
 */

public interface QuestRecordService {



    /**
     * rest  今日回答问题数量
     * @param memberId
     * @return
     */
    int countForToday(String memberId);

    RetResult save(String memberId, String questId, String answerIds);

    //发送红包
    RetResult sendHb(String recordId, String ip,String rootPath);
}
