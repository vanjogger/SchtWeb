package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.service.ShopService;
import com.scht.common.BaseFrontController;
import com.scht.front.bean.RetResult;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vanjoger on 2016/12/11.
 */
@Controller
@RequestMapping("/rest/shop")
public class RestShopControlller extends BaseFrontController{

    private static Logger logger = LoggerFactory.getLogger(RestShopControlller.class);

    @Autowired
    ShopService shopService;

    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object login(@RequestParam("account") String account, @RequestParam("password") String password){
        logger.info("shop login ： account= "+account+" & password:"+password);
        RetResult result = this.shopService.restLogin(account,password);
       return JSON.toJSON(result);
    }

    @RequestMapping(value = "/updateInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateInfo(@RequestParam("id") String id, @RequestParam("linkName") String linkName, @RequestParam("linkMobile") String linkMobile, @RequestParam("linkAddress") String linkAddress){
        logger.info("shop updateInfo ： id= "+id+" & linkName:"+linkName+" & linkMobile: "+linkMobile+" & linkAddress : "+linkAddress);
        RetResult result = this.shopService.updateInfo(id, linkName, linkMobile, linkAddress);
        return JSON.toJSON(result);
    }

    @RequestMapping(value = "/updatePwd", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updatePwd(@RequestParam("id") String id, @RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd){
        logger.info("shop updateInfo ： id= "+id+" & oldPwd:"+oldPwd+" & newPwd: "+newPwd);
        RetResult result = this.shopService.updatePwd(id, oldPwd, newPwd);
        return JSON.toJSON(result);
    }

    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object list(@RequestParam(value="shopTypeKey",required = false) String shopTypeKey,@RequestParam(value="sortType",required = false)String sortType,
                       @RequestParam(value="type",required = false)String type, @RequestParam(value="code",required = false)String code,@RequestParam("pageNo") int pageNo,
                       @RequestParam(value="pageSize",defaultValue = "10") int pageSize){

        RetResult result = this.shopService.list(shopTypeKey,sortType,type,code,pageNo,pageSize);
        return JSON.toJSON(result);
    }

}
