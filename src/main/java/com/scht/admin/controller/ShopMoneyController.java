package com.scht.admin.controller;

import com.scht.admin.bean.AmountType;
import com.scht.admin.dao.ShopFlowDao;
import com.scht.admin.dao.ShopMoneyDao;
import com.scht.admin.entity.ShopFlow;
import com.scht.admin.entity.ShopMoney;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vanjoger on 2016/11/27.
 */
@Controller
@RequestMapping("/shopMoney")
public class ShopMoneyController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("/list")
    public String list(){
        return "shop/money/shop_money_list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String shopName,String shopAccount,String status,PageInfo page){
        Map<String,Object> params = new HashMap<>();
        if(StringUtil.isNotNull(shopName))
            params.put("shopName",shopName);
        params.put("shopAccount",shopAccount);

        page.setParams(params);
        this.page(ShopMoneyDao.class,page);

        return this.getPageResult(page);
    }



    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){
        if(StringUtil.isNotNull(id)){
            ShopMoney money = this.baseService.findById(ShopMoneyDao.class,id);
            model.addAttribute("dto",money);
        }
        return "shop/money/shop_money_edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(ShopMoney money,HttpServletRequest request){
        try {
            this.baseService.update(ShopMoneyDao.class, money);
            this.saveLog("调整商家金额"+money.getShopName(),request);
            //保存日志
            ShopFlow flow = new ShopFlow();
            flow.setId(UUIDFactory.random());
            flow.setShopId(money.getShopId());
            flow.setType(AmountType.HandFee.name());
            flow.setAmount(money.getAvailAmount());
            flow.setAfterAmount(money.getAvailAmount());
            flow.setCreateTime(new Date().getTime());
            this.baseService.insert(ShopFlowDao.class,flow);

            return this.FmtResult(true, "调整成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"调整失败",null);
    }

}
