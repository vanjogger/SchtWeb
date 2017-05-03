package com.scht.front.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.scht.admin.dao.QuestRecordDao;
import com.scht.admin.dao.QuestSetDao;
import com.scht.admin.entity.QuestRecord;
import com.scht.admin.entity.QuestSet;
import com.scht.admin.entity.Question;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.QuestRecordService;
import com.scht.admin.service.QuestionService;
import com.scht.common.BaseController;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/2.
 */
@Controller
@RequestMapping("/rest/quest/")
public class RestQuestionController extends BaseController {

    @Autowired
    QuestionService questionService;
    @Autowired
    BaseService baseService;
    @Autowired
    QuestRecordService questRecordService;

    //查询问题记录
    @RequestMapping(value = "list", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                           @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                           String memberId){

        RetResult result = null;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("start", (pageNo - 1) * pageSize);
            params.put("limit", pageSize);
            params.put("memberId", memberId);
            List list = this.baseService.searchByPage(QuestRecordDao.class, params);
            int total = this.baseService.count4Page(QuestRecordDao.class, params);
            RetData data = new RetData(pageNo, pageSize, list,total);
            result = new RetResult(RetResult.RetCode.OK);
            result.setData(data);
        }catch (Exception e){
            result = new RetResult(RetResult.RetCode.System_Error);
        }
        return JSON.toJSON(result);

    }
    //查询回答详情
    @RequestMapping(value = "find", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object find(String id , HttpServletRequest request){
        QuestRecord record = this.baseService.findById(QuestRecordDao.class, id);
        if(StringUtil.isNotNull(record.getQuestJson())) {

            Question question =  JSON.parseObject(record.getQuestJson(), Question.class);
            if(!StringUtil.isNullOrEmpty(question.getContent())) { //内容里面的图片路径改为绝对路径
                Pattern pattern = Pattern.compile("<img[^>]*src=\"([^\"]*)\"[^>]*[^>]*>");
                Matcher matcher = pattern.matcher(question.getContent());
                String url = "http://" + request.getServerName();
                while (matcher.find()) {
                    String temp = matcher.group(1);
                    if (!temp.substring(0, 7).equals("http://")) {
                        question.setContent(question.getContent().replaceAll(temp, url + temp));
                    }
                }
            }
            record.setQuestion(question);
        }
        RetResult result = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(record);
        result.setData(data);
        return JSON.toJSON(result);
    }

    //拉取下一个问题
    @RequestMapping(value = "quest", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object quest(String memberId, HttpServletRequest request){
        //判断今天回答问题数量
        QuestSet set = this.baseService.findById(QuestSetDao.class, "");
        if(set != null && set.getDayCount() > 0) {
            int todayCount = questRecordService.countForToday(memberId);
            if(todayCount >= set.getDayCount()) {
                RetResult result = new RetResult(RetResult.RetCode.Quest_Record_Than);
                return JSON.toJSON(result);
            }
        }
        Question question = questionService.findForMember(memberId);
        if(question == null) {
            RetResult result = new RetResult(RetResult.RetCode.Quest_Not_Have);
            return JSON.toJSON(result);
        }else{
            if(!StringUtil.isNullOrEmpty(question.getContent())) { //内容里面的图片路径改为绝对路径
                Pattern pattern = Pattern.compile("<img[^>]*src=\"([^\"]*)\"[^>]*[^>]*>");
                Matcher matcher = pattern.matcher(question.getContent());
                String url = "http://" + request.getServerName();
                while (matcher.find()) {
                    String temp = matcher.group(1);
                    if (!temp.substring(0, 7).equals("http://")) {
                        question.setContent(question.getContent().replaceAll(temp, url + temp));
                    }
                }
            }
        }
        RetResult result = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(question);
        result.setData(data);
        return JSON.toJSON(result);
    }
    //回答问题

    @RequestMapping(value = "save", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object save(String memberId, String questId, String answerIds){
        RetResult result = null;
        //判断是否回答了该问题
        if(StringUtil.isNullOrEmpty(memberId) || StringUtil.isNullOrEmpty(questId)) {
            result = new RetResult(RetResult.RetCode.Illegal_Request);
            result.setResMsg("请求参数错误");
            return JSON.toJSON(result);
        }
        if(StringUtil.isNullOrEmpty(answerIds)) {
            result = new RetResult(RetResult.RetCode.Illegal_Request);
            result.setResMsg("请选择答案");
            return JSON.toJSON(result);
        }
          result = questRecordService.save(memberId, questId, answerIds);
        return JSON.toJSON(result);
    }

    //回答正确，发送红包
    @RequestMapping(value = "sendHb",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object sendHb(String recordId, HttpServletRequest request) {
        RetResult result = questRecordService.sendHb(recordId, getRequestIp(request),request.getServletContext().getRealPath("/"));
        return JSON.toJSON(result);
    }
}
