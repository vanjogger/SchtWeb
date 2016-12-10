package com.scht.admin.controller;

import com.scht.admin.dao.ProductCommentDao;
import com.scht.admin.entity.Admin;
import com.scht.admin.entity.ProductComment;
import com.scht.admin.entity.ShopBank;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.ShopBankService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.DateUtil;
import com.scht.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vanjoger on 2016/11/27.
 */
@Controller
@RequestMapping("/productComment")
public class ProductCommentController extends BaseController {

    @Autowired
    BaseService baseService;
    @Autowired
    ShopBankService shopBankService;


    @RequestMapping("/list")
    public String list(){
        return "product/product_comment_list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String productName,String startDate,String endDate,String status,PageInfo page){
        Map<String,Object> params = new HashMap<>();
        params.put("productName",productName);
        if(StringUtil.isNotNull(startDate))
            params.put("startDate", DateUtil.getLongFormStrDate(startDate));
        if(StringUtil.isNotNull(endDate))
            params.put("endDate", DateUtil.getLongFormStrDate(endDate));
        params.put("status",status);

        page.setParams(params);
        this.page(ProductCommentDao.class,page);

        return this.getPageResult(page);
    }



    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){
        if(StringUtil.isNotNull(id)){
            ProductComment comment = this.baseService.findById(ProductCommentDao.class,id);
            model.addAttribute("dto",comment);
        }
        return "product/product_comment_edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public JSONObject save(ProductComment comment,HttpServletRequest request){
        try {
            comment.setStatus("1");
            Admin admin = (Admin) getCurrentUser(request);
            comment.setReplyId(admin.getId());
            comment.setReplyTime(new Date().getTime());
            this.baseService.update(ProductCommentDao.class,comment);
            //保存日志
            this.saveLog("回复商品评论：" + comment.getId(), request);
            return this.FmtResult(true, "修改成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"修改失败",null);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONObject delete(String id){
        try {
            this.baseService.delete(ProductCommentDao.class,new String[]{id});
            return this.FmtResult(true,"删除成功",null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"删除失败",null);
    }
}
