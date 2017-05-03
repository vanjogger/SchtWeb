package com.scht.admin.service;

import com.scht.admin.entity.Order;
import com.scht.admin.entity.OrderPayRecord;
import com.scht.front.bean.RetResult;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface OrderService {

    Order findByCode(String code);
    //验证成功订单，订单完成
    void successOrder(Order id);

    //定时任务
    void updateTask();

    Integer countOrder(Map params);
    //给买家推送发货消息
    void pushDispatchMessage(Order order);

    /** ******* APP 接口 ******* **/

    /**
     * 创建订单
     * @return
     */
    RetResult createOrder(String memberId, String productId, int amount, String remark, String userName, String telephone, String address, String express);
    RetResult createOrder(Order order);
    /**
     * 关闭订单
     * @param id
     * @return
     */
    RetResult closeOrder(String id);

    RetResult list(String shopId,String status, int pageNo, int pageSize);

    RetResult pay(String orderId, String memberId, String payType, boolean balance, HttpServletRequest request, String ip) throws IOException, DocumentException;
    RetResult pay(String orderId, String memberId, String payType, String couponId, HttpServletRequest request, String ip) throws IOException, DocumentException;

    //支付返回
    void payBack(OrderPayRecord record);

}
