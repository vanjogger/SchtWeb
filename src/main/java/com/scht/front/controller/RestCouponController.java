package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.dao.CouponRecordDao;
import com.scht.admin.entity.CouponRecord;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wxh on 2017/4/25.
 */
@Controller
@RequestMapping("/rest/coupon/")
public class RestCouponController extends BaseController {

    @Autowired
    BaseService baseService;
    //我的优惠券
    @RequestMapping(value = "list", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object list(@RequestParam(value = "pageNo",defaultValue = "1")int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10")int pageSize,
                       String status,String memberId){
        RetResult result = null;
        if(StringUtil.isNullOrEmpty(memberId)) {
            result = new RetResult(RetResult.RetCode.Illegal_Request);
            return result;
        }
        Map<String,Object> map = new HashMap<>();
        if(!StringUtil.isNullOrEmpty(status)){
            map.put("status", status);
        }
        map.put("start", (pageNo-1) * pageSize);
        map.put("limit", pageSize);
        if(!StringUtil.isNullOrEmpty(memberId)) {
            map.put("memberId", memberId);
        }
        List<CouponRecord> list = this.baseService.searchByPage(CouponRecordDao.class, map);
        int count = this.baseService.count4Page(CouponRecordDao.class, map);
        RetData data = new RetData(pageNo, pageSize, list, count);
        result = new RetResult(RetResult.RetCode.OK);
        result.setData(data);
        return JSON.toJSON(result);
    }
}
