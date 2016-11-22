package com.scht.admin.controller;

import com.scht.admin.dao.AdvertDao;
import com.scht.admin.dao.AdvertPlaceDao;
import com.scht.admin.entity.Advert;
import com.scht.admin.service.AdvertPlaceService;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/22.
 */
@Controller
@RequestMapping("/advert/")
public class AdvertController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("list")
    public String list(ModelMap map){
        map.put("places", baseService.findAll(AdvertPlaceDao.class));
        return "/advert/advert";
    }

    @RequestMapping(value = "listData")
    @ResponseBody
    public JSONObject listData(PageInfo pageInfo,String placeId,String title,String status){
        Map<String,Object> map = new HashMap<>();
        if(!StringUtil.isNullOrEmpty(placeId)) {
            map.put("placeId", placeId);
        }
        if(!StringUtil.isNullOrEmpty(title)) {
            map.put("title", title);
        }
        if(!StringUtil.isNullOrEmpty(status)) {
            map.put("status", status);
        }
        pageInfo.setParams(map);
        pageInfo = this.page(AdvertDao.class, pageInfo);
        return this.getPageResult(pageInfo);
    }

    //添加
    @RequestMapping("add")
    public String add(ModelMap map){
        map.put("places", baseService.findAll(AdvertPlaceDao.class));
        return "/advert/advert_add";
    }

    //保存
    @RequestMapping(value = "save")
    @ResponseBody
    public JSONObject save(Advert advert, HttpServletRequest request){
        advert.setId(UUIDFactory.random());
        advert.setCreateTime(System.currentTimeMillis());
        baseService.insert(AdvertDao.class, advert);
        this.saveLog("添加广告“" + advert.getTitle() + "”", request);
        return this.FmtResult(true,"添加广告成功",null);
    }

    //编辑
    @RequestMapping("find")
    public String find(String id,ModelMap map){
        map.put("data", baseService.findById(AdvertDao.class, id));
        map.put("places", baseService.findAll(AdvertPlaceDao.class));
        return "/advert/advert_edit";
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public JSONObject update(Advert advert, HttpServletRequest request){
        baseService.update(AdvertDao.class, advert);
        this.saveLog("修改广告“" + advert.getTitle() + "”", request);
        return this.FmtResult(true,"修改广告成功",null);
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public JSONObject delete(String id, HttpServletRequest request){
        this.baseService.delete(AdvertDao.class, new String[]{id});
        this.saveLog("删除广告",request);
        return this.FmtResult(true,"删除广告成功",null);
    }

    @RequestMapping(value = "deleteAll")
    @ResponseBody
    public JSONObject deleteAll(@RequestParam("ids")String[] ids, HttpServletRequest request){
        this.baseService.delete(AdvertDao.class, ids);
        this.saveLog("删除广告",request);
        return this.FmtResult(true,"删除广告成功",null);
    }
}
