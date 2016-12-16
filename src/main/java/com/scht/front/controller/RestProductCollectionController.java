package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.dao.*;
import com.scht.admin.entity.*;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/15.
 */
@Controller
@RequestMapping("/rest/productCollection/")
public class RestProductCollectionController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping(value = "list", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object list(String memberId, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Map<String,Object> params = new HashMap<>();
        params.put("start", (pageNo-1) * pageSize);
        params.put("limit", pageSize);
        RetResult result = null;
        if(StringUtil.isNullOrEmpty(memberId)) {
            result = new RetResult(RetResult.RetCode.Execute_Error);
            return JSON.toJSON(result);
        }
        params.put("memberId", memberId);
        List list = this.baseService.searchByPage(ProductCollectionDao.class,params);
        int count = this.baseService.count4Page(ProductCollectionDao.class, params);
        RetData data = new RetData(pageNo,pageSize, list, count);
        result = new RetResult(RetResult.RetCode.OK);
        result.setData(data);
        return JSON.toJSON(result);
    }


    //收藏
    @RequestMapping(value = "save", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object save(String memberId, String productId){
        RetResult result = null;
        Member member =  this.baseService.findById(MemberDao.class, memberId);
        if(member == null) {
            result = new RetResult(RetResult.RetCode.User_Not_Exist);
        }else {
            Product product = this.baseService.findById(ProductDao.class, productId);
            if(product == null) {
                result = new RetResult(RetResult.RetCode.Product_Not_Exist);
            }else {
                //查询是否已经收藏
                Map<String,Object> map = new HashMap<>();
                map.put("memberId", memberId);
                map.put("productId", productId);
                if(this.baseService.count4Page(ProductCollectionDao.class, map) > 0) {
                    result = new RetResult(RetResult.RetCode.Collection_Exist);
                }else {
                    ProductCollection collection = new ProductCollection();
                    collection.setId(UUIDFactory.random());
                    collection.setMemberId(memberId);
                    collection.setProductId(productId);
                    collection.setProductName(product.getTitle());
                    collection.setProductIcon(product.getIcon());
                    collection.setProductPrice(product.getPrice());
                    collection.setCreateTime(System.currentTimeMillis());
                    this.baseService.insert(ProductCollectionDao.class, collection);
                    result = new RetResult(RetResult.RetCode.OK);
                }
            }
        }
        return JSON.toJSON(result);
    }

    //删除收藏
    @RequestMapping(value = "delete", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object delete(String id){
        RetResult result = null;
        this.baseService.delete(ProductCollectionDao.class, new String[]{id});
        result = new RetResult(RetResult.RetCode.OK);
        return JSON.toJSON(result);
    }
}
