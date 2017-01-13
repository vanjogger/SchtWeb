package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.bean.Status;
import com.scht.admin.dao.OneLinkDao;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.LocationUtil;
import com.scht.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/22.
 */
@Controller
@RequestMapping("/rest/oneLink/")
public class RestOneLinkController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping(value = "list",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object list(String code,
                       String lng, String lat,@RequestParam(value = "pageNo",defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){

        RetResult result = null;
        try {
            Map<String, Object> map = new HashMap<>();
            if(StringUtil.isNullOrEmpty(code) && !StringUtil.isNullOrEmpty(lng) && !StringUtil.isNullOrEmpty(lat)) {
                code = LocationUtil.geoRegion(lat, lng);
            }
            if (!StringUtil.isNullOrEmpty(code)) {
                map.put("districtId", code);
            }
            map.put("status", Status.NORMAL.name());
            map.put("front", "1");
            map.put("start", (pageNo - 1) * pageSize);
            map.put("limit", pageSize);
            List list = this.baseService.searchByPage(OneLinkDao.class, map);
            int count = this.baseService.count4Page(OneLinkDao.class, map);
            result = new RetResult(RetResult.RetCode.OK);
            RetData data = new RetData(pageNo,pageSize, list, count);
            result.setData(data);
        }catch (Exception e){
            result = new RetResult(RetResult.RetCode.System_Error);
        }
        return JSON.toJSON(result);
    }
}
