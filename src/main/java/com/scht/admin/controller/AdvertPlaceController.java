package com.scht.admin.controller;

import com.scht.admin.entity.AdvertPlace;
import com.scht.admin.service.AdvertPlaceService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/11/21.
 */
@Controller
@RequestMapping("/advertPlace/")
public class AdvertPlaceController extends BaseController {

    @Autowired
    AdvertPlaceService advertPlaceService;

    @RequestMapping("list")
    public String list(){
        return "/advert/place";

    }

    @RequestMapping("listData")
    @ResponseBody
    public JSONObject listData(PageInfo page, String code){
        page = this.advertPlaceService.list(code, page);
        return this.getPageResult(page);
    }

    @RequestMapping("add")
    public String add(){
       return "/advert/place_add";
    }

    @RequestMapping("save")
    @ResponseBody
    public JSONObject save(AdvertPlace data){
        int result = this.advertPlaceService.save(data);
        if(result == -1) {
            return this.FmtResult(false,"广告位编号被占用",null);
        }
        return FmtResult(true,"添加广告为成功",null);
    }

    @RequestMapping("find")
    public String find(String id, ModelMap map){
        map.put("data", advertPlaceService.find(id));
        return "/advert/place_edit";
    }

    @RequestMapping("update")
    @ResponseBody
    public JSONObject update(AdvertPlace data){
        int result  = this.advertPlaceService.update(data);
        if(result == -1) {
            return this.FmtResult(false,"广告位编号被占用",null);
        }
        return this.FmtResult(true,"修改成功",null);
    }

    @RequestMapping("delete")
    @ResponseBody
    public JSONObject delete(String id){
        int result = this.advertPlaceService.delete(id);
        if(result == -1) {
            return this.FmtResult(false,"广告位下有广告，不可删除",null);
        }
        return this.FmtResult(true,"广告位删除成功",null);
    }
}
