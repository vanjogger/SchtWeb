package com.scht.admin.controller;

import com.scht.admin.dao.PushSetDao;
import com.scht.admin.entity.PushSet;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.util.UUIDFactory;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/11/24.
 */
@Controller
@RequestMapping("/pushSet/")
public class PushSetController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("find")
    public String find(ModelMap map){
        map.put("data", this.baseService.findById(PushSetDao.class,""));
        return "/setting/push_set";
    }

    @RequestMapping("save")
    @ResponseBody
    public JSONObject save(PushSet set, HttpServletRequest request){
        PushSet data = this.baseService.findById(PushSetDao.class,"");
        if(data != null){
            set.setId(data.getId());
            this.baseService.update(PushSetDao.class,set);
        }else{
            set.setId(UUIDFactory.random());
            this.baseService.insert(PushSetDao.class, set);
        }
        this.saveLog("设置推送设置",request);
        return this.FmtResult(true,"设置成功",null);
    }
}
