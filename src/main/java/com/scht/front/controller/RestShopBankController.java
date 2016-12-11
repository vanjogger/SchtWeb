package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.service.ShopBankService;
import com.scht.common.BaseFrontController;
import com.scht.front.bean.RetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by vanjoger on 2016/12/11.
 */
@Controller
@RequestMapping("/rest/shopBank")
public class RestShopBankController extends BaseFrontController {

    @Autowired
    ShopBankService shopBankService;


    @RequestMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object add(@RequestParam("shopId") String shopId,@RequestParam("yhmc") String yhmc,
                      @RequestParam("khh") String khh,@RequestParam("yhmc") String ckr,@RequestParam("kh") String kh){
        RetResult result = this.shopBankService.save(shopId,yhmc,khh,ckr,kh);
        return JSON.toJSON(result);
    }


    @RequestMapping(value = "/edit", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object edit(@RequestParam("id") String id,@RequestParam("yhmc") String yhmc,
                      @RequestParam("khh") String khh,@RequestParam("yhmc") String ckr,@RequestParam("kh") String kh){
        RetResult result = this.shopBankService.edit(id, yhmc, khh, ckr, kh);
        return JSON.toJSON(result);
    }


    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object list(@RequestParam("id") String id,@RequestParam("pageNo") int pageNo,
                       @RequestParam(value="pageSize",defaultValue = "10") int pageSize){
        RetResult result = this.shopBankService.list(id, pageNo, pageSize);
        return JSON.toJSON(result);
    }


    @RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object delete(@RequestParam("id") String id){
        RetResult result = this.shopBankService.delete(id);
        return JSON.toJSON(result);
    }
}
