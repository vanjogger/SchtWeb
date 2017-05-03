package com.scht.admin.controller;

import com.scht.admin.dao.CouponDao;
import com.scht.admin.dao.CouponRecordDao;
import com.scht.admin.entity.Coupon;
import com.scht.admin.service.BaseService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * 优惠券
 * Created by wxh on 2017/4/24.
 */
@Controller
@RequestMapping("/coupon/")
public class CouponController extends BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping("list")
    public String list(){
        return "/coupon/list";
    }

    @RequestMapping("listData")
    @ResponseBody
    public Object listData(PageInfo page){
        page.setParams(new HashMap<String, Object>());
         this.page(CouponDao.class, page);
        return this.getPageResult(page);
    }

    @RequestMapping("beforeEdit")
    public String beforeEdit(String id,Model model){
        if(StringUtil.isNotNull(id)){
            Coupon coupon = this.baseService.findById(CouponDao.class, id);
            model.addAttribute("dto",coupon);
        }
        return "coupon/coupon_edit";
    }

    @RequestMapping("save")
    @ResponseBody
    public Object save(Coupon coupon,HttpServletRequest request){
        try {
            if(StringUtil.isNotNull(coupon.getId())) {
                Coupon old = this.baseService.findById(CouponDao.class, coupon.getId());
                if(old.getPushCount() > coupon.getCount()) {
                    return this.FmtResult(false,"该券发放数量不能小于总数量",null);
                }
                coupon.setCreateTime(old.getCreateTime());
                coupon.setPushCount(old.getPushCount());
                this.baseService.update(CouponDao.class,coupon);
                this.saveLog("修改优惠券："+coupon.getName(), request);
            }else{
                coupon.setId(UUIDFactory.random());
                coupon.setCreateTime(System.currentTimeMillis());
                coupon.setPushCount(0);
                this.baseService.insert(CouponDao.class, coupon);
                this.saveLog("添加优惠券："+coupon.getName(), request);
            }
            return this.FmtResult(true, "保存成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"保存失败",null);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(String id, HttpServletRequest request){
        try {
            Coupon coupon = this.baseService.findById(CouponDao.class, id);
            this.baseService.delete(CouponDao.class, new String[]{id});
            this.saveLog("删除优惠券：" + coupon.getName(), request);
            return this.FmtResult(true, "删除成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"删除失败",null);
    }

    @RequestMapping("recordList")
    public String recordList(String couponId, ModelMap map){
        map.put("couponId", couponId);
        return "coupon/record_list";
    }

    @RequestMapping("recordData")
    @ResponseBody
    public Object recordData(PageInfo page, String couponId, String status, String memberAccount){
        Map<String,Object> map = new HashMap<>();
        map.put("couponId", couponId);
        if(!StringUtil.isNullOrEmpty(status)){
            map.put("status", status);
        }
        if(!StringUtil.isNullOrEmpty(memberAccount)) {
            map.put("memberAccount", memberAccount);
        }
        page.setParams(map);
        this.page(CouponRecordDao.class,page);
        return this.getPageResult(page);
    }
}
