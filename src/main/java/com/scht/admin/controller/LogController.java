package com.scht.admin.controller;

import com.scht.admin.dao.LogDao;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.PermissionService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.DateUtil;
import com.scht.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/4.
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseController {

    @Autowired
    PermissionService permissionService;
    @Autowired
    BaseService baseService;


    @RequestMapping("/list")
    public String list() {
        return "/log/list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String name,String startDate,String endDate,PageInfo pageInfo){
        //Map<String, Object> params = new HashMap<String, Object>();
      //  params.put("sortColumns", "release_time");
        //params.put("state", 1);
      //  pageInfo.setParams(params);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("operateName",name);
        if(StringUtil.isNotNull(startDate))
            params.put("startMills", DateUtil.getLongFormStrDate(startDate));
        if(StringUtil.isNotNull(endDate))
            params.put("endMills",DateUtil.getLongFormStrDate(endDate));
        params.put("sortColumns", "f_id");
        // params.put("state", MetaValueConstants.USER_STATE_DELETE);
        pageInfo.setParams(params);
       PageInfo info = this.page(LogDao.class, pageInfo);

        return this.getPageResult(info);
    }




}
