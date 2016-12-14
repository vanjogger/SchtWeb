package com.scht.admin.controller;

import com.scht.admin.dao.AgentFlowDao;
import com.scht.admin.dao.ShopFlowDao;
import com.scht.admin.entity.ShopFlow;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.DateUtil;
import com.scht.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vanjoger on 2016/11/27.
 */
@Controller
@RequestMapping("/agentFlow")
public class AgentFlowController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("/list")
    public String list(){
        return "agent/agent_flow_list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String agentName,String agentAccount,String startDate,String endDate,PageInfo page){
        Map<String,Object> params = new HashMap<>();

        params.put("agentName",agentName);
        params.put("agentAccount",agentAccount);
        if(StringUtil.isNotNull(startDate))
            params.put("startDate", DateUtil.getLongFormStrDate(startDate));
        if(StringUtil.isNotNull(endDate))
            params.put("endDate",DateUtil.getLongFormStrDate(endDate));
        page.setParams(params);
        this.page(AgentFlowDao.class,page);

        return this.getPageResult(page);
    }



}
