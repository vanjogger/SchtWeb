package com.scht.admin.controller;

import com.scht.admin.bean.Status;
import com.scht.admin.dao.MemberDao;
import com.scht.admin.entity.Member;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.MemberService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.MD5Util;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/25.
 */
@Controller
@RequestMapping("/admin/member/")
public class MemberController extends BaseController {

    @Autowired
    BaseService baseService;

    @Autowired
    MemberService memberService;

    @RequestMapping("list")
    public String list(){
        return "/member/list";
    }

    @RequestMapping("listData")
    @ResponseBody
    public JSONObject listData(String account, String nick, String telephone, String status, PageInfo pageInfo){
        Map<String,Object> map = new HashMap<>();
        if(!StringUtil.isNullOrEmpty(account)){
            map.put("account", account);
        }
        if(!StringUtil.isNullOrEmpty(nick)) {
            map.put("nick", nick);
        }
        if(!StringUtil.isNullOrEmpty(telephone)){
            map.put("telephone", telephone);
        }
        if(!StringUtil.isNullOrEmpty(status)) {
            map.put("status", status);
        }
        pageInfo.setParams(map);
        pageInfo = this.page(MemberDao.class, pageInfo);
        return this.getPageResult(pageInfo);
    }

    @RequestMapping("add")
    public String add(){
        return "/member/add";
    }

    @RequestMapping("save")
    @ResponseBody
    public JSONObject save(Member member, HttpServletRequest request){
        if(memberService.findByAccount(member.getAccount()) != null ){
            return this.FmtResult(false,"该账号已存在",null);
        }
        member.setPassword(MD5Util.getMD5ofStr(member.getPassword()));
        member.setId(UUIDFactory.random());
        member.setCreateTime(System.currentTimeMillis());
        baseService.insert(MemberDao.class, member);
        this.saveLog("添加会员账号：" + member.getAccount(), request);
        return this.FmtResult(true,"添加成功",null);
    }

    @RequestMapping("find")
    public String find(String id, ModelMap map){
        map.put("data", this.baseService.findById(MemberDao.class, id));
        return "/member/edit";
    }

    @RequestMapping("update")
    @ResponseBody
    public JSONObject update(Member member, HttpServletRequest request){
        if(!StringUtil.isNullOrEmpty(member.getPassword())) {
            member.setPassword(MD5Util.getMD5ofStr(member.getPassword()));
        }
        this.baseService.update(MemberDao.class, member);
        this.saveLog("编辑会员账号：" + member.getAccount(), request);
        return this.FmtResult(true,"编辑成功",null);
    }

    @RequestMapping("updateStatus")
    @ResponseBody
    public JSONObject updateStatus(String id,String status, HttpServletRequest request){
        Member member = this.baseService.findById(MemberDao.class, id);
        if(member == null) {
            return this.FmtResult(false,"数据错误",null);
        }
        member.setStatus(status);
        this.baseService.update(MemberDao.class, member);
        this.saveLog((Status.NORMAL.name().equals(status)?"开启":"冻结")+"会员账号："+member.getAccount(),request);
        return this.FmtResult(true,"操作成功",null);
    }
}
