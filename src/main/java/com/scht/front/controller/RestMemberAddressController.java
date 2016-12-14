package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.dao.MemberAddressDao;
import com.scht.admin.entity.MemberAddress;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.MemberAddressService;
import com.scht.admin.service.MemberService;
import com.scht.common.BaseController;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Created by Administrator on 2016/12/14.
 */
@Controller
@RequestMapping("/rest/memberAddress/")
public class RestMemberAddressController extends BaseController {

    @Autowired
    BaseService baseService;

    @Autowired
    MemberAddressService memberAddressService;

    @RequestMapping(value = "list", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object list(String memberId){
       List list = memberAddressService.listByMemberId(memberId);
        RetResult result = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(list);
        result.setData(data);
        return JSON.toJSON(result);
    }

    //新增
    @RequestMapping(value = "save",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object save(String memberId, String name,String mobile, String address, String details,String isDefault){
        RetResult result = null;
        try{
            result = memberAddressService.RestSave(memberId,name,mobile,address,details,isDefault);
        }catch (Exception e){
            result = new RetResult(RetResult.RetCode.System_Error);
        }
        return JSON.toJSON(result);
    }
    //查询
    @RequestMapping(value = "find", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object find(String id){
        MemberAddress address = this.baseService.findById(MemberAddressDao.class, id);
        RetResult result = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(address);
        result.setData(data);
        return JSON.toJSON(result);
    }

    //修改
    @RequestMapping(value = "update", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object update(String id, String memberId, String name,String mobile, String address, String details,String isDefault){
            RetResult result = null;
            try{
                result = memberAddressService.restUpdate(id, memberId, name, mobile, address, details, isDefault);
            }catch (Exception e){
                result = new RetResult(RetResult.RetCode.System_Error);
            }
            return JSON.toJSON(result);
    }
    //删除
    @RequestMapping(value = "delete", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object delete(String id){
        RetResult result = null;
        try {
            this.baseService.delete(MemberAddressDao.class, new String[]{id});
            result = new RetResult(RetResult.RetCode.OK);
        }catch (Exception e){
            result = new RetResult(RetResult.RetCode.System_Error);
        }
        return JSON.toJSON(result);
    }
    //查询默认
    @RequestMapping(value = "findDefault", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object findDefault(String memberId){
        MemberAddress address = memberAddressService.findDefault(memberId);
        RetResult result = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(address);
        result.setData(data);
        return JSON.toJSON(result);
    }
}
