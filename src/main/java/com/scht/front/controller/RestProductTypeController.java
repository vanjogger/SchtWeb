package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.bean.Status;
import com.scht.admin.dao.ProductDao;
import com.scht.admin.dao.ProductTypeDao;
import com.scht.admin.entity.Product;
import com.scht.admin.entity.ProductType;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.ProductTypeService;
import com.scht.common.BaseController;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wxh on 2017/4/4.
 */
@Controller
@RequestMapping("/rest/productType/")
public class RestProductTypeController extends BaseController {

    //查询商品分类
    @Autowired
    ProductTypeService productTypeService;
    @Autowired
    BaseService baseService;
    //查询分类列表
    @RequestMapping(value = "normalList", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object normalList(@RequestParam(value = "pageNo", defaultValue = "1")int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("start", (pageNo-1)*pageSize);
        map.put("limit", pageSize);
        map.put("status", Status.NORMAL.name());
        map.put("type","1");
        List list = this.baseService.searchByPage(ProductTypeDao.class,map);
        RetResult result = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(list);
        result.setData(data);
        return JSON.toJSON(result);
    }

    //查询分类列表
    @RequestMapping(value = "list", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object list(@RequestParam(value = "pageNo", defaultValue = "1")int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("start", (pageNo-1)*pageSize);
        map.put("limit", pageSize);
        map.put("status", Status.NORMAL.name());
        map.put("type","0");
       List list = this.baseService.searchByPage(ProductTypeDao.class,map);
        RetResult result = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(list);
        result.setData(data);
        return JSON.toJSON(result);
    }

    //外卖商品列表
    @RequestMapping(value = "wbList", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object wbList(String shopId){
        RetResult result = new RetResult(RetResult.RetCode.OK);
        Map<String,Object> map = new HashMap<>();
        map.put("start", 0);
        map.put("limit", Integer.MAX_VALUE);
        map.put("status", Status.NORMAL.name());
        map.put("type","0");
        List<ProductType> list = this.baseService.searchByPage(ProductTypeDao.class,map);
        if(list == null || list.size() == 0) return JSON.toJSON(result);

        //查询商品
        map.put("front","true");
        if(!StringUtil.isNullOrEmpty(shopId)) {
            map.put("shopId", shopId);
        }
        map.put("wb", "1");
        List<Product> productList = this.baseService.searchByPage(ProductDao.class,map);
        Map<String,List<Product>> tempMap = new HashMap<>();
        if(productList != null && productList.size() > 0) {
            List<Product> temp = null;
            for(Product product : productList) {
                if(tempMap.get(product.getTypeId()) != null) {
                    temp = tempMap.get(product.getTypeId());
                }else{
                    temp = new ArrayList<>();
                }
                temp.add(product);
                tempMap.put(product.getTypeId(), temp);
            }
        }
        for(ProductType type : list) {
            type.setProductList(tempMap.get(type.getId()));
        }
        RetData data = new RetData(list);
        result.setData(data);
        return JSON.toJSON(result);
    }
}
