package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.service.AdvertService;
import com.scht.admin.service.NoticeService;
import com.scht.admin.service.ProductCommentService;
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
@RequestMapping("/rest/productComment/")
public class RestProductCommentController extends BaseFrontController {

    @Autowired
    ProductCommentService productCommentService;

    @RequestMapping(value = "list",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object ddss( String shopId,  String productId ,
                        @RequestParam(value = "pageNo",defaultValue = "1")int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                        String memberId){
//        return JSON.toJSON(new RetResult(RetResult.RetCode.OK));
       RetResult result = this.productCommentService.list(shopId,memberId,productId,pageNo,pageSize);
       return JSON.toJSON(result);
    }

//    @RequestMapping(value = "list", produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public Object list(@RequestParam("shopId")String shopId,@RequestParam("productId") String productId,
//                       @RequestParam(value = "pageNo",defaultValue = "1")int pageNo,
//                       @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
//                       @RequestParam("memberId")String memberId){
//       RetResult result = this.productCommentService.list(shopId,memberId,productId,pageNo,pageSize);
//       return JSON.toJSON(result);
//    }

    @RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object save(String orderId,String productId,
                     String grade, String images, String memberId,String content){
        RetResult result = this.productCommentService.save(orderId, productId, grade, images, content, memberId);
        return JSON.toJSON(result);
    }

    @RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object delete(String id){
        RetResult result = this.productCommentService.delete(id);
        return JSON.toJSON(result);
    }
}
