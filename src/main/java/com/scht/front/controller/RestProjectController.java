package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.dao.ProjectDao;
import com.scht.admin.entity.Project;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.front.bean.RetResult;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * Created by wxh on 2017/7/19.
 */
@Controller
@RequestMapping("/rest/project/")
public class RestProjectController  extends BaseController{

    @Autowired
    BaseService baseService;

    @RequestMapping(value = "save", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object save(Project project){
       RetResult result = null;
        project.setId(UUIDFactory.random());
        project.setCreateTime(System.currentTimeMillis());
        this.baseService.insert(ProjectDao.class, project);
        result = new RetResult(RetResult.RetCode.OK);
        return JSON.toJSON(result);
    }
}
