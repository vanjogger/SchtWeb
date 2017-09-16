package com.scht.admin.controller;

import com.scht.admin.entity.Nation;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.NationService;
import com.scht.common.BaseController;
import com.scht.front.util.SmsUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by wxh on 2016/9/29.
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


    @RequestMapping("/querySms")
    public String querySms(Model model){
        Map map = SmsUtil.queryNum();
        if(map!=null&&map.get("code").equals("2")){
            model.addAttribute("msg","短信剩余条数："+map.get("num"));
        }else{
            model.addAttribute("msg","查询失败");
        }
        return "setting/sms";
    }

}
