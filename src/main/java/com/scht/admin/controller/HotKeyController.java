package com.scht.admin.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.dao.HotKeyDao;
import com.scht.admin.entity.HotKey;
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
 * Created by Administrator on 2016/12/27.
 */
@Controller
@RequestMapping("/hotkey/")
public class HotKeyController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("list")
    public String list(){
        return "/hotkey/list";
    }

    @RequestMapping(value = "listData")
    @ResponseBody
    public Object listData(PageInfo pageInfo,HttpServletRequest request, String name){
        Map<String,Object> map = new HashMap<>();
        if(!StringUtil.isNullOrEmpty(name)){
            map.put("name", name);
        }
        pageInfo.setParams(map);
        pageInfo = this.page(HotKeyDao.class, pageInfo);
        return this.getPageResult(pageInfo);
    }

    @RequestMapping("add")
    public String add(){
        return "/hotkey/add";
    }

    @RequestMapping("save")
    @ResponseBody
    public Object save(HotKey data){
        data.setId(UUIDFactory.random());
        data.setCreateTime(System.currentTimeMillis());
        this.baseService.insert(HotKeyDao.class, data);
        return this.FmtResult(true,"保存成功",null);
    }

    @RequestMapping("find")
    public String find(String id, ModelMap map){
        map.put("data", this.baseService.findById(HotKeyDao.class, id));
        return "/hotkey/edit";
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(HotKey data){
        this.baseService.update(HotKeyDao.class, data);
        return this.FmtResult(true,"修改成功",null);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(String id){
        this.baseService.delete(HotKeyDao.class, new String[]{id});
        return this.FmtResult(true,"删除成功",null);
    }



}
