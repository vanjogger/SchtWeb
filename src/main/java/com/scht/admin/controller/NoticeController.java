package com.scht.admin.controller;

import com.scht.admin.dao.NoticeDao;
import com.scht.admin.dao.NoticeTypeDao;
import com.scht.admin.entity.Notice;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
@Controller
@RequestMapping("/notice/")
public class NoticeController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("list")
    public String list(ModelMap map){
        map.put("types", baseService.findAll(NoticeTypeDao.class));
        return "/notice/notice";
    }

    @RequestMapping("listData")
    @ResponseBody
    public JSONObject listData(String typeId, String title, PageInfo pageInfo){
        Map<String,Object> map = new HashMap<>();
        if(!StringUtil.isNullOrEmpty(typeId)){
            map.put("typeId", typeId);
        }
        if(!StringUtil.isNullOrEmpty(title)){
            map.put("title", title);
        }
        pageInfo.setParams(map);
        pageInfo = this.page(NoticeDao.class, pageInfo);
        return this.getPageResult(pageInfo);
    }

    @RequestMapping("add")
    public String add(ModelMap map){
        map.put("types", baseService.findAll(NoticeTypeDao.class));
        return "/notice/notice_add";
    }

    @RequestMapping("save")
    @ResponseBody
    public JSONObject save(Notice notice, HttpServletRequest request){
        notice.setId(UUIDFactory.random());
        notice.setCreateTime(System.currentTimeMillis());
        this.baseService.insert(NoticeDao.class, notice);
        this.saveLog("添加公告：" + notice.getTitle(), request);
        return this.FmtResult(true,"添加成功",null);

    }

    @RequestMapping("find")
    public String find(String id,ModelMap map){
        map.put("data", baseService.findById(NoticeDao.class, id));
        map.put("types", baseService.findAll(NoticeTypeDao.class));
        return "/notice/notice_edit";
    }

    @RequestMapping("update")
    @ResponseBody
    public JSONObject update(Notice notice, HttpServletRequest request){
        this.baseService.update(NoticeDao.class, notice);
        this.saveLog("修改公告：" + notice.getTitle(), request);
        return this.FmtResult(true,"修改成功",null);
    }

    @RequestMapping("delete")
    @ResponseBody
    public JSONObject delete(HttpServletRequest request, String id){
        Notice notice = baseService.findById(NoticeDao.class, id);
        baseService.delete(NoticeDao.class, new String[]{id});
        this.saveLog("删除公告："+notice.getTitle(),request);
        return this.FmtResult(true,"删除成功",null);
    }
}
