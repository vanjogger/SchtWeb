package com.scht.admin.controller;

import com.scht.admin.dao.AliPaySetDao;
import com.scht.admin.dao.WeixinPaySetDao;
import com.scht.admin.entity.AliPaySet;
import com.scht.admin.entity.WeixinPaySet;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.util.UUIDFactory;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/11/24.
 */
@Controller
@RequestMapping("/paySet/")
public class PaySettingController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("ali")
    public String aliPay(ModelMap map)
    {
        map.put("data", baseService.findById(AliPaySetDao.class,""));
        return "/setting/alipay";
    }

    @RequestMapping("saveAli")
    @ResponseBody
    public JSONObject saveAliPay(AliPaySet set, HttpServletRequest request){
        AliPaySet data = baseService.findById(AliPaySetDao.class,"");
        if(data != null){
            set.setId(data.getId());
            baseService.update(AliPaySetDao.class, set);
        }else{
            set.setId(UUIDFactory.random());
            baseService.insert(AliPaySetDao.class, set);
        }
        this.saveLog("设置支付宝支付", request);
        return this.FmtResult(true,"设置成功",null);
    }


    @RequestMapping("weixin")
    public String weixinPay(ModelMap map)
    {
        map.put("data", baseService.findById(WeixinPaySetDao.class, ""));
        return "/setting/weixin_pay";
    }
    @RequestMapping("saveWeixin")
    @ResponseBody
    public JSONObject saveWeixin(WeixinPaySet set, HttpServletRequest request){
        WeixinPaySet data = baseService.findById(WeixinPaySetDao.class,"");
        if(data != null){
            set.setId(data.getId());
            baseService.update(WeixinPaySetDao.class, set);
        }else{
            set.setId(UUIDFactory.random());
            baseService.insert(WeixinPaySetDao.class, set);
        }
        this.saveLog("设置微信支付设置", request);
        return this.FmtResult(true,"设置成功",null);
    }
}
