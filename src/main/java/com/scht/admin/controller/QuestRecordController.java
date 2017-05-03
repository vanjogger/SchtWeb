package com.scht.admin.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.dao.QuestRecordDao;
import com.scht.admin.dao.QuestionDao;
import com.scht.admin.entity.Member;
import com.scht.admin.entity.QuestRecord;
import com.scht.admin.entity.Question;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.MemberService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/3.
 */
@Controller
@RequestMapping("/questRecord/")
public class QuestRecordController extends BaseController {

    @Autowired
    BaseService baseService;

    @Autowired
    MemberService memberService;
    //列表
    @RequestMapping("list")
    public String list(){
        return "/quest/record_list";
    }

    @RequestMapping("listData1")
    @ResponseBody
    public JSONObject listData(PageInfo pageInfo, String questTitle, String memberAccount){
        Map<String,Object> map = new HashMap<>();
        if(!StringUtil.isNullOrEmpty(questTitle)){
            map.put("questTitle", questTitle);
        }
        if(!StringUtil.isNullOrEmpty(memberAccount)){
            map.put("memberAccount", memberAccount);
        }
        pageInfo.setParams(map);
        pageInfo = this.page(QuestRecordDao.class, pageInfo);
        initResult(pageInfo.getResult());
        return this.getPageResult(pageInfo);
    }

    private void initResult(List<QuestRecord> list){
        if(list == null || list.size() == 0) return;
        List<String> memberIds = new ArrayList<>();
        for(QuestRecord record : list){
            memberIds.add(record.getMemberId());
            record.setQuestion(JSON.parseObject(record.getQuestJson(), Question.class));
        }
        List<Member> memberList = memberService.listByIds(memberIds.toArray(new String[0]));
        Map<String,Member> map = new HashMap<>();
        for(Member member : memberList) {
            map.put(member.getId(), member);
        }
        for(QuestRecord record : list) {
            Member member = map.get(record.getMemberId());
            if(member != null) {
                record.setMemberAccount(member.getAccount());
            }
        }
    }
}
