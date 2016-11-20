package com.scht.admin.controller;


import com.scht.admin.entity.Admin;
import com.scht.admin.service.*;
import com.scht.common.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/31.
 */
@Controller
public class IndexController extends BaseController {

    private static Logger logger  = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    AdminService adminService;

    @RequestMapping("/admin")
    public String index(){
        return "login";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/welcome")
    public String welcome(Model model,HttpServletRequest request){
        Admin admin = (Admin) this.getCurrentUser(request);
        Map map = new HashMap();

        Map data = new HashMap();


        model.addAttribute("data",data);
        return "welcome";
    }

}
