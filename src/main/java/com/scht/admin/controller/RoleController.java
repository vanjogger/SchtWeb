package com.scht.admin.controller;


import com.scht.admin.bean.TreeBean;
import com.scht.admin.entity.Permission;
import com.scht.admin.entity.Role;
import com.scht.admin.entity.RolePermission;
import com.scht.admin.service.PermissionService;
import com.scht.admin.service.RoleService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/9.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/list")
    public String list(){
        return "/role/list";
    }


    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(PageInfo pageInfo){
        List list  = this.roleService.listRole();
        pageInfo.setTotal(list!=null?list.size():0);
        pageInfo.setResult(list);
        return this.getPageResult(pageInfo);
    }

    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){

        List<Permission> plist = this.permissionService.listAllPermission();
        List<String> rplist = new ArrayList<>();

        if(StringUtil.isNotNull(id)){
            Role role = this.roleService.getRolebyId(id);
            model.addAttribute("role",role);
            rplist =  this.roleService.listPermissionByRoleId(id);

        }

        JSONArray array = new JSONArray();
        for(Permission p:plist){
            if(p.getpId().equals("0")){//�����˵�
               TreeBean bean = new TreeBean();
                bean.setId(p.getId());
                bean.setExpanded(true);
                bean.setText(p.getName());
                bean.setChecked(rplist.contains(p.getUrl()) ? true : false);
                bean = getTreeData(bean,rplist,plist);
               // parlist.add(bean);

                array.add(JSONObject.fromObject(bean));

            }
        }
        model.addAttribute("data",array);
        return "/role/edit";
    }

    /**
     * ѭ��������װ����
     * @param tree
     * @param datalist
     * @param list
     * @return
     */
    private TreeBean getTreeData(TreeBean tree,List<String> datalist,List<Permission> list){
        for(Permission p:list){
            if(!p.getpId().equals("0")&&p.getpId().equals(tree.getId())){
                List<TreeBean> children = tree.getChildren();
                if(!StringUtil.isNotEmpty(children))
                    children = new ArrayList<>();
                TreeBean bean = new TreeBean();
                bean.setId(p.getId());
                bean.setExpanded(true);
                bean.setText(p.getName());
                bean.setChecked(datalist.contains(p.getUrl()) ? true : false);

                bean = getTreeData(bean,datalist,list);
                children.add((bean));
                tree.setChildren(children);
            }
        }
        return tree;
    }



    @RequestMapping("/save")
    @ResponseBody
    public JSONObject save(Role role,String permissions,HttpServletRequest request){
        try {
            List list = this.roleService.getRoleByName(role.getRoleName());
            if(StringUtil.isNotEmpty(list)){
                if(StringUtil.isNotNull(role.getId())){//�༭
                    Role dbrole = (Role) list.get(0);
                    if(!dbrole.getId().equals(role.getId())){
                        return this.FmtResult(false,"已存在相同名称的角色",null);
                    }
                }else{
                    return this.FmtResult(false,"已存在相同名称的角色",null);
                }
            }

            String roleId = role.getId();
            if (StringUtil.isNotNull(role.getId())) {
                    this.roleService.deleteRolePermission(roleId);
                    this.roleService.updateRole(role);
                    this.saveLog("编辑角色" + role.getRoleName() + "(" + role.getId() + ")", request);
            } else {
                   roleId = UUIDFactory.random();
                   role.setId(roleId);
                   role.setStatus("0");
                   this.roleService.saveRole(role);
                   this.saveLog("保存角色" + role.getRoleName(), request);
            }
            String[] arrays = permissions.split(",");
            for(String p:arrays) {
                RolePermission rp = new RolePermission();
                rp.setId(UUIDFactory.random());
                rp.setPermissionId(p);
                rp.setRoleId(roleId);
                this.roleService.saveRolePermission(rp);
            }

            return this.FmtResult(true, "保存成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"保存失败",null);
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public JSONObject updateStatus(String id,String status,HttpServletRequest request){
        try{
            if(StringUtil.isNotNull(id)){
                Role role = this.roleService.getRolebyId(id);
                if(role!=null){
                    role.setStatus(status);
                    this.roleService.updateRole(role);
                    this.saveLog("更新角色状态"+role.getRoleName(),request);
                }
                return this.FmtResult(true,"更新成功",null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"更新失败",null);
    }


}
