package com.scht.admin.controller;

import com.scht.admin.dao.NoticeDao;
import com.scht.admin.dao.NoticeTypeDao;
import com.scht.admin.entity.NoticeType;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.NoticeTypeService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
@Controller
@RequestMapping("/noticeType/")
public class NoticeTypeController extends BaseController {

    @Autowired
    BaseService baseService;

    @Autowired
    NoticeTypeService noticeTypeService;

    @RequestMapping("list")
    public String list(){
        return "/notice/type";
    }

    @RequestMapping("listData")
    @ResponseBody
    public JSONObject listData(PageInfo pageInfo){
        List<NoticeType> list = baseService.findAll(NoticeTypeDao.class);
        pageInfo.setResult(list);
        pageInfo.setTotal(list == null ? 0 : list.size());
        return this.getPageResult(pageInfo);
    }

    @RequestMapping("add")
    public String add(){
        return "/notice/type_add";
    }

    @RequestMapping("save")
    @ResponseBody
    public JSONObject save(NoticeType type, HttpServletRequest request){
        noticeTypeService.insert(type);
        this.saveLog("添加公告分类“"+type.getName()+"”",request);
        return this.FmtResult(true,"添加成功",null);
    }

    @RequestMapping("find")
    public String find(String id,ModelMap map){
        map.put("data", baseService.findById(NoticeTypeDao.class, id));
        return "/notice/type_edit";
    }

    @RequestMapping("update")
    @ResponseBody
    public JSONObject update(NoticeType type, HttpServletRequest request){
        baseService.update(NoticeTypeDao.class, type);
        this.saveLog("修改公告分类“" + type.getName() + "”", request);
        return FmtResult(true,"修改成功",null);
    }

    @RequestMapping("delete")
    @ResponseBody
    public JSONObject delete(String id, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("typeId", id);
        if(this.baseService.count4Page(NoticeDao.class,map) > 0){
            return this.FmtResult(false,"分类下有公告，不可删除",null);
        }
        NoticeType type = this.baseService.findById(NoticeTypeDao.class,id);
        baseService.delete(NoticeTypeDao.class, new String[]{id});
        this.saveLog("删除公告分类："+type.getName(),request);
        return this.FmtResult(true,"删除成功",null);
    }


}
