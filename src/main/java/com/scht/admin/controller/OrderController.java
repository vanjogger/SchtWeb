package com.scht.admin.controller;

import com.scht.admin.bean.OrderStatus;
import com.scht.admin.dao.*;
import com.scht.admin.entity.*;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.OrderProductService;
import com.scht.admin.service.OrderService;
import com.scht.admin.service.ShopService;
import com.scht.common.BaseController;
import com.scht.common.PageInfo;
import com.scht.util.DateUtil;
import com.scht.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1.
 */
@Controller
@RequestMapping("/order/")
public class OrderController extends BaseController {

    @Autowired
    BaseService baseService;
    @Autowired
    ShopService shopService;
    @Autowired
    OrderProductService orderProductService;
    @Autowired
    OrderService orderService;

    @RequestMapping("list")
    public String list(ModelMap map, String orderType, String wb){
        map.put("orderType", orderType);
        map.put("wb", wb);
        return "/order/list";
    }

    @RequestMapping("listData")
    @ResponseBody
    public JSONObject listData(HttpServletRequest request, PageInfo pageInfo,String orderType,String wb,
                               String startTime, String endTime, String status, String no, String memberAccount){
        Map<String,Object> map = new HashMap<>();
        Admin admin = (Admin) getCurrentUser(request);
        if("1".equals(admin.getType())){
            map.put("agentId", admin.getId());
        }
        if(!StringUtil.isNullOrEmpty(wb)) {
            map.put("wb", wb);
        }
        if(!StringUtil.isNullOrEmpty(orderType)) {
            map.put("orderType", orderType);
        }
        if(!StringUtil.isNullOrEmpty(startTime)) {
            map.put("startTime", DateUtil.getLongFormStrDate(startTime, DateUtil.pattern_10));
        }
        if(!StringUtil.isNullOrEmpty(endTime)) {
            map.put("endTime", DateUtil.getLongFormStrDate(endTime, DateUtil.pattern_10));
        }
        if(!StringUtil.isNullOrEmpty(status)) {
            map.put("status", status);
        }
        if(!StringUtil.isNullOrEmpty(no)) {
            map.put("no", no);
        }
        if(!StringUtil.isNullOrEmpty(memberAccount)) {
            map.put("memberAccount", memberAccount);
        }
        pageInfo.setParams(map);
        pageInfo = this.page(OrderDao.class,pageInfo);
        initList(pageInfo.getResult());
        return this.getPageResult(pageInfo);
    }

    @RequestMapping("find")
    public String find(ModelMap map, String id){
        Order order = this.baseService.findById(OrderDao.class, id);
        if(!StringUtil.isNullOrEmpty(order.getShopId())){
            Shop shop = this.baseService.findById(ShopDao.class, order.getShopId());
            order.setShopName(shop.getName());
        }
        order.setList(orderProductService.listByOrderId(order.getId()));
        map.put("data", order);
        return "/order/view";
    }
    
    //发货操作
    @RequestMapping("dispatch")
    @ResponseBody
    public JSONObject dispatch(String id, String expressName, String expressNo,HttpServletRequest request){
        Order order = this.baseService.findById(OrderDao.class, id);
        if(order == null) {
            return this.FmtResult(false,"订单不存在",null);
        }
        if(order.isWb()) {
            if(StringUtil.isNullOrEmpty(expressName)) {
                return this.FmtResult(false,"请选择快递员",null);
            }
            DispatchMember dm = this.baseService.findById(DispatchMemberDao.class, expressName);
            if(dm == null){
                return this.FmtResult(false,"快递员不存在",null);
            }
            //外卖
            order.setWbName(dm.getName());
           order.setWbTelephone(dm.getTelephone());
        }else {
            if (order == null || !"1".equals(order.getExpress()) || !OrderStatus.PAY.name().equals(order.getStatus())) {
                return this.FmtResult(false, "该订单不需要发货", null);
            }
            order.setExpressName(expressName);
            order.setExpressNo(expressNo);
        }
        order.setStatus(OrderStatus.DISPATCH.name());
        order.setDispatchTime(System.currentTimeMillis());
        //确认收货时限设置
        OrderLimitSet set = this.baseService.findById(OrderLimitSetDao.class,"");
        if(set != null && set.getSuccessLimit() > 0) {
            order.setLimitTime(DateUtil.addDays(System.currentTimeMillis(), set.getSuccessLimit()));
        }else{
            order.setLimitTime(0l);
        }
        this.baseService.update(OrderDao.class, order);
        this.saveLog("订单:" + order.getNo() + "进行发货操作", request);
        orderService.pushDispatchMessage(order);
        return this.FmtResult(true, "发货操作成功", null);
    }

    @RequestMapping("receive")
    @ResponseBody
    public JSONObject receive(String id, HttpServletRequest request){
        Order order = this.baseService.findById(OrderDao.class, id);
        if(!order.isWb() || !OrderStatus.PAY.name().equals(order.getStatus())) {
            return this.FmtResult(false,"订单不是外卖订单或不是付款状态",null);
        }
        order.setStatus(OrderStatus.RECEIVE.name());
        this.baseService.update(OrderDao.class, order);
        this.saveLog("订单:" + order.getNo() + "进行接单操作", request);
//        orderService.pushDispatchMessage(order);
        return this.FmtResult(true,"商家接收订单",null);
    }

    //关闭的订单删除
    @RequestMapping("delete")
    @ResponseBody
    public JSONObject delete(String id, HttpServletRequest request){
        Order order = this.baseService.findById(OrderDao.class,id);
        if(order != null && !OrderStatus.CLOSE.name().equals(order.getStatus())){
            return this.FmtResult(false,"订单不是关闭状态，不可删除",null);
        }
        this.baseService.delete(OrderProductDao.class, new String[]{id});
        this.baseService.delete(OrderDao.class, new String[]{id});
        this.saveLog("删除订单："+ order.getNo(),request);
        return this.FmtResult(true,"删除成功",null);
    }

    private void initList(List<Order> list){
        if(list == null || list.size() == 0) {
            return;
        }
        List<String> shopIds = new ArrayList<>();
        for(Order order : list){
            if(!StringUtil.isNullOrEmpty(order.getShopId())) {
                shopIds.add(order.getShopId());
            }
        }
        if(shopIds.size() > 0) {
            List<Shop> shops = shopService.listByIds(shopIds.toArray(new String[0]));
            Map<String,String> map = new HashMap<>();
            for(Shop shop : shops){
                map.put(shop.getId(), shop.getName());
            }
            for(Order order : list){
                order.setShopName(map.get(order.getShopId()));
            }
        }
    }

    //消费验证
    @RequestMapping("verify")
    public String verifyOrder(){
        return "/order/verify";
    }

    //根据验证码查询订单
    @RequestMapping("findByCode")
    public String findByCode(String code,ModelMap map){
        if(!StringUtil.isNullOrEmpty(code)) {
            code = code.replaceAll("-","");
            if(code.length() == 12){
                code =  code.substring(0,4) + "-" + code.substring(4,8) + "-" +code.substring(8);
                Order order = orderService.findByCode(code);
                if(order != null){
                    if (!StringUtil.isNullOrEmpty(order.getShopId())) {
                        Shop shop = this.baseService.findById(ShopDao.class, order.getShopId());
                        order.setShopName(shop.getName());
                    }
                    order.setList(orderProductService.listByOrderId(order.getId()));
                    map.put("data", order);
                }
            }
        }
        return "/order/order_verify";
    }

    //验证确认
    @RequestMapping("verifySuccess")
    @ResponseBody
    public JSONObject verifySuccess(String id,HttpServletRequest request){
        Order order = this.baseService.findById(OrderDao.class,id);
        if(order == null) {
            return this.FmtResult(false,"订单不存在",null);
        }
        if(!OrderStatus.PAY.name().equals(order.getStatus())){
            return this.FmtResult(false,"该订单状态为："+OrderStatus.valueOf(order.getStatus()).getName()+"，不能验证",null);
        }
        try {
            orderService.successOrder(order);
            this.saveLog("验证订单：" + order.getNo(),request);
            return this.FmtResult(true,"验证成功",null);
        }catch (Exception e){
            return this.FmtResult(false,"验证失败",null);
        }

    }



}
