package com.scht.admin.controller;

import com.scht.admin.bean.Status;
import com.scht.admin.dao.ShopTypeDao;
import com.scht.admin.entity.ShopType;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.ShopTypeService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vanjoger on 2016/11/26.
 */
@Controller
@RequestMapping("/shopType")
public class ShopTypeController extends BaseController{
    @Autowired
    BaseService baseService;
    @Autowired
    ShopTypeService shopTypeService;

    @RequestMapping("/list")
    public String list(){
        return "shop/type/shop_type_list";
    }

    @RequestMapping("/listData")
    @ResponseBody
    public Object listData(String name,String status,PageInfo page){
        Map<String,Object> params = new HashMap<>();
        if(StringUtil.isNotNull(name))
            params.put("name",name);

        page.setParams(params);
        this.page(ShopTypeDao.class,page);

        return this.getPageResult(page);
    }



    @RequestMapping("/beforeEdit")
    public String beforeEdit(String id,Model model){
        if(StringUtil.isNotNull(id)){
            ShopType data = this.baseService.findById(ShopTypeDao.class,id);
            model.addAttribute("dto",data);
        }
        return "shop/type/shop_type_edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(ShopType data,HttpServletRequest request){
        try {
            List<ShopType> list = listBySort(data.getSort());
            if(StringUtil.isNotNull(data.getId())){
                if(StringUtil.isNotEmpty(list)){
                    ShopType type = list.get(0);
                    if(!type.getId().equals(data.getId()))
                        return this.FmtResult(false,"排序位置上已存在数据，请重新输入",null);
                }
                this.baseService.update(ShopTypeDao.class,data);
                this.saveLog("更新商家分类",request);
            }else{
                if(StringUtil.isNotEmpty(list))
                    return this.FmtResult(false,"排序位置上已存在数据，请重新输入",null);
                data.setId(UUIDFactory.random());
                data.setStatus(Status.NORMAL.name());
                this.baseService.insert(ShopTypeDao.class, data);

                this.saveLog("新增商家分类", request);
            }
            return this.FmtResult(true, "保存成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"保存失败",null);
    }

    private List<ShopType> listBySort(int sort){
        List<ShopType> list = this.shopTypeService.listBySort(sort);
        return list;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONObject delete(String id){
        try{
            this.baseService.delete(ShopTypeDao.class,new String[]{id});
            return this.FmtResult(true,"删除成功",null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.FmtResult(false,"删除失败",null);
    }

    @RequestMapping("/listAll")
    @ResponseBody
    public Object listAll(){
        List<ShopType> list = this.baseService.findAll(ShopTypeDao.class);
        JSONArray array = new JSONArray();
        if(!list.isEmpty()){
            for(ShopType dto:list){
                JSONObject json = new JSONObject();
                json.put("text",dto.getName());
                json.put("value",dto.getId());
                array.add(json);
            }
        }
        return array;
    }

}