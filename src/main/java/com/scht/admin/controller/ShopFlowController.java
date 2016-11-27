package com.scht.admin.controller;

import com.scht.admin.dao.ShopFlowDao;
import com.scht.admin.dao.ShopMoneyDao;
import com.scht.admin.entity.ShopFlow;
import com.scht.admin.entity.ShopMoney;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vanjoger on 2016/11/27.
 */
@Controller
@RequestMapping("/shopFlow")
public class ShopFlowController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("/list")
    public String list(){
        return "shop/flow/shop_flow_list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String shopName,String shopAccount,String startDate,String endDate,PageInfo page){
        Map<String,Object> params = new HashMap<>();

        params.put("shopName",shopName);
        params.put("shopAccount",shopAccount);
        if(StringUtil.isNotNull(startDate))
            params.put("startDate", DateUtil.getLongFormStrDate(startDate));
        if(StringUtil.isNotNull(endDate))
            params.put("endDate",DateUtil.getLongFormStrDate(endDate));
        page.setParams(params);
        this.page(ShopFlowDao.class,page);

        return this.getPageResult(page);
    }



    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){
        if(StringUtil.isNotNull(id)){
            ShopFlow flow = this.baseService.findById(ShopFlowDao.class,id);
            model.addAttribute("dto",flow);
        }
        return "shop/flow/shop_flow_edit";
    }



}
