package com.scht.admin.controller;

import com.scht.admin.dao.MemberAddressDao;
import com.scht.admin.entity.MemberAddress;
import com.scht.admin.entity.ShopBank;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wxh on 2016/11/27.
 */
@Controller
@RequestMapping("/memberAddress")
public class MemberAddressController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("/list")
    public String list(){
        return "member/member_address_list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String memberName,PageInfo page){
        Map<String,Object> params = new HashMap<>();
        
        params.put("memberName",memberName);

        page.setParams(params);
        this.page(MemberAddressDao.class,page);

        return this.getPageResult(page);
    }



    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){
        if(StringUtil.isNotNull(id)){
            MemberAddress address = this.baseService.findById(MemberAddressDao.class,id);
            model.addAttribute("dto",address);
        }
        return "member/member_address_view";
    }


    @RequestMapping("/delete")
    @ResponseBody
    public JSONObject delete(String id){
        try {
            this.baseService.delete(MemberAddressDao.class,new String[]{id});
            return this.FmtResult(true,"删除成功",null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"删除失败",null);
    }
}
