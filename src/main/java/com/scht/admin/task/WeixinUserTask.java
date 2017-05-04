package com.scht.admin.task;

import com.scht.admin.service.WeixinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wxh on 2017/5/3.
 */
@Component
public class WeixinUserTask   {

    @Autowired
    WeixinUserService weixinUserService;
    public String execute(){
        weixinUserService.weixinUserTask();
        return null;
    }
}
