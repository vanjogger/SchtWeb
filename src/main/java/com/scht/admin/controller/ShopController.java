package com.scht.admin.controller;

import com.scht.admin.bean.Status;
import com.scht.admin.dao.ShopDao;
import com.scht.admin.dao.ShopTypeDao;
import com.scht.admin.entity.Admin;
import com.scht.admin.entity.Shop;
import com.scht.admin.entity.ShopType;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.ShopService;
import com.scht.admin.service.ShopTypeService;
import com.scht.common.BaseController;
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
@RequestMapping("/shop")
public class ShopController extends BaseController {
    @Autowired
    ShopService shopService;
    @Autowired
    BaseService baseService;
    @Autowired
    ShopTypeService shopTypeService;

    @RequestMapping("/list")
    public String list(){
        return "shop/info/shop_info_list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String name,String shopTypeId,PageInfo page){
        Map<String,Object> params = new HashMap<>();
        if(StringUtil.isNotNull(name))
            params.put("name",name);
        params.put("shopTypeId", shopTypeId);

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
            }
        }
    }

    @RequestMapping("/beforeAdd")
    public String beforeAdd(Model model){
        return "shop/info/shop_info_add";
    }

    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){
        if(StringUtil.isNotNull(id)){
            Shop data = this.baseService.findById(ShopDao.class,id);
            model.addAttribute("dto",data);
        }

        return "shop/info/shop_info_edit";
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



    @RequestMapping("/delete")
    @ResponseBody
    public JSONObject delete(String id){
        try{
            this.baseService.delete(ShopDao.class,new String[]{id});
            return this.FmtResult(true,"删除成功",null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"删除失败",null);
    }


}
