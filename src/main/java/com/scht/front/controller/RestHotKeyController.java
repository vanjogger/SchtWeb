package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.dao.HotKeyDao;
import com.scht.admin.entity.HotKey;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/27.
 */
@Controller
@RequestMapping("/rest/hotKey/")
public class RestHotKeyController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping(value = "list", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object list(@RequestParam(value = "pageNo",defaultValue = "1")int pageNo, @RequestParam(value = "pageSize",
            defaultValue = "10" )int pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("start", (pageNo-1) * pageSize);
        map.put("limit", pageSize);
        List<HotKey> list = this.baseService.searchByPage(HotKeyDao.class, map);
        int count = this.baseService.count4Page(HotKeyDao.class, map);
        RetResult restResult = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(pageNo,pageSize, list,count);
        restResult.setData(data);
        return JSON.toJSON(restResult);
    }

}
