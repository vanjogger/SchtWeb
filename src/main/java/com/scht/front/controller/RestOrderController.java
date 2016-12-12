package com.scht.front.controller;

import com.alibaba.fastjson.JSON;
import com.scht.admin.bean.OrderStatus;
import com.scht.admin.dao.OrderDao;
import com.scht.admin.dao.OrderProductDao;
import com.scht.admin.dao.ShopDao;
import com.scht.admin.entity.Order;
import com.scht.admin.entity.Shop;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.OrderProductService;
import com.scht.admin.service.OrderService;
import com.scht.common.BaseController;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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

    //订单支付
    public Object orderPay(){
        return null;
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
}
