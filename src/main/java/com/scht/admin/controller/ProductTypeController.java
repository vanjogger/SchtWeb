package com.scht.admin.controller;

import com.scht.admin.dao.ProductDao;
import com.scht.admin.dao.ProductTypeDao;
import com.scht.admin.entity.ProductType;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.ProductTypeService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/27.
 */
@Controller
@RequestMapping("/productType/")
public class ProductTypeController extends BaseController {

    @Autowired
    BaseService baseService;

    @Autowired
    ProductTypeService productTypeService;

    @RequestMapping("list")
    public String list(String type, ModelMap map)
    {
        map.put("type", type);
        return "/product/type";
    }

    @RequestMapping("listData")
    @ResponseBody
    public JSONObject listData(PageInfo pageInfo, String name,String type, String status){
        Map<String,Object> map = new HashMap<>();
        if(!StringUtil.isNullOrEmpty(name)){
            map.put("name", name);
        }
        if(!StringUtil.isNullOrEmpty(type)) {
            map.put("type", type);
        }
        if(!StringUtil.isNullOrEmpty(status)){
            map.put("status", status);
        }
        pageInfo.setParams(map);
        pageInfo = this.page(ProductTypeDao.class, pageInfo);
        return this.getPageResult(pageInfo);
    }

    @RequestMapping("add")
    public String add(String type, ModelMap map){
        map.put("type", type);
        return "/product/type_add";
    }

    @RequestMapping("save")
    @ResponseBody
    public JSONObject save(ProductType type, HttpServletRequest request){
        if(this.productTypeService.findByKey(type.getKey(), type.getType()) != null){
            return this.FmtResult(false,"KEY值已被使用",null);
        }
        type.setId(UUIDFactory.random());
        this.baseService.insert(ProductTypeDao.class, type);
        this.saveLog("添加商品分类：" + type.getName(), request);
        return this.FmtResult(true,"添加成功", null);
    }

    @RequestMapping("find")
    public String find(String id, ModelMap map){
        map.put("data", this.baseService.findById(ProductTypeDao.class, id));
        return "/product/type_edit";
    }


    @RequestMapping("update")
    @ResponseBody
    public JSONObject update(ProductType type, HttpServletRequest request){
        ProductType data = this.productTypeService.findByKey(type.getKey(), type.getType());
        if(data != null && !data.getId().equals(type.getId())){
            return this.FmtResult(false,"KEY值已被使用",null);
        }
        this.baseService.update(ProductTypeDao.class, type);
        this.saveLog("修改商品分类：" + type.getName(), request);
        return this.FmtResult(true,"修改成功", null);
    }

    @RequestMapping("delete")
    @ResponseBody
    public JSONObject delete(String id, HttpServletRequest request){
        ProductType type = this.baseService.findById(ProductTypeDao.class, id);
        Map<String,Object> map = new HashMap<>();
        map.put("typeId", id);
        if(this.baseService.count4Page(ProductDao.class, map) > 0){
            return this.FmtResult(false,"分类下有商品，不可删除",null);
        }
        this.baseService.delete(ProductTypeDao.class, new String[]{id});
        this.saveLog("删除商品分类："+type.getName(),request);
        return this.FmtResult(true,"删除成功",null);
    }
}
