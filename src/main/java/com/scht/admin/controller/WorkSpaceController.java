package com.scht.admin.controller;

import com.scht.admin.bean.OrderStatus;
import com.scht.admin.bean.Status;
import com.scht.admin.entity.Admin;
import com.scht.admin.service.MemberService;
import com.scht.admin.service.OrderService;
import com.scht.admin.service.ShopService;
import com.scht.admin.service.ShopWithDrawalsService;
import com.scht.common.BaseController;
import com.scht.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vanjoger on 2016/11/24.
 */
@Controller
public class WorkSpaceController extends BaseController{

    @Autowired
    MemberService memberService;
    @Autowired
    ShopService shopService;
    @Autowired
    OrderService orderService;
    @Autowired
    ShopWithDrawalsService shopWithDrawalsService;



    @RequestMapping("/workspace")
    public String workSpace(Model model,HttpServletRequest request){
        String startDate = DateUtil.getCurrentDate()+" 00:00:00";
        String endDate = DateUtil.getCurrentDate()+" 23:59:59";

        Long startMills = DateUtil.getLongFormStrDate(startDate);
        Long endMills = DateUtil.getLongFormStrDate(endDate);

        Admin admin = (Admin) this.getCurrentUser(request);

        Map params = new HashMap();


        //---------------------  会员统计  总数  今日新增数  冻结数
        Integer totalMember = this.memberService.countMember(params);
        model.addAttribute("totalMember", totalMember);

        params.put("startDate", startMills);
        params.put("endDate", endMills);
        Integer newMember = this.memberService.countMember(params);
        model.addAttribute("newMember", newMember);

        params.clear();
        params.put("status", Status.FROZEN.name());
        Integer frozenMember = this.memberService.countMember(params);
        model.addAttribute("frozenMember", frozenMember);
        // --------- 商家数目  总数  冻结数
        params.clear();
        if(admin.getType().equals("1"))
            params.put("agentId",admin.getId());
        Integer totalShop = this.shopService.countShop(params);
        model.addAttribute("totalShop",totalShop);

        params.put("status", Status.FROZEN.name());
        Integer frozenShop = this.shopService.countShop(params);
        model.addAttribute("frozenShop", frozenShop);

        //-- 订单  订单总数   待支付订单  代发货订单
        params.clear();
        if(admin.getType().equals("1"))
            params.put("agentId",admin.getId());
        Integer totalOrder = this.orderService.countOrder(params);
        model.addAttribute("totalOrder",totalOrder);

        params.put("status", OrderStatus.CREATE.name());
        Integer createOrder = this.orderService.countOrder(params);
        model.addAttribute("createOrder", createOrder);

        params.put("status", OrderStatus.PAY.name());
        Integer payOrder = this.orderService.countOrder(params);
        model.addAttribute("payOrder",payOrder);
        // 商家提现
        params.clear();
        if(admin.getType().equals("1"))
            params.put("agentId",admin.getId());
        Integer totalTx = this.shopWithDrawalsService.countWithDrawals(params);
        model.addAttribute("totalTx", totalTx);

        params.put("status", "0");
        Integer dshTx = this.shopWithDrawalsService.countWithDrawals(params);
        model.addAttribute("dshTx",dshTx);

        return "workspace";
    }
}
