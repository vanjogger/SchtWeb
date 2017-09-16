package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.service.OrderService;
import com.scht.admin.service.ProductService;
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
@RequestMapping("/rest/shopOrder")
public class RestShopOrderController extends BaseFrontController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object list(@RequestParam("shopId") String shopId,   String status, @RequestParam(value = "pageNo",defaultValue = "1") int pageNo,
                       @RequestParam(value="pageSize",defaultValue = "10") int pageSize){
       RetResult result = this.orderService.list(shopId,status,pageNo,pageSize);
       return JSON.toJSON(result);
    }

}
