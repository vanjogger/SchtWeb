package com.scht.admin.controller;

import com.scht.admin.bean.SiteSetting;
import com.scht.admin.dao.SiteSettingDao;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wxh on 2017/6/6.
 */
@Controller
@RequestMapping("/siteSet/")
public class SiteSettingController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("find")
    public String find(ModelMap map){
        SiteSetting setting = this.baseService.findById(SiteSettingDao.class,"");
        map.put("data", setting);
        return "setting/site";
    }

    @RequestMapping("save")
    @ResponseBody
    public Object save(SiteSetting setting){
        SiteSetting old = this.baseService.findById(SiteSettingDao.class, "");
        if(old != null) {
            setting.setId(old.getId());
            this.baseService.update(SiteSettingDao.class, setting);
        }else{
            setting.setId(UUIDFactory.random());
            this.baseService.insert(SiteSettingDao.class, setting);
        }
        return this.FmtResult(true,"设置成功",null);
    }
}
