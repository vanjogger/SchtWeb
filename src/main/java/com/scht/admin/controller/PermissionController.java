package com.scht.admin.controller;

import com.scht.admin.dao.PermissionDao;
import com.scht.admin.entity.Permission;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.PermissionService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/4.
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    @Autowired
    PermissionService permissionService;
    @Autowired
    BaseService baseService;


    @RequestMapping("/list")
    public String list() {
        return "/permission/list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(PageInfo pageInfo){
        //Map<String, Object> params = new HashMap<String, Object>();
      //  params.put("sortColumns", "release_time");
        //params.put("state", 1);
      //  pageInfo.setParams(params);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sortColumns", "f_id");
        // params.put("state", MetaValueConstants.USER_STATE_DELETE);
        pageInfo.setParams(params);
       PageInfo info = this.page(PermissionDao.class, pageInfo);

        return this.getPageResult(info);
    }

    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){
        List<Permission> plist = this.permissionService.listAllPermission();
        model.addAttribute("list",plist);
        if(StringUtil.isNotNull(id)){
            Permission permission = this.permissionService.findPermission(id);
            model.addAttribute("permission",permission);
        }

        return "/permission/edit";
    }



    @RequestMapping("/save")
    @ResponseBody
    public JSONObject save(Permission permission,HttpServletRequest request){
        try {


            if (StringUtil.isNotNull(permission.getId())) {
                this.baseService.update(PermissionDao.class,permission);
                this.saveLog("修改权限" + permission.getName() + "(" + permission.getUrl() + ")", request);
            } else {
                permission.setId(UUIDFactory.random());
                this.baseService.insert(PermissionDao.class,permission);
                this.saveLog("保存权限" + permission.getName(), request);
            }

            return this.FmtResult(true, "保存成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"保存失败",null);
    }

   @RequestMapping("/delete")
    @ResponseBody
    public JSONObject delete(String id ,HttpServletRequest request){
        try{
            if(StringUtil.isNotNull(id)){
                Permission permission = this.baseService.findById(PermissionDao.class,id);
                this.baseService.delete(PermissionDao.class,new String[]{id});
                this.saveLog("删除权限:"+permission.getName(),request);

                return this.FmtResult(true,"删除成功",null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"删除失败",null);
    }



}
