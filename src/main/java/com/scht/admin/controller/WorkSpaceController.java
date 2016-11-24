package com.scht.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vanjoger on 2016/11/24.
 */
@Controller
public class WorkSpaceController {


    @RequestMapping("/workspace")
    public String workSpace(HttpServletRequest request){
        return "workspace";
    }
}
