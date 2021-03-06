package com.scht.admin.controller;

import com.scht.admin.dao.DispatchMemberDao;
import com.scht.admin.dao.HotKeyDao;
import com.scht.admin.dao.ShopDao;
import com.scht.admin.entity.Admin;
import com.scht.admin.entity.DispatchMember;
import com.scht.admin.entity.HotKey;
import com.scht.admin.entity.Shop;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wxh on 2017/6/8.
 */
@Controller
@RequestMapping("/dispatchMember/")
public class DispatchMemberController extends BaseController {


    @Autowired
    BaseService baseService;

    @RequestMapping("list")
    public String list(){
        return "/dispatch/list";
    }

    @RequestMapping(value = "listData")
    @ResponseBody
    public Object listData(PageInfo pageInfo,HttpServletRequest request, String shopId){
        Map<String,Object> map = new HashMap<>();
        Admin admin = (Admin) getCurrentUser(request);
        if("1".equals(admin.getType())) {
            map.put("agentId", admin.getId());
        }
        if(!StringUtil.isNullOrEmpty(shopId)) {
           Shop shop = this.baseService.findById(ShopDao.class, shopId);
            if(shop != null) {
                if(StringUtil.isNotNull(shop.getDistrictId())){
                    map.put("region", shop.getDistrictId());
                }else if(StringUtil.isNotNull(shop.getCityId())) {
                    map.put("region", shop.getCityId());
                }else if(StringUtil.isNotNull(shop.getProvinceId())) {
                    map.put("region", shop.getProvinceId());
                }
            }
        }
        pageInfo.setParams(map);
        pageInfo = this.page(DispatchMemberDao.class, pageInfo);
        return this.getPageResult(pageInfo);
    }

    @RequestMapping("add")
    public String add(){
        return "/dispatch/add";
    }

    @RequestMapping("save")
    @ResponseBody
    public Object save(DispatchMember data, HttpServletRequest request){
        data.setId(UUIDFactory.random());
        data.setCreateTime(System.currentTimeMillis());
        Admin admin = (Admin) getCurrentUser(request);
        if("1".equals(admin.getType()))
            data.setAgentId(admin.getId());
        this.baseService.insert(DispatchMemberDao.class, data);
        return this.FmtResult(true,"保存成功",null);
    }

    @RequestMapping("find")
    public String find(String id, ModelMap map){
        map.put("data", this.baseService.findById(DispatchMemberDao.class, id));
        return "/dispatch/edit";
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(DispatchMember data){
        this.baseService.update(DispatchMemberDao.class, data);
        return this.FmtResult(true,"修改成功",null);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(String id){
        this.baseService.delete(DispatchMemberDao.class, new String[]{id});
        return this.FmtResult(true,"删除成功",null);
    }

}
