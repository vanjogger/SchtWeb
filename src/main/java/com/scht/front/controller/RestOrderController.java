package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.bean.OrderStatus;
import com.scht.admin.dao.OrderDao;
import com.scht.admin.dao.OrderLimitSetDao;
import com.scht.admin.dao.OrderProductDao;
import com.scht.admin.dao.ShopDao;
import com.scht.admin.entity.Member;
import com.scht.admin.entity.Order;
import com.scht.admin.entity.OrderLimitSet;
import com.scht.admin.entity.Shop;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.OrderProductService;
import com.scht.admin.service.OrderService;
import com.scht.admin.service.ShopService;
import com.scht.common.BaseController;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.DateUtil;
import com.scht.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/12.
 */
@Controller
@RequestMapping("/rest/order/")
public class RestOrderController extends BaseController {

    @Autowired
    OrderService orderService;

    @Autowired
    BaseService baseService;

    @Autowired
    OrderProductService orderProductService;
    @Autowired
    ShopService shopService;
    //订单列表
    @RequestMapping(value = "list", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object list(@RequestParam(value = "pageNo",defaultValue = "1")int pageNo,
                       @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,String memberId,
                       String shopId, String status, String wb){
        if(StringUtil.isNullOrEmpty(memberId) && StringUtil.isNullOrEmpty(shopId)) {
            return JSON.toJSON(new RetResult(RetResult.RetCode.Illegal_Request));
        }
        Map<String,Object> map = new HashMap<>();
        map.put("start", (pageNo-1)*pageSize);
        map.put("limit", pageSize);
        if(!StringUtil.isNullOrEmpty(memberId)) {
            map.put("memberId", memberId);
        }
        if(!StringUtil.isNullOrEmpty(wb)) {
            map.put("wb", wb);
        }
        if(!StringUtil.isNullOrEmpty(shopId)) {
            map.put("shopId", shopId);
        }
        if(!StringUtil.isNullOrEmpty(status)) {
            map.put("status", status);
        }
        List<Order> list = this.baseService.searchByPage(OrderDao.class, map);
        initList(list);
        int count = this.baseService.count4Page(OrderDao.class, map);
        RetResult result = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(pageNo,pageSize, list,count);
        result.setData(data);
        return JSON.toJSON(result);
    }
    //订单详情
    @RequestMapping(value = "find",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object find(String id){
        RetResult result = null;
        try{
            Order order = this.baseService.findById(OrderDao.class, id);
            if(!StringUtil.isNullOrEmpty(order.getShopId())){
                Shop shop = this.baseService.findById(ShopDao.class, order.getShopId());
                order.setShopName(shop.getName());
                order.setShopIcon(shop.getIcon());
            }
            order.setList(orderProductService.listByOrderId(order.getId()));
            RetData data = new RetData(order);
            result = new RetResult(RetResult.RetCode.OK);
            result.setData(data);
        }catch (Exception e){
            result = new RetResult(RetResult.RetCode.System_Error);
        }
        return JSON.toJSON(result);
    }

    //接受订单
    @RequestMapping(value = "receive",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object receive(String id, HttpServletRequest request){
        RetResult result = null;
        Order order = this.baseService.findById(OrderDao.class, id);
        if(!order.isWb() || !OrderStatus.PAY.name().equals(order.getStatus())) {
            result = new RetResult(RetResult.RetCode.Illegal_Request);
            result.setResMsg("订单不是外卖订单或不是付款状态");
            return JSON.toJSON(result);
        }
        order.setStatus(OrderStatus.RECEIVE.name());
        orderService.pushReceiveMessage(order);
        this.baseService.update(OrderDao.class, order);
        result = new RetResult(RetResult.RetCode.OK);
        return JSON.toJSON(result);
    }
    //发货
//发货操作
    @RequestMapping(value = "dispatch", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object dispatch(String id, String expressName, String expressNo,HttpServletRequest request){
        Order order = this.baseService.findById(OrderDao.class, id);
        RetResult result = null;
        if(order == null) {
            result = new RetResult(RetResult.RetCode.Illegal_Request);
            result.setResMsg("订单不存在");
            return JSON.toJSON(result);
        }
        if(order.isWb()) {
            //外卖
            order.setWbTelephone(expressName);
        }else {
            if (order == null || !"1".equals(order.getExpress()) || !OrderStatus.PAY.name().equals(order.getStatus())) {
                result = new RetResult(RetResult.RetCode.Illegal_Request);
                result.setResMsg("该订单不需要发货");
                return JSON.toJSON(result);
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
        orderService.pushDispatchMessage(order);
        result = new RetResult(RetResult.RetCode.OK);
        return JSON.toJSON(result);
    }

    @RequestMapping(value = "createSaleOrder", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object createSaleOrder(Order order){
        try {
            order.setWb(true);
            RetResult result = this.orderService.createOrder(order);
            return JSON.toJSON(result);
        }catch (Exception e){
            e.printStackTrace();
            return JSON.toJSON(new RetResult(RetResult.RetCode.System_Error));
        }
    }
    /**
     * 创建订单
     * @param memberId
     * @param productId
     * @param amount
     * @param remark
     * @param userName
     * @param telephone
     * @param address
     * @param express
     * @return
     */
    @RequestMapping(value = "createOrder",  produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object createOrder(String memberId, String productId,@RequestParam(value = "amount", defaultValue = "1")int amount,
                              String remark,
                              String userName,String telephone,String address,String express){
        try {
            RetResult result = this.orderService.createOrder(memberId, productId, amount, remark, userName, telephone,
                    address, express);
            return JSON.toJSON(result);
        }catch (Exception e){
            e.printStackTrace();
            return JSON.toJSON(new RetResult(RetResult.RetCode.System_Error));
        }
    }
    //未付款订单关闭
    @RequestMapping(value = "closeOrder", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object closeOrder(String id){
        try {
            RetResult result = this.orderService.closeOrder(id);
            return JSON.toJSON(result);
        }catch (Exception e){
            e.printStackTrace();
            return JSON.toJSON(new RetResult(RetResult.RetCode.System_Error));
        }
    }
    //关闭订单删除
    @RequestMapping(value = "deleteOrder", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object deleteOrder(String id){
        RetResult result = null;
        try {
            Order order = this.baseService.findById(OrderDao.class, id);
            if (order != null && !OrderStatus.CLOSE.name().equals(order.getStatus())) {
                result = new RetResult(RetResult.RetCode.Order_Status_Error);
                return JSON.toJSON(result);
            }
            this.baseService.delete(OrderProductDao.class, new String[]{id});
            this.baseService.delete(OrderDao.class, new String[]{id});
            result = new RetResult(RetResult.RetCode.OK);
            return JSON.toJSON(result);
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.System_Error);
            return JSON.toJSON(result);
        }
    }

    //外卖订单支付
    @RequestMapping(value = "saleOrderPay", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object saleOrderPay(String orderId, String memberId, String payType, String couponRecordId, HttpServletRequest request){
        RetResult result = null;
        try{
            result = orderService.pay(orderId, memberId, payType,couponRecordId,request, getRequestIp(request));
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.System_Error);
        }
        return JSON.toJSON(result);
    }

    //订单支付
    @RequestMapping(value = "pay", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object orderPay(String orderId, String memberId, String payType,
                           boolean balance,HttpServletRequest request){
        RetResult result = null;
        try{
            result = orderService.pay(orderId, memberId, payType,balance,request, getRequestIp(request));
        }catch (Exception e){
            result = new RetResult(RetResult.RetCode.System_Error);
        }
        return JSON.toJSON(result);
    }

    //确认收货
    @RequestMapping(value = "successOrder", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object successOrder(String id){
        RetResult result = null;
        try {
            Order order = this.baseService.findById(OrderDao.class, id);
            if (order == null || !OrderStatus.DISPATCH.name().equals(order.getStatus())) {
                result = new RetResult(RetResult.RetCode.Operate_Error);
                return JSON.toJSON(result);
            }
            orderService.successOrder(order);
            result = new RetResult(RetResult.RetCode.OK);
            return JSON.toJSON(result);
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.System_Error);
            return JSON.toJSON(result);
        }
    }

    //消费验证 商家
    @RequestMapping(value = "verifyOrder", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object verifyOrder(String code,String shopId){
        RetResult result = new RetResult(RetResult.RetCode.Illegal_Request);
        try {
            if (!StringUtil.isNullOrEmpty(code)) {
                code = code.replaceAll("-", "");
                if (code.length() == 12) {
                    code = code.substring(0, 4) + "-" + code.substring(4, 8) + "-" + code.substring(8);
                    Order order = orderService.findByCode(code);
                    if (order != null && shopId.equals(order.getShopId())) {
                        order.setList(orderProductService.listByOrderId(order.getId()));
                        result = new RetResult(RetResult.RetCode.OK);
                        RetData data = new RetData(order);
                        result.setData(data);
                        return JSON.toJSON(result);
                    }else{
                        result = new RetResult(RetResult.RetCode.Order_Not_exist);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSON(result);
    }
    //消费验证提交
    @RequestMapping(value = "verifySuccess", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object verifySuccess(String id){
        RetResult result =  null;
        Order order = this.baseService.findById(OrderDao.class,id);
        if(order == null) {
            result = new RetResult(RetResult.RetCode.Order_Not_exist);
        }else  if(!OrderStatus.PAY.name().equals(order.getStatus())){
            result = new RetResult(RetResult.RetCode.Order_Status_Error);
        }else {
            try {
                orderService.successOrder(order);
                result = new RetResult(RetResult.RetCode.OK);
            } catch (Exception e) {
                result = new RetResult(RetResult.RetCode.System_Error);
            }
        }
        return JSON.toJSON(result);
    }

    private void initList(List<Order> list){
        if(list == null || list.size() == 0) return ;
        List<String> shopIds = new ArrayList<>();
        for(Order order : list) {
            shopIds.add(order.getShopId());
        }
        Map<String,Shop> shopMap = new HashMap<>();
        if(shopIds.size() > 0) {
            List<Shop> shopList = shopService.listByIds(shopIds.toArray(new String[0]));
            if(shopList != null && shopList.size() > 0) {
                for(Shop shop : shopList) {
                    shopMap.put(shop.getId(), shop);
                }
            }
        }
        for(Order order : list){
            order.setShopName(shopMap.get(order.getShopId()) != null ? shopMap.get(order.getShopId()).getName() : "自营店铺");
            order.setList(orderProductService.listByOrderId(order.getId()));
        }
    }
}
