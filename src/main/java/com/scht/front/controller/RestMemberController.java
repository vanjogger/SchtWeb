package com.scht.front.controller;

import ch.qos.logback.core.util.StringCollectionUtil;
import com.alibaba.fastjson.JSON;
import com.scht.admin.bean.Status;
import com.scht.admin.dao.MemberDao;
import com.scht.admin.dao.MemberMoneyDao;
import com.scht.admin.dao.ProductCollectionDao;
import com.scht.admin.dao.ShopCollectionDao;
import com.scht.admin.entity.Member;
import com.scht.admin.entity.MemberMoney;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.MemberService;
import com.scht.admin.service.MessageRecordService;
import com.scht.common.BaseController;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.MD5Util;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.CacheRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vanjoger on 2016/12/3.
 */
@Controller
@RequestMapping("/rest/member/")
public class RestMemberController  extends BaseController{

    @Autowired
    MemberService memberService;

    @Autowired
    BaseService baseService;
    @Autowired
    MessageRecordService messageRecordService;

    @RequestMapping(value = "bind", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object bind(String openId, String id){
        RetResult result = memberService.bindOpenId(id, openId);
        return JSON.toJSON(result);
    }

    @RequestMapping(value = "register", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object register(String mobile, String password, String msgCode){
        RetResult result = messageRecordService.validateCode(mobile, msgCode);
        if(!RetResult.RetCode.OK.equals(result.getResCode())) {
            return JSON.toJSON(result);
        }
        Member member = memberService.findByAccount(mobile);
        if(member != null) {
            result = new RetResult(RetResult.RetCode.User_Already_Reg);
            return JSON.toJSON(result);
        }
        member = new Member();
        member.setId(UUIDFactory.random());
        member.setAccount(mobile);
        member.setTelephone(mobile);
        member.setPassword(MD5Util.getMD5ofStr(password));
        member.setCreateTime(System.currentTimeMillis());
        member.setStatus(Status.NORMAL.name());
        if(StringUtil.isNullOrEmpty(member.getNick())) {
            member.setNick(StringUtil.randomNick());
        }
        this.baseService.insert(MemberDao.class, member);
        //会员资金
        MemberMoney money = new MemberMoney(member.getId());
        money.setId(UUIDFactory.random());
        this.baseService.insert(MemberMoneyDao.class, money);
        result = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(member);
        result.setData(data);
        return JSON.toJSON(result);
    }

    //重新拉去会员信息
    @RequestMapping(value = "find", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object find(String id){
        try {
            Member member = this.baseService.findById(MemberDao.class, id);
            Map<String,Object> map = new HashMap<>();
            map.put("memberId", member.getId());
           member.setShopCollects(this.baseService.count4Page(ShopCollectionDao.class, map));
            member.setProductColects(this.baseService.count4Page(ProductCollectionDao.class, map));
            member.setMoney(((MemberMoney)this.baseService.findById(MemberMoneyDao.class, member.getId())).getMoney());
            RetResult result = new RetResult(RetResult.RetCode.OK);
            RetData data = new RetData(member);
            result.setData(data);
            return JSON.toJSON(result);
        }catch (Exception e){
            return JSON.toJSON(new RetResult(RetResult.RetCode.System_Error));
        }
    }

    //修改个人资料
    @RequestMapping(value = "update", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object update(String nick,String headIcon,String address, String qq,String weixin,String id){
        RetResult result = null;
        try {
            result = this.memberService.restUpdate(nick, headIcon, address, qq, weixin, id);
        }catch (Exception e) {
            result = new RetResult(RetResult.RetCode.System_Error);
        }
        return JSON.toJSON(result);
    }

    //会员登录
    @RequestMapping(value = "login", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object login(String mobile, String password){
        Member member = memberService.findByAccount(mobile);
        RetResult result = null;
        if(member == null) {
            result = new RetResult(RetResult.RetCode.User_Not_Exist);
        }else if(!member.getPassword().equals(MD5Util.getMD5ofStr(password))) {
            result = new RetResult(RetResult.RetCode.User_UnCorrect_Pwd);
        }else if(Status.FROZEN.name().equals(member.getStatus())) {
            result = new RetResult(RetResult.RetCode.User_Frozen);
        }else{
            result = new RetResult(RetResult.RetCode.OK);
            RetData data = new RetData(member);
            result.setData(data);
        }
        return JSON.toJSON(result);
    }

    //修改登录密码
    @RequestMapping(value = "updatePassword", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object updatePassword(String id, String password, String oldPassword){
        RetResult result = null;
        try{
            result = memberService.restUpdatePassword(id, password, oldPassword);
        }catch (Exception e){
            result = new RetResult(RetResult.RetCode.System_Error);
        }
        return JSON.toJSON(result);
    }

    //充值登录密码，忘记密码
    @RequestMapping(value = "resetPassword", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object resetPassword(String mobile, String password, String msgCode){
        RetResult result = null;
        try {
            Member member = this.memberService.findByAccount(mobile);
            if(member == null) {
                result = new RetResult(RetResult.RetCode.User_Not_Exist);
            }else {
                result = messageRecordService.validateCode(mobile, msgCode);
                if (RetResult.RetCode.OK.equals(result.getResCode())) {
                    member.setPassword(MD5Util.getMD5ofStr(password));
                    this.baseService.update(MemberDao.class, member);
                }
            }
        } catch (Exception e){
            result = new RetResult(RetResult.RetCode.System_Error);
        }
        return JSON.toJSON(result);
    }


}
