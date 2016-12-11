package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.service.ProductService;
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
@RequestMapping("/rest/shopProduct")
public class RestShopProductController extends BaseFrontController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object list(@RequestParam("id") String id, @RequestParam("productName") String productName, @RequestParam("pageNo") int pageNo,
                       @RequestParam(value="pageSize",defaultValue = "10") int pageSize){
        RetResult result = this.productService.list(id,productName,pageNo,pageSize);
        return JSON.toJSON(result);
    }

}
