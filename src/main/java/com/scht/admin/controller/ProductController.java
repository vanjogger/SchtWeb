package com.scht.admin.controller;

import com.scht.admin.bean.Status;
import com.scht.admin.dao.ProductDao;
import com.scht.admin.dao.ProductTypeDao;
import com.scht.admin.dao.ShopDao;
import com.scht.admin.entity.Admin;
import com.scht.admin.entity.Product;
import com.scht.admin.entity.ProductType;
import com.scht.admin.entity.Shop;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.ShopService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/27.
 */
@Controller
@RequestMapping("/product/")
public class ProductController extends BaseController {

    @Autowired
    BaseService baseService;

    @Autowired
    ShopService shopService;
    @RequestMapping("wbList")
    public String wbList(String type, ModelMap map){
        map.put("type", type);
        map.put("typeList", this.baseService.findAll(ProductTypeDao.class));
        return "/product/wb_list";
    }

    @RequestMapping("list")
    public String list(String type, ModelMap map){
        map.put("type", type);
//        map.put("typeList", this.baseService.findAll(ProductTypeDao.class));
        return "/product/list";
    }

    @RequestMapping("listData")
    @ResponseBody
    public JSONObject listData(HttpServletRequest request, PageInfo pageInfo, String type, String title, String status,
                               String wb){
        Map<String,Object> map = new HashMap<>();
        Admin admin = (Admin) getCurrentUser(request);
        if("1".equals(admin.getType())){
            map.put("agentId", admin.getId());
        }
        if(!StringUtil.isNullOrEmpty(wb)) {
            map.put("wb", wb);
        }
        if(!StringUtil.isNullOrEmpty(type)){
            map.put("type", type);
        }
        if(!StringUtil.isNullOrEmpty(title)) {
            map.put("title", title);
        }
        if(!StringUtil.isNullOrEmpty(status)) {
            map.put("status", status);
        }
        pageInfo.setParams(map);
        pageInfo = this.page(ProductDao.class, pageInfo);
        formatDate(pageInfo.getResult());
        return this.getPageResult(pageInfo);
    }

    @RequestMapping("add")
    public String add(ModelMap map, String type){
        map.put("type", type);
        map.put("typeList", this.baseService.findAll(ProductTypeDao.class));
        return "/product/add";
    }

    @RequestMapping("wbadd")
    public String wbadd(ModelMap map, String type){
        map.put("type", type);
        map.put("typeList", this.baseService.findAll(ProductTypeDao.class));
        return "/product/wbadd";
    }
    @RequestMapping("save")
    @ResponseBody
    public JSONObject save(Product product, HttpServletRequest request){
        try {
            Admin admin = (Admin) getCurrentUser(request);
            if ("1".equals(admin.getType())) {
                product.setAgentId(admin.getId());
            }
            if ("1".equals(product.getSelf()) && StringUtil.isNullOrEmpty(product.getShopId())) {
                return this.FmtResult(false, "请选择商品所属商家", null);
            }
            if ("0".equals(product.getSelf())) {
                product.setShopId(null);
            }
            product.setId(UUIDFactory.random());
            product.setCreateTime(System.currentTimeMillis());
            this.baseService.insert(ProductDao.class, product);
            this.saveLog("添加商品：" + product.getTitle(), request);
            return this.FmtResult(true, "添加成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false, "添加失败", null);
    }

    @RequestMapping("find")
    public String find(ModelMap map, String id){
        Product data = this.baseService.findById(ProductDao.class, id);
        if(!StringUtil.isNullOrEmpty(data.getShopId())){
            Shop shop = this.baseService.findById(ShopDao.class, data.getShopId());
            data.setShopName(shop.getName());
        }
        map.put("data", data);
        if(data.isWb()) {
            map.put("typeList", this.baseService.findAll(ProductTypeDao.class));
            return "/product/wbedit";
        }
        return "/product/edit";
    }

    @RequestMapping("update")
    @ResponseBody
    public JSONObject update(Product product, HttpServletRequest request){
        try {
            Admin admin = (Admin) getCurrentUser(request);
            if ("1".equals(product.getSelf()) && StringUtil.isNullOrEmpty(product.getShopId())) {
                return this.FmtResult(false, "请选择商品所属商家", null);
            }
            if ("0".equals(product.getSelf())) {
                product.setShopId(null);
            }
            this.baseService.update(ProductDao.class, product);
            this.saveLog("修改商品：" + product.getTitle(), request);
            return this.FmtResult(true, "修改成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false, "修改失败", null);
    }

    @RequestMapping("delete")
    @ResponseBody
    public JSONObject delete(String id, HttpServletRequest request){
        Product product = this.baseService.findById(ProductDao.class, id);
        this.baseService.delete(ProductDao.class, new String[]{id});
        this.saveLog("删除商品：" + product.getTitle(), request);
        return this.FmtResult(true,"删除成功",null);
    }

    @RequestMapping("updateStatus")
    @ResponseBody
    public JSONObject updateStatus(String id, String status, HttpServletRequest request){
        try {
            Product product = this.baseService.findById(ProductDao.class, id);
            product.setStatus(status);
            this.baseService.update(ProductDao.class, product);
            this.saveLog((Status.NORMAL.name().equals(status) ? "上架" : "下架") + "商品：" + product.getTitle(), request);
            return this.FmtResult(true, "修改成功", null);
        }catch (Exception e){
            return this.FmtResult(false,"修改失败",null);
        }
    }

    private void formatDate(List<Product> list){
        List<String> shopIds = new ArrayList<>();
        if(list != null && list.size() > 0) {
            List<ProductType> types = this.baseService.findAll(ProductTypeDao.class);
            Map<String, String> map = new HashMap<>();
            for(ProductType type : types){
                map.put(type.getId(), type.getName());
            }
            for(Product product : list) {
                if("1".equals(product.getSelf())){
                    shopIds.add(product.getShopId());
                }
                product.setTypeName(map.get(product.getTypeId()));
            }
            if(shopIds.size() > 0) {
                //
                List<Shop> shops = shopService.listByIds(shopIds.toArray(new String[0]));
                Map<String,String> shopMap = new HashMap<>();
                for(Shop shop : shops){
                    shopMap.put(shop.getId(), shop.getName());
                }
                for(Product product : list){
                    product.setShopName(shopMap.get(product.getShopId()));
                }
            }
        }
    }
}
