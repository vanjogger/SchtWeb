package com.scht.admin.controller;

import com.scht.admin.bean.Status;
import com.scht.admin.dao.ShopDao;
import com.scht.admin.dao.SubShopDao;
import com.scht.admin.entity.Admin;
import com.scht.admin.entity.Shop;
import com.scht.admin.entity.ShopType;
import com.scht.admin.entity.SubShop;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.ShopService;
import com.scht.admin.service.ShopTypeService;
import com.scht.admin.service.SubShopService;
import com.scht.common.BaseController;
import com.scht.common.ConfigHelper;
import com.scht.common.PageInfo;
import com.scht.util.MD5Util;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vanjoger on 2016/11/26.
 */
@Controller
@RequestMapping("/chainShop")
public class ShopChainController extends BaseController {
    @Autowired
    ShopService shopService;
    @Autowired
    BaseService baseService;
    @Autowired
    ShopTypeService shopTypeService;
    @Autowired
    SubShopService subShopService;

    @RequestMapping("/list")
    public String list(){
        return "shop/chain/shop_chain_list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String name,String shopTypeId,PageInfo page){
        Map<String,Object> params = new HashMap<>();
        if(StringUtil.isNotNull(name))
            params.put("name",name);
        params.put("shopTypeId", shopTypeId);
        params.put("type","1");
        page.setParams(params);
        this.page(ShopDao.class, page);

        List<Shop> list = page.getResult();
        formatdata(list);
        page.setResult(list);

        return this.getPageResult(page);
    }

    private void formatdata(List<Shop> list){
        List<ShopType> data = shopTypeService.listMap();
        Map<String,String> map = new HashMap<>();
        for(ShopType type:data){
            map.put(type.getId(),type.getName());
        }

        if(!list.isEmpty()){
            for(Shop shop:list){
                shop.setShopTypeId(map.get(shop.getShopTypeId()));
                shop.setList(subShopService.listByShopId(shop.getId()));
            }
        }
    }

    @RequestMapping("/beforeAdd")
    public String beforeAdd(Model model){
        return "shop/chain/shop_chain_add";
    }

    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){
        if(StringUtil.isNotNull(id)){
            Shop data = this.baseService.findById(ShopDao.class,id);
            model.addAttribute("dto",data);
        }

        return "shop/chain/shop_chain_edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(Shop data,HttpServletRequest request){
        try {
            List<Shop> list = this.shopService.listByAccount(data.getAccount());

            if(StringUtil.isNotNull(data.getId())){
                if(!list.isEmpty()){
                    Shop shop = list.get(0);
                    if(!shop.getId().equals(data.getId()))
                        return this.FmtResult(false,"已存在相同账号的商家！",null);
                }

                this.baseService.update(ShopDao.class,data);
                this.saveLog("更新商家信息",request);
            }else{
                if(!list.isEmpty())
                    return this.FmtResult(false,"已存在相同账号的商家！",null);

                data.setId(UUIDFactory.random());
                data.setStatus(Status.NORMAL.name());
                data.setCreateTime(new Date().getTime());
                data.setPassword(MD5Util.getMD5ofStr(data.getPassword()));

                Admin admin = (Admin) this.getCurrentUser(request);
                data.setAgentId(admin.getId());

                this.baseService.insert(ShopDao.class, data);

                this.saveLog("新增商家信息", request);
            }
            return this.FmtResult(true, "保存成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"保存失败",null);
    }


    @RequestMapping("/frozen")
    @ResponseBody
    public JSONObject frozen(String id){
        try{
            Shop shop = this.baseService.findById(ShopDao.class,id);
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
        return this.FmtResult(false, "操作失败", null);
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
                return this.FmtResult(true,"重置密码成功",null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"重置密码失败",null);
    }


    /**
     * 连锁子商家页面  **************************
     */

    @RequestMapping("/listSub")
    public String listSub(String shopId,Model model){
        model.addAttribute("shopId",shopId);
        return "shop/chain/shop_sub_list";
    }

    @RequestMapping("/listSubData")
    @ResponseBody
    public Object listSubData(String name,String shopId,PageInfo page){
        Map<String,Object> params = new HashMap<>();
        if(StringUtil.isNotNull(name))
            params.put("name",name);
        params.put("shopId", shopId);

        page.setParams(params);
        this.page(SubShopDao.class, page);

      /*  List<Shop> list = page.getResult();
        formatdata(list);
        page.setResult(list);*/

        return this.getPageResult(page);
    }

  /*  private void formatdata(List<Shop> list){
        List<ShopType> data = shopTypeService.listMap();
        Map<String,String> map = new HashMap<>();
        for(ShopType type:data){
            map.put(type.getId(),type.getName());
        }

        if(!list.isEmpty()){
            for(Shop shop:list){
                shop.setShopTypeId(map.get(shop.getShopTypeId()));
                shop.setList(subShopService.listByShopId(shop.getId()));
            }
        }
    }*/


    @RequestMapping("/beforeAddEdit")
    public String beforeAddEdit(String id,String shopId,Model model){
        if(StringUtil.isNotNull(id)){
            Shop data = this.baseService.findById(ShopDao.class,id);
            model.addAttribute("dto",data);
        }
        model.addAttribute("shopId",shopId);
        return "shop/chain/shop_sub_edit";
    }

    @RequestMapping("/saveSub")
    @ResponseBody
    public Object saveSub(SubShop data,HttpServletRequest request){
        try {

            if(StringUtil.isNotNull(data.getId())){
                this.baseService.update(ShopDao.class,data);
                this.saveLog("更新连锁商家信息",request);
            }else{
                data.setId(UUIDFactory.random());
                data.setStatus(Status.NORMAL.name());

                this.baseService.insert(ShopDao.class, data);

                this.saveLog("新增连锁商家信息", request);
            }
            return this.FmtResult(true, "保存成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"保存失败",null);
    }


}
