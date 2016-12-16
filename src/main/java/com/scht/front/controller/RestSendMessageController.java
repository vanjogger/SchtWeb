package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.entity.Member;
import com.scht.admin.service.MemberService;
import com.scht.admin.service.MessageRecordService;
import com.scht.common.BaseController;
import com.scht.front.bean.RetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/12/14.
 */
@Controller
@RequestMapping("/rest/sendMsg/")
public class RestSendMessageController extends BaseController {

    @Autowired
    MemberService memberService;

    @Autowired
    MessageRecordService messageRecordService;

    @RequestMapping(value = "register",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object registerMsg(String mobile){
        //验证手机号是否注册了，
        RetResult result = null;
        Member member = memberService.findByAccount(mobile);
        if(member != null){
            result = new RetResult(RetResult.RetCode.User_Already_Reg);
            return JSON.toJSON(result);
        }
        result = messageRecordService.sendSms(mobile,"1");
//        result = new RetResult(RetResult.RetCode.OK);
        return JSON.toJSON(result);
    }

    @RequestMapping(value = "findPwd", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object findPassword(String mobile){
        RetResult result = null;
        Member member = memberService.findByAccount(mobile);
        if(member == null){
            result = new RetResult(RetResult.RetCode.User_Not_Exist);
            return JSON.toJSON(result);
        }
        result = messageRecordService.sendSms(mobile,"2");
        return JSON.toJSON(result);
    }

}
