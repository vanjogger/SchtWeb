package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.service.AdvertService;
import com.scht.admin.service.NoticeService;
import com.scht.admin.service.OrderService;
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
@RequestMapping("/rest/info")
public class RestInfoController extends BaseFrontController {

    @Autowired
    AdvertService advertService;
    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = "/queryAdvert", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object list(@RequestParam("code") String code, @RequestParam(value = "region", required = false)String region){
       RetResult result = this.advertService.list(code, region);
       return JSON.toJSON(result);
    }

    @RequestMapping(value = "/queryNotice", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object queryNotice( String no){
        RetResult result = this.noticeService.list(no);
        return JSON.toJSON(result);
    }

    @RequestMapping(value = "/noticeDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object noticeDetail( String id){
        RetResult result = this.noticeService.detail(id);
        return JSON.toJSON(result);
    }
}
