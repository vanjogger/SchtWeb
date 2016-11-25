package com.scht.admin.controller;

import com.scht.admin.dao.WithdrawSetDao;
import com.scht.admin.entity.WithdrawSet;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.util.UUIDFactory;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/11/25.
 */
@Controller
@RequestMapping("/withdrawSet/")
public class WithdrawSetController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("find")
    public String find(ModelMap map){
        map.put("data", baseService.findById(WithdrawSetDao.class,""));
        return "/setting/withdraw_set";
    }

    @RequestMapping("save")
    @ResponseBody
    public JSONObject save(WithdrawSet set, HttpServletRequest request){
        WithdrawSet data = baseService.findById(WithdrawSetDao.class, "");
        if(data != null){
            set.setId(data.getId());
            this.baseService.update(WithdrawSetDao.class, set);
        }else{
            set.setId(UUIDFactory.random());
            this.baseService.insert(WithdrawSetDao.class,set);
        }
        this.saveLog("设置交易设置",request);
        return this.FmtResult(true,"设置成功",null);

    }
}
