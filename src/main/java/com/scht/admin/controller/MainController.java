package com.scht.admin.controller;

import com.scht.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wxh on 2016/12/2.
 */
@Controller
@RequestMapping("/main")
public class MainController extends BaseController {


    @RequestMapping("/workspace")
    public String workspace(){
        return "workspace";
    }


}
