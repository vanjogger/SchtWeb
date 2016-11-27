package com.scht.admin.controller;


import com.alibaba.fastjson.JSONArray;
import com.scht.admin.bean.UserStatus;
import com.scht.admin.dao.AdminDao;
import com.scht.admin.dao.AgentMoneyDao;
import com.scht.admin.entity.Admin;
import com.scht.admin.entity.AgentMoney;
import com.scht.admin.service.AdminService;
import com.scht.admin.service.AgentMoneyService;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.RoleService;
import com.scht.common.BaseController;
import com.scht.common.ConfigHelper;
import com.scht.common.PageInfo;
import com.scht.util.MD5Util;
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
 * Created by Administrator on 2016/4/6.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    AdminService adminService;
    @Autowired
    RoleService roleService;
    @Autowired
    BaseService baseService;
    @Autowired
    AgentMoneyService agentMoneyService;

    @RequestMapping("/list")
    public String list(){
        return "admin/admin_list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String loginName,String status,PageInfo page){
        Map<String,Object> params = new HashMap<>();
        if(StringUtil.isNotNull(loginName))
            params.put("loginName",loginName);
        if(StringUtil.isNotNull(status))
            params.put("status",status);
        page.setParams(params);
        this.page(AdminDao.class,page);

        return this.getPageResult(page);
    }

    @RequestMapping("/beforeUpdatePwd")
    public String beforeUpdatePwd(){

        return "admin/update_pwd";
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public JSONObject updatePwd(String old_pwd,String new_pwd,HttpServletRequest request,Model model){
        Admin  admin = (Admin) this.getCurrentUser(request);
        if(admin.getPassword().equals(MD5Util.getMD5ofStr(old_pwd))){
            admin.setPassword(MD5Util.getMD5ofStr(new_pwd));
            this.baseService.update(AdminDao.class, admin);
            return this.FmtResult(true,"保存成功",null);
        }else{
            return this.FmtResult(false,"保存失败",null);
        }
    }

    @RequestMapping("/beforeAdd")
    public String beforeAdd(String id,Model model){
        if(StringUtil.isNotNull(id)){
            Admin admin = this.adminService.get(id);
            model.addAttribute("admin",admin);
        }
        List list = this.roleService.listRole();
        model.addAttribute("list",list);

        return "admin/add";
    }

    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){
        if(StringUtil.isNotNull(id)){
            Admin admin = this.adminService.get(id);
            model.addAttribute("admin",admin);
        }
        List list = this.roleService.listRole();
        model.addAttribute("list",list);

        return "admin/edit";
    }


    @RequestMapping("/save")
    @ResponseBody
    public Object save(Admin admin,HttpServletRequest request){
        try {
            List<Admin> adminlist = this.adminService.listAdminbyName(admin.getLoginName());
            if (StringUtil.isNotEmpty(adminlist)) {
                Admin dbadmin = adminlist.get(0);
                if (StringUtil.isNotNull(admin.getId())) {
                    if(!admin.getId().equals(dbadmin.getId())){
                        return this.FmtResult(false, "已存在相同名称的管理员", null);
                    }
                }else{
                    return this.FmtResult(false, "已存在相同名称的管理员", null);
                }
            }
            if (StringUtil.isNotNull(admin.getId())) {
                this.saveLog("编辑管理员"+admin.getLoginName()+"("+admin.getId()+")",request);
                this.baseService.update(AdminDao.class, admin);
                saveAgentMoney(admin);
            } else {
                    admin.setId(UUIDFactory.random());
                    admin.setPassword(MD5Util.getMD5ofStr(admin.getPassword()));
                    admin.setLoginCnt(0);
                    admin.setStatus(UserStatus.NORMAL.name());
                    //this.adminService.saveAdmin(admin);
                    this.baseService.insert(AdminDao.class,admin);
                    this.saveLog("保存管理员" + admin.getLoginName(), request);
                    saveAgentMoney(admin);
            }
            return this.FmtResult(true, "保存成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"保存失败",null);
    }

    /**
     * 保存或更新代理商资金表
     * @param admin
     */
    private void saveAgentMoney(Admin admin) {
        if(admin.getType().equals("1")){//是代理商
            AgentMoney money = this.agentMoneyService.findByAgentId(admin.getId());
            if(money!=null){
                money.setAgentName(admin.getLoginName());
                this.baseService.update(AgentMoneyDao.class,money);
            }else{
                money = new AgentMoney();
                money.setId(UUIDFactory.random());
                money.setAgentId(admin.getId());
                money.setAgentName(admin.getLoginName());
                money.setAvailAmount("0");
                money.setFrozenAmount("0");
                money.setTotalAmount("0");
                this.baseService.insert(AgentMoneyDao.class,money);
            }
        }
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public JSONObject updateStatus(String id,String status,HttpServletRequest request){
        try {
            Admin admin =  this.baseService.findById(AdminDao.class,id);
            if(admin!=null) {
                admin.setStatus(status);
                String content = "冻结管理员"+admin.getLoginName();
                if(status.equals("NORMAL")){
                    content = "解冻管理员"+admin.getLoginName();
                }
                this.baseService.update(AdminDao.class, admin);
                this.saveLog(content,request);
                return this.FmtResult(true,"操作成功",null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"操作失败",null);
    }

    @RequestMapping("/beforeChangePwd")
    public String beforeChangePwd(String id,Model model){
        Admin admin = this.baseService.findById(AdminDao.class, id);
        model.addAttribute("data",admin);
        return "admin/change_pwd";
    }

    @RequestMapping("/changePwd")
    @ResponseBody
    public JSONObject changePwd(String id,String pwd){
        try{
            Admin admin = this.baseService.findById(AdminDao.class,id);
            if(admin!=null){
                admin.setPassword(MD5Util.getMD5ofStr(pwd));
                this.baseService.update(AdminDao.class, admin);
                return this.FmtResult(true,"修改成功",null);
            }
            return this.FmtResult(false,"没有找到数据",null);
        }catch (Exception e){
            e.printStackTrace();
            return this.FmtResult(false,"修改失败",null);
        }
    }


    @RequestMapping("/system")
    public String system(Model model){
        String name = ConfigHelper.GetInstance().GetConfig("system_user");
        String loginPwd = ConfigHelper.GetInstance().GetConfig("system_login_pwd");
        String payPwd = ConfigHelper.GetInstance().GetConfig("system_pay_pwd");
        model.addAttribute("user_name",name);
        model.addAttribute("login_pwd",loginPwd);
        model.addAttribute("pay_pwd",payPwd);
        return "admin/system";
    }

    @RequestMapping("/saveSystem")
    @ResponseBody
    public JSONObject saveSystem(String user_name){

        ConfigHelper.GetInstance().SetConfig("system_user", user_name);

        return this.FmtResult(true,"保存成功",null);
    }

    @RequestMapping("/beforeUpdateSystemPwd")
    public String beforeUpdateSystemPwd(){
        return "admin/system_pwd";
    }

    @RequestMapping("/updateSystemPwd")
    @ResponseBody
    public JSONObject updateSystemPwd(String loginPwd,String payPwd){

        ConfigHelper.GetInstance().SetConfig("system_login_pwd", MD5Util.getMD5ofStr(loginPwd));
        ConfigHelper.GetInstance().SetConfig("system_pay_pwd",MD5Util.getMD5ofStr(payPwd));
        return this.FmtResult(true,"保存成功",null);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONObject delete(String id){
        try{
            this.adminService.delete(id);
            return this.FmtResult(true,"删除成功",null);
        }catch (Exception e){
            e.printStackTrace();
            return this.FmtResult(false,"删除失败"+e.getMessage(),null);
        }
    }


    @RequestMapping("/query")
    @ResponseBody
    public Object query(String type){
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("type",type);
            List<Admin> list = this.adminService.query(map);
            JSONArray array = new JSONArray();
            if(!list.isEmpty()){
                for(Admin admin:list){
                    JSONObject json = new JSONObject();
                    json.put("text",admin.getLoginName());
                    json.put("value",admin.getId());
                    array.add(json);
                }
            }
            return array;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
