package com.scht.admin.controller;

import com.scht.admin.entity.Nation;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.NationService;
import com.scht.common.BaseController;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by vanjoger on 2016/9/29.
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController{

    @Autowired
    BaseService baseService;
    @Autowired
    NationService nationService;

    @RequestMapping("/listQy")
    public String listQy(Model model){
        return "selector";
    }

    @RequestMapping("listNationByParentId")
    @ResponseBody
    public JSONObject listNationByParentId(String lx,String id){
        try{
            List<Nation> list = this.nationService.listByParentId(lx,id);
            return this.FmtResult(true,null,list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,null,null);
    }

}
