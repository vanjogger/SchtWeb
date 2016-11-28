package com.scht.admin.controller;

import com.scht.admin.dao.OrderLimitSetDao;
import com.scht.admin.entity.OrderLimitSet;
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
 * Created by Administrator on 2016/11/28.
 */
@Controller
@RequestMapping("/orderLimitSet/")
public class OrderLimitSetController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("find")
    public String find(ModelMap map){
        map.put("data", this.baseService.findById(OrderLimitSetDao.class, ""));
        return "/setting/order_limit";
    }

    @RequestMapping("save")
    @ResponseBody
    public JSONObject save(OrderLimitSet set, HttpServletRequest request){
        try {
            OrderLimitSet data = this.baseService.findById(OrderLimitSetDao.class, "");
            if (data != null) {
                set.setId(data.getId());
                this.baseService.update(OrderLimitSetDao.class, set);
            } else {
                set.setId(UUIDFactory.random());
                this.baseService.insert(OrderLimitSetDao.class, set);
            }
            this.saveLog("设置订单时限", request);
            return this.FmtResult(true, "设置成功", null);
        }catch (Exception e){
            return this.FmtResult(false,"设置失败",null);
        }
    }
}
