package com.scht.admin.controller;

import com.scht.admin.dao.ShopWithDrawalsDao;
import com.scht.admin.entity.ShopFlow;
import com.scht.admin.entity.ShopMoney;
import com.scht.admin.entity.ShopWithDrawals;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.ShopMoneyService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.DateUtil;
import com.scht.util.StringUtil;
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
 * Created by vanjoger on 2016/11/27.
 */
@Controller
@RequestMapping("/shopTx")
public class ShopWithDrawalsController extends BaseController {

    @Autowired
    BaseService baseService;
    @Autowired
    ShopMoneyService shopMoneyService;

    @RequestMapping("/list")
    public String list(){
        return "shop/withdrawals/shop_withdrawals_list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String shopName,String shopAccount,String startDate,String endDate,String status,PageInfo page){
        Map<String,Object> params = new HashMap<>();
        params.put("status",status);
        params.put("shopName",shopName);
        params.put("shopAccount",shopAccount);
        if(StringUtil.isNotNull(startDate))
            params.put("startDate", DateUtil.getLongFormStrDate(startDate));
        if(StringUtil.isNotNull(endDate))
            params.put("endDate",DateUtil.getLongFormStrDate(endDate));
        page.setParams(params);
        this.page(ShopWithDrawalsDao.class,page);

        return this.getPageResult(page);
    }


    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){
        if(StringUtil.isNotNull(id)){
            ShopWithDrawals dto = this.baseService.findById(ShopWithDrawalsDao.class,id);
            model.addAttribute("dto",dto);
            //查询商家资金
            if(dto!=null) {
                List<ShopMoney> moneys = this.shopMoneyService.listByShopId(dto.getShopId());
                String availAmount = "0";
                if(StringUtil.isNotEmpty(moneys)) {
                    ShopMoney money = moneys.get(0);
                    availAmount =  money.getAvailAmount();
                }
                model.addAttribute("availAmount", availAmount);
            }

        }
        return "shop/withdrawals/shop_withdrawals_edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public JSONObject save(ShopWithDrawals dto,HttpServletRequest request){
        try{
            if(StringUtil.isNotNull(dto.getId())){
                this.baseService.update(ShopWithDrawalsDao.class,dto);
                if("1".equals(dto.getStatus())) {
                    this.saveLog("通过商家提现:"+dto.getId(),request);
                }else if("2".equals(dto.getStatus())){
                    this.saveLog("拒绝商家提现:"+dto.getId(),request);
                }
                return this.FmtResult(true,"保存成功",null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"保存失败",null);
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public JSONObject updateStatus(String id,String status,HttpServletRequest request){
        try{
            ShopWithDrawals dto = this.baseService.findById(ShopWithDrawalsDao.class,id);
            if(dto!=null){
                dto.setStatus(status);
                this.baseService.update(ShopWithDrawalsDao.class, dto);
                if("1".equals(status)) {
                    this.saveLog("通过商家提现:"+id,request);
                }else if("2".equals(status)){
                    this.saveLog("拒绝商家提现:"+id,request);
                }
                return this.FmtResult(true,"操作成功",null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"操作失败",null);
    }

}
