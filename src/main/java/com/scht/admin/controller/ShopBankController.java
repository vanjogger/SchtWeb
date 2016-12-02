package com.scht.admin.controller;

import com.scht.admin.bean.AmountType;
import com.scht.admin.dao.ShopBankDao;
import com.scht.admin.dao.ShopFlowDao;
import com.scht.admin.dao.ShopMoneyDao;
import com.scht.admin.entity.ShopBank;
import com.scht.admin.entity.ShopFlow;
import com.scht.admin.entity.ShopMoney;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import net.sf.json.JSONObject;
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
@RequestMapping("/shopBank")
public class ShopBankController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("/list")
    public String list(){
        return "shop/bank/shop_bank_list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String shopName,String shopAccount,String status,PageInfo page){
        Map<String,Object> params = new HashMap<>();
        if(StringUtil.isNotNull(shopName))
            params.put("shopName",shopName);
        params.put("shopAccount",shopAccount);

        page.setParams(params);
        this.page(ShopBankDao.class,page);

        return this.getPageResult(page);
    }



    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){
        if(StringUtil.isNotNull(id)){
            ShopBank bank = this.baseService.findById(ShopBankDao.class,id);
            model.addAttribute("dto",bank);
        }
        return "shop/bank/shop_bank_edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(ShopBank bank,HttpServletRequest request){
        try {
            this.baseService.update(ShopBankDao.class, bank);
            //保存日志
            this.saveLog("修改商家银行卡信息：" + bank.getShopName(), request);
            return this.FmtResult(true, "修改成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"修改失败",null);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONObject delete(String id){
        try {
            this.baseService.delete(ShopBankDao.class,new String[]{id});
            return this.FmtResult(true,"删除成功",null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"删除失败",null);
    }
}
