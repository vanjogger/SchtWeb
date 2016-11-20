package com.scht.admin.controller;

import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by vanjoger on 2016/9/29.
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController{

    @Autowired
    BaseService baseService;

    @RequestMapping("/listQy")
    public String listQy(Model model){
        return "selector";
    }



}
