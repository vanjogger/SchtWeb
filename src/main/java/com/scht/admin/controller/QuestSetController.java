package com.scht.admin.controller;

import com.scht.admin.dao.QuestSetDao;
import com.scht.admin.entity.QuestSet;
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
 * Created by Administrator on 2017/3/1.
 */
@Controller
@RequestMapping("/questSet/")
public class QuestSetController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("find")
    public String find(ModelMap map){
        map.put("data", this.baseService.findById(QuestSetDao.class,""));
        return "/quest/set";
    }

    @RequestMapping("save")
    @ResponseBody
    public JSONObject save(QuestSet data, HttpServletRequest request){
        QuestSet set = this.baseService.findById(QuestSetDao.class,"");
        if(set != null) {
            data.setId(set.getId());
            this.baseService.update(QuestSetDao.class, data);
        }else{
            data.setId(UUIDFactory.random());
            this.baseService.insert(QuestSetDao.class, data);
        }
        this.saveLog("设置问答设置", request);
        return this.FmtResult(true,"设置成功","");
    }
}
