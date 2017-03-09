package com.scht.admin.controller;

import com.scht.admin.dao.MemberFlowDao;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.DateUtil;
import com.scht.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/3.
 */
@Controller
@RequestMapping("/memberFlow/")
public class MemberFlowController extends BaseController {
    @Autowired
    BaseService baseService;

    @RequestMapping("/list")
    public String list(){
        return "member/member_flow";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String memberAccount,String startDate,String endDate,PageInfo page){
        Map<String,Object> params = new HashMap<>();
        if(StringUtil.isNotNull(memberAccount)) {
            params.put("memberAccount", memberAccount);
        }
        if(StringUtil.isNotNull(startDate))
            params.put("startDate", DateUtil.getLongFormStrDate(startDate));
        if(StringUtil.isNotNull(endDate))
            params.put("endDate",DateUtil.getLongFormStrDate(endDate));
        page.setParams(params);
        this.page(MemberFlowDao.class,page);
        return this.getPageResult(page);
    }
}
