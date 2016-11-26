package com.scht.admin.controller;

import com.scht.admin.dao.AgentMoneyDao;
import com.scht.admin.entity.Admin;
import com.scht.admin.entity.AgentMoney;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.MD5Util;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vanjoger on 2016/11/26.
 */
@Controller
@RequestMapping("/agentMoney")
public class AgentMoneyController extends BaseController{

    @Autowired
    BaseService baseService;

    @RequestMapping("/list")
    public String list(){
        return "agent/agent_money_list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String agentName,String status,PageInfo page){
        Map<String,Object> params = new HashMap<>();
        if(StringUtil.isNotNull(agentName))
            params.put("agentName",agentName);

        page.setParams(params);
        this.page(AgentMoneyDao.class,page);

        return this.getPageResult(page);
    }



    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){
        if(StringUtil.isNotNull(id)){
            AgentMoney money = this.baseService.findById(AgentMoneyDao.class,id);
            model.addAttribute("dto",money);
        }
        return "agent/agent_money_edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(AgentMoney money,HttpServletRequest request){
        try {
            this.baseService.update(AgentMoneyDao.class, money);
            this.saveLog("调整代理商金额"+money.getAgentName(),request);
            return this.FmtResult(true, "调整成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"调整失败",null);
    }

}
