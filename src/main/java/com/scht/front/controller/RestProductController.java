package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.bean.Status;
import com.scht.admin.dao.ProductDao;
import com.scht.admin.entity.Product;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.ProductService;
import com.scht.common.BaseController;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/12/14.
 */
@Controller
@RequestMapping("/rest/product/")
public class RestProductController  extends BaseController{

    @Autowired
    BaseService baseService;
    @Autowired
    ProductService productService;

    @RequestMapping(value = "regionList", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object regionList(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "region",required = false) String region,
            @RequestParam(value = "tc", required = false) String tc,
            @RequestParam(value = "wb", required = false) String wb,
            @RequestParam(value = "order", required = false)String order,
            @RequestParam(value = "type", required = false)String type,
            String lat,String lng
    ){
        Map<String,Object> map = new HashMap<>();
        map.put("front","true");
        map.put("status", Status.NORMAL.name());
        map.put("start", (pageNo-1)*pageSize);
        map.put("limit", pageSize);
        if(!StringUtil.isNullOrEmpty(lat)) {
            map.put("lat", lat);
        }
        if(!StringUtil.isNullOrEmpty(lng)) {
            map.put("lng", lng);
        }
        if(!StringUtil.isNullOrEmpty(region)) {
            map.put("region", region);
        }
        if(!StringUtil.isNullOrEmpty(tc)) {
            map.put("tc", tc);
        }
        if(!StringUtil.isNullOrEmpty(wb)) {
            map.put("wb", wb);
        }
        if(!StringUtil.isNullOrEmpty(type)){
            map.put("type", type);
        }
        if(!StringUtil.isNullOrEmpty(order)) {
            map.put("order", order);
        }
        List list = productService.regionList(map);
        int count = this.baseService.count4Page(ProductDao.class, map);
        RetResult result = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(pageNo,pageSize,list, count);
        result.setData(data);
        return JSON.toJSON(result);

    }

    @RequestMapping(value = "list", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object list(@RequestParam(value = "shopId", required = false)String shopId,
                       @RequestParam(value = "title", required = false) String title,
                       @RequestParam(value = "order", required = false)String order,
                       @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       @RequestParam(value = "typeId", required = false)String typeId,
                       @RequestParam(value = "region",required = false) String region,
                       @RequestParam(value = "tc", required = false) String tc,
                       @RequestParam(value = "wb", required = false) String wb,
                       @RequestParam(value = "type", required = false)String type){
        Map<String,Object> map = new HashMap<>();
        map.put("front","true");
        map.put("status", Status.NORMAL.name());
        map.put("start", (pageNo-1)*pageSize);
        map.put("limit", pageSize);
        if(!StringUtil.isNullOrEmpty(typeId)) {
            map.put("typeId", typeId);
        }
        if(!StringUtil.isNullOrEmpty(region)) {
            map.put("region", region);
        }
        if(!StringUtil.isNullOrEmpty(tc)) {
            map.put("tc", tc);
        }
        if(!StringUtil.isNullOrEmpty(wb)) {
            map.put("wb", wb);
        }
        if(!StringUtil.isNullOrEmpty(type)){
            map.put("type", type);
        }
        if(!StringUtil.isNullOrEmpty(shopId)) {
            map.put("shopId", shopId);
        }
        if(!StringUtil.isNullOrEmpty(title)) {
            map.put("title", title);
        }
        if(!StringUtil.isNullOrEmpty(order)) {
            map.put("order", order);
        }
        List list = this.baseService.searchByPage(ProductDao.class, map);
        int count = this.baseService.count4Page(ProductDao.class, map);
        RetResult result = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(pageNo,pageSize,list, count);
        result.setData(data);
        return JSON.toJSON(result);
    }

    @RequestMapping(value = "find", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object find(String id, HttpServletRequest request){
        Product product = this.baseService.findById(ProductDao.class, id);
        if(!StringUtil.isNullOrEmpty(product.getContent())) { //内容里面的图片路径改为绝对路径
            Pattern pattern = Pattern.compile("<img[^>]*src=\"([^\"]*)\"[^>]*[^>]*>");
            Matcher matcher = pattern.matcher(product.getContent());
            String url = "http://" + request.getServerName();
            while (matcher.find()) {
                String temp = matcher.group(1);
                if (!temp.substring(0, 7).equals("http://")) {
                    product.setContent(product.getContent().replaceAll(temp, url + temp));
                }
            }
        }
        RetResult result = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(product);
        result.setData(data);
        return JSON.toJSON(result);
    }

    @RequestMapping(value = "view")
    public Object view(String id, ModelMap map, HttpServletRequest request){
        Product product = this.baseService.findById(ProductDao.class, id);
       map.put("data", product);
       return "/pages/product";
    }
}
