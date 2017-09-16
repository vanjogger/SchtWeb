package com.scht.admin.controller;

import com.scht.admin.bean.Status;
import com.scht.admin.dao.OneLinkDao;
import com.scht.admin.dao.ShopDao;
import com.scht.admin.dao.SubShopDao;
import com.scht.admin.entity.*;
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
 * Created by wxh on 2016/11/26.
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

    @RequestMapping("/listDate")
    @ResponseBody
    public Object listDate(String name, String status, PageInfo pageInfo, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        if(!StringUtil.isNullOrEmpty(name)) {
            map.put("name", name);
        }
        if(!StringUtil.isNullOrEmpty(status)){
            map.put("status", status);
        }
        Admin admin = (Admin) this.getCurrentUser(request);
        if ("1".equals(admin.getType())){
            map.put("agentId", admin.getId());
        }
        pageInfo.setParams(map);
        pageInfo = this.page(OneLinkDao.class, pageInfo);
        return this.getPageResult(pageInfo);
    }

//    @RequestMapping("/listData")
//    @ResponseBody
//    public Object listData(String name, String status,PageInfo page, HttpServletRequest request){
//        Map<String,Object> map = new HashMap<>();
//        if(!StringUtil.isNullOrEmpty(name)) {
//            map.put("name", name);
//        }
//        if(!StringUtil.isNullOrEmpty(status)) {
//            map.put("status", status);
//        }
//        Admin admin = (Admin) this.getCurrentUser(request);
//        if("1".equals(admin.getType())){
//            map.put("agentId", admin.getId());
//        }
//        page.setParams(map);
//        page = this.page(OneLinkDao.class,page);
//        return this.getPageResult(page);
//    }


//    @RequestMapping("/listData")
//    @ResponseBody
//    public Object listData(String name,String shopTypeId,String agentId,PageInfo page,HttpServletRequest request){
//        Map<String,Object> params = new HashMap<>();
//        if(StringUtil.isNotNull(name))
//            params.put("name",name);
//        params.put("shopTypeId", shopTypeId);
//        params.put("type","1");
//        Admin admin = (Admin) this.getCurrentUser(request);
//        if(admin.getType().equals("1")){//代理商
//            params.put("agentId", admin.getId());
//        }
//        page.setParams(params);
//        this.page(ShopDao.class, page);
//
//        List<Shop> list = page.getResult();
//        formatdata(list);
//        page.setResult(list);
//
//        return this.getPageResult(page);
//    }

    private void formatdata(List<Shop> list){
        List<ShopType> data = shopTypeService.listMap(new HashMap());
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
//            Shop data = this.baseService.findById(ShopDao.class,id);
//            model.addAttribute("dto",data);
            model.addAttribute("dto", this.baseService.findById(OneLinkDao.class, id));
        }

        return "shop/chain/shop_chain_edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(OneLink data, HttpServletRequest request){
        Admin admin = (Admin) getCurrentUser(request);
        if(!StringUtil.isNullOrEmpty(data.getAccount())) {
            List<Shop> shops = shopService.listByAccount(data.getAccount());
            if(shops == null || shops.size() == 0) {
                return this.FmtResult(false,"关联商家不存在",null);
            }
            data.setShopId(shops.get(0).getId());
        }
        data.setId(UUIDFactory.random());
        data.setAgentId(admin.getId());
        data.setCreateTime(System.currentTimeMillis());
        this.baseService.insert(OneLinkDao.class, data);
        this.saveLog("新增一键连接:"+data.getName(),request);
        return this.FmtResult(true,"保存成功",null);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(OneLink data, HttpServletRequest request){
        Admin admin = (Admin) getCurrentUser(request);
        if(!StringUtil.isNullOrEmpty(data.getAccount())) {
            List<Shop> shops = shopService.listByAccount(data.getAccount());
            if(shops == null || shops.size() == 0) {
                return this.FmtResult(false,"关联商家不存在",null);
            }
            data.setShopId(shops.get(0).getId());
        }
        data.setAgentId(admin.getId());
        this.baseService.update(OneLinkDao.class, data);
        this.saveLog("修改一键连接："+data.getName(),request);
        return this.FmtResult(true,"保存成功",null);
    }
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(String id, HttpServletRequest request){
        Admin admin = (Admin) getCurrentUser(request);
        OneLink data = this.baseService.findById(OneLinkDao.class, id);
        if(data == null) {
            return this.FmtResult(false,"一键连接不存在",null);
        }
        this.baseService.delete(OneLinkDao.class, new String[]{id});
        this.saveLog("删除一键连接："+data.getName(),request);
        return this.FmtResult(true,"删除成功",null);
    }

//    @RequestMapping("/save")
//    @ResponseBody
//    public Object save(Shop data,HttpServletRequest request){
//        try {
//            List<Shop> list = this.shopService.listByAccount(data.getAccount());
//
//            if(StringUtil.isNotNull(data.getId())){
//                if(!list.isEmpty()){
//                    Shop shop = list.get(0);
//                    if(!shop.getId().equals(data.getId()))
//                        return this.FmtResult(false,"已存在相同账号的商家！",null);
//                }
//
//                this.baseService.update(ShopDao.class,data);
//                this.saveLog("更新商家信息",request);
//            }else{
//                if(!list.isEmpty())
//                    return this.FmtResult(false,"已存在相同账号的商家！",null);
//
//                data.setId(UUIDFactory.random());
//                data.setStatus(Status.NORMAL.name());
//                data.setCreateTime(new Date().getTime());
//                data.setPassword(MD5Util.getMD5ofStr(data.getPassword()));
//
//                Admin admin = (Admin) this.getCurrentUser(request);
//                data.setAgentId(admin.getId());
//
//                this.baseService.insert(ShopDao.class, data);
//
//                this.saveLog("新增商家信息", request);
//            }
//            return this.FmtResult(true, "保存成功", null);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return this.FmtResult(false,"保存失败",null);
//    }


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
                return this.FmtResult(true,"密码已重置为:"+initPwd,null);
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
    public String listSub(String id,Model model){
        model.addAttribute("shopId", id);
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


    @RequestMapping("/beforeSubEdit")
    public String beforeSubEdit(String id,String shopId,Model model){
        if(StringUtil.isNotNull(id)){
            SubShop data = this.baseService.findById(SubShopDao.class,id);
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
                this.baseService.update(SubShopDao.class,data);
                this.saveLog("更新连锁商家信息",request);
            }else{
                data.setId(UUIDFactory.random());
                data.setStatus(Status.NORMAL.name());

                this.baseService.insert(SubShopDao.class, data);

                this.saveLog("新增连锁商家信息", request);
            }
            return this.FmtResult(true, "保存成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"保存失败",null);
    }


    @RequestMapping("/subshop/frozen")
    @ResponseBody
    public JSONObject frozenSub(String id){
        try{
            SubShop subShop = this.baseService.findById(SubShopDao.class,id);
            if(subShop!=null){
                subShop.setStatus(Status.FROZEN.name());
                this.baseService.update(SubShopDao.class, subShop);
                return this.FmtResult(true,"操作成功",null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"操作失败",null);
    }

    @RequestMapping("/subshop/unfrozen")
    @ResponseBody
    public JSONObject unfrozenSub(String id){
        try{
            SubShop subShop = this.baseService.findById(SubShopDao.class,id);
            if(subShop!=null){
                subShop.setStatus(Status.NORMAL.name());
                this.baseService.update(SubShopDao.class,subShop);
                return this.FmtResult(true,"操作成功",null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"操作失败",null);
    }


    @RequestMapping("/subshop/deleteSub")
    @ResponseBody
    public JSONObject deleteSub(String id){
        try{
            this.baseService.delete(SubShopDao.class,new String[]{id});
            return this.FmtResult(true,"删除成功",null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"删除失败",null);
    }


}
