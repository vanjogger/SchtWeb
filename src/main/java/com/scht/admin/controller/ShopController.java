package com.scht.admin.controller;

import com.scht.admin.bean.Status;
import com.scht.admin.dao.ShopDao;
import com.scht.admin.entity.Admin;
import com.scht.admin.entity.Nation;
import com.scht.admin.entity.Shop;
import com.scht.admin.entity.ShopType;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.NationService;
import com.scht.admin.service.ShopService;
import com.scht.admin.service.ShopTypeService;
import com.scht.common.*;
import com.scht.util.MD5Util;
import com.scht.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vanjoger on 2016/11/26.
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends BaseController {
    @Autowired
    ShopService shopService;
    @Autowired
    BaseService baseService;
    @Autowired
    ShopTypeService shopTypeService;
    @Autowired
    NationService nationService;

    @RequestMapping("/list")
    public String list(){
        return "shop/info/shop_info_list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String name,String shopTypeId,String agentId,PageInfo page,HttpServletRequest request){
        Map<String,Object> params = new HashMap<>();
        if(StringUtil.isNotNull(name))
            params.put("name",name);
        params.put("shopTypeId", shopTypeId);
        params.put("type", "0");

        Admin admin = (Admin) this.getCurrentUser(request);
        if(admin.getType().equals("1")){//代理商
            params.put("agentId", admin.getId());
        }
        page.setParams(params);
        this.page(ShopDao.class, page);

        List<Shop> list = page.getResult();
        formatdata(list);
        page.setResult(list);

        return this.getPageResult(page);
    }

    private void formatdata(List<Shop> list){
        List<ShopType> data = shopTypeService.listMap(new HashMap());
        Map<String,String> map = new HashMap<>();
        for(ShopType type:data){
            map.put(type.getId(),type.getName());
        }

        if(!list.isEmpty()){
            for(Shop shop:list){
                shop.setShopTypeId(map.get(shop.getShopTypeId()));
            }
        }
    }

    @RequestMapping("/beforeAdd")
    public String beforeAdd(Model model){
        List<DictionaryEntity> list = DictionaryConfigHelper.getDictionaryEntityList("ShopCode");
        model.addAttribute("list",list);
        return "shop/info/shop_info_add";
    }

    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){
        if(StringUtil.isNotNull(id)){
            Shop data = this.baseService.findById(ShopDao.class,id);
            model.addAttribute("dto",data);
        }

        List<DictionaryEntity> list = DictionaryConfigHelper.getDictionaryEntityList("ShopCode");
        model.addAttribute("list",list);

        return "shop/info/shop_info_edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(Shop data,HttpServletRequest request){
        try {
            Admin admin = (Admin) this.getCurrentUser(request);
           String result =  this.shopService.save(data, admin);
            if(result.equals("0")) {
                if(StringUtil.isNotNull(data.getId())){
                    this.saveLog("更新商家信息",request);
                }else{
                    this.saveLog("新增商家信息",request);
                }
                return this.FmtResult(true, "保存成功", null);
            }else if(result.equals("-1")){
                return this.FmtResult(false,"已存在相同账号的商家",null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"保存失败",null);
    }


    @RequestMapping("/frozen")
    @ResponseBody
    public JSONObject frozen(String id){
        try{
            Shop shop = this.baseService.findById(ShopDao.class, id);
            if(shop!=null){
                shop.setStatus(Status.FROZEN.name());
                this.baseService.update(ShopDao.class,shop);
            }
            return this.FmtResult(true,"操作成功",null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"操作失败",null);
    }

    @RequestMapping("/unfrozen")
    @ResponseBody
    public JSONObject unfrozen(String id){
        try{
            Shop shop = this.baseService.findById(ShopDao.class,id);
            if(shop!=null){
                shop.setStatus(Status.NORMAL.name());
                this.baseService.update(ShopDao.class,shop);
            }
            return this.FmtResult(true,"操作成功",null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"操作失败",null);
    }

    @RequestMapping("/resetPwd")
    @ResponseBody
    public JSONObject resetPwd(String id){
        try{
            Shop shop = this.baseService.findById(ShopDao.class,id);
            if(shop!=null){
                String initPwd = ConfigHelper.GetInstance().GetConfig("InitPwd");
                shop.setPassword(MD5Util.getMD5ofStr(initPwd));
                this.baseService.update(ShopDao.class,shop);
                return this.FmtResult(true,"密码已重置为:"+initPwd,null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"重置密码失败",null);
    }


    //根据名称模糊检索所有商家
    @RequestMapping("/ajaxList")
    @ResponseBody
    public JSONObject searchShop(String name){
        return this.FmtResult(true,"", shopService.listByName(name));
    }
}
