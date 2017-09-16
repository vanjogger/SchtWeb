package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.service.ShopBankService;
import com.scht.admin.service.ShopTypeService;
import com.scht.common.BaseFrontController;
import com.scht.front.bean.RetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wxh on 2016/12/11.
 */
@Controller
@RequestMapping("/rest/shopType")
public class RestShopTypeController extends BaseFrontController {

    @Autowired
    ShopTypeService shopTypeService;


    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object list(){
        RetResult result = this.shopTypeService.list();
        return JSON.toJSON(result);
    }


}
