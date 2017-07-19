package com.scht.admin.controller;

import com.scht.admin.dao.ProjectDao;

import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wxh on 2017/7/19.
 */
@Controller
@RequestMapping("/project/")
public class ProjectController extends BaseController {

    @Autowired
    BaseService baseService;
    //列表
    @RequestMapping("list")
    public String list(){
        return "/project/list";
    }

    @RequestMapping("listData")
    @ResponseBody
    public JSONObject listData(HttpServletRequest request,PageInfo pageInfo,
                               String name,String telephone, String shopName, String projectName){
        Map<String,Object> map = new HashMap<>();
        if(!StringUtil.isNullOrEmpty(name)) {
            map.put("name", name);
        }
        if(!StringUtil.isNullOrEmpty(telephone)) {
            map.put("telephone", telephone);
        }
        if(!StringUtil.isNullOrEmpty(shopName)) {
            map.put("shopName", shopName);
        }
        if(!StringUtil.isNullOrEmpty(projectName)) {
            map.put("projectName", projectName);
        }
        pageInfo.setParams(map);
        pageInfo = this.page(ProjectDao.class, pageInfo);
        return this.getPageResult(pageInfo);
    }

    //查看
    @RequestMapping("find")
    public String find(String id, ModelMap map){
        map.put("data", this.baseService.findById(ProjectDao.class, id));
        return "/project/edit";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONObject delete(String id){
        try {
            this.baseService.delete(ProjectDao.class,new String[]{id});
            return this.FmtResult(true,"删除成功",null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"删除失败",null);
    }
}
