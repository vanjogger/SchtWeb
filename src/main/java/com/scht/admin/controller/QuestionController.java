package com.scht.admin.controller;

import com.scht.admin.dao.CouponDao;
import com.scht.admin.dao.ProductTypeDao;
import com.scht.admin.dao.QuestionDao;
import com.scht.admin.dao.ShopDao;
import com.scht.admin.entity.QuestAnswer;
import com.scht.admin.entity.Question;
import com.scht.admin.entity.Shop;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.QuestionService;
import com.scht.admin.service.ShopService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2017/3/1.
 */
@Controller
@RequestMapping("/question/")
public class QuestionController extends BaseController {

    @Autowired
    BaseService baseService;
    @Autowired
    QuestionService questionService;
    @Autowired
    ShopService shopService;

    //列表
    @RequestMapping("list")
    public String list(){
        return "/quest/list";
    }

    @RequestMapping("listData")
    @ResponseBody
    public JSONObject listData(PageInfo pageInfo, String title, String shopName, String status){
        Map<String,Object> map = new HashMap<>();
        if(!StringUtil.isNullOrEmpty(title)){
            map.put("title", title);
        }
        if(!StringUtil.isNullOrEmpty(shopName)){
            map.put("shopName", shopName);
        }
        if(!StringUtil.isNullOrEmpty(status)) {
            map.put("status", status);
        }
        pageInfo.setParams(map);
        pageInfo = this.page(QuestionDao.class, pageInfo);
        initResult(pageInfo.getResult());
        return this.getPageResult(pageInfo);
    }
    //添加
    @RequestMapping("add")
    public String add(ModelMap map)
    {
        map.put("coupons",this.baseService.findAll(CouponDao.class));
        return "/quest/add";
    }
    @RequestMapping(value = "save", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject save( Question data, String jsonStr,HttpServletRequest request){
        try {
            if (StringUtil.isNullOrEmpty(jsonStr)) {
                return this.FmtResult(false, "请填写答案", null);
            }
            JSONArray array = JSONArray.fromObject(jsonStr);
            Object[] objects = array.toArray();
            List<QuestAnswer> answerList = new ArrayList<>();
            for (Object obj : objects) {
                answerList.add((QuestAnswer) JSONObject.toBean((JSONObject) obj, QuestAnswer.class));
            }
            data.setList(answerList);
            questionService.save(data);
            this.saveLog("添加问答：" + data.getTitle(), request);
            return this.FmtResult(true, "添加成功", null);
        }catch (Exception e){
            return this.FmtResult(false,"添加失败",null);
        }
    }
    //查看
    @RequestMapping("find")
    public String find(String id, ModelMap map){
        Question question = this.baseService.findById(QuestionDao.class, id);
        if(question != null) {
            question.setList(questionService.listAnswer(id));
            Shop shop = this.baseService.findById(ShopDao.class, question.getShopId());
            if(shop != null)
                question.setShopName(shop.getName());
        }
        map.put("coupons",this.baseService.findAll(CouponDao.class));
        map.put("data", question);
        return "/quest/edit";
    }

    @RequestMapping(value = "update", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject update(Question data,String jsonStr, HttpServletRequest request){
        try {
            if (StringUtil.isNullOrEmpty(jsonStr)) {
                return this.FmtResult(false, "请填写答案", null);
            }
            JSONArray array = JSONArray.fromObject(jsonStr);
            Object[] objects = array.toArray();
            List<QuestAnswer> answerList = new ArrayList<>();
            for (Object obj : objects) {
                answerList.add((QuestAnswer) JSONObject.toBean((JSONObject) obj, QuestAnswer.class));
            }
            data.setList(answerList);
            questionService.update(data);
            this.saveLog("修改问答：" + data.getTitle(), request);
            return this.FmtResult(true, "修改成功", null);
        }catch (Exception e){
            return this.FmtResult(false,"修改失败",null);
        }
    }

    //删除
    @RequestMapping(value = "delete", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject delete(String id, HttpServletRequest request){
        Question question = this.baseService.findById(QuestionDao.class,id);
        questionService.delete(id);
        this.saveLog("删除问题：" + question.getTitle(), request);
        return this.FmtResult(true,"删除成功",null);
    }


    private void initResult(List<Question> list){
        if(list == null || list.size() == 0) return;
        List<String> shopIds = new ArrayList<>();
        for(Question question : list) {
            shopIds.add(question.getShopId());
        }
        List<Shop> shopList = shopService.listByIds(shopIds.toArray(new String[0]));
        Map<String,Shop> shopMap = new HashMap<>();
        for(Shop shop : shopList) {
            shopMap.put(shop.getId(), shop);
        }
        for(Question question : list){
            if(shopMap.get(question.getShopId()) != null) {
                question.setShopName(shopMap.get(question.getShopId()).getName());
            }
        }
    }
}
