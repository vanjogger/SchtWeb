package com.scht.admin.service.impl;

import com.scht.admin.bean.OrderStatus;
import com.scht.admin.bean.ProductTypeEnum;
import com.scht.admin.dao.*;
import com.scht.admin.entity.*;
import com.scht.admin.service.OrderService;
import com.scht.util.PushAPPUtil;
import com.scht.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/12/1.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderProductDao orderProductDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    BaseMyBatisDao baseMyBatisDao;

    private ExecutorService  executor = Executors.newCachedThreadPool();

    @Override
    public Order findByCode(String code) {
        return orderDao.findByCode(code);
    }

    @Override
    public void successOrder(Order order) {
        if(!OrderStatus.PAY.name().equals(order.getStatus()) && !OrderStatus.DISPATCH.name().equals(order.getStatus()))
            return;
        PushRecord shopRecord = null;
        if(!ProductTypeEnum.EXTEND.name().equals(order.getOrderType())) { //不是推广订单
            if(!StringUtil.isNullOrEmpty(order.getShopId())){
                //钱给商家 todo:
                //给卖家推送APP消息
                shopRecord = PushRecord.createShopOrder(order.getShopId(),"订单完成",
                        "订单已确认完成，请查看详情",order.getId());
            }else if(!StringUtil.isNullOrEmpty(order.getAgentId())){
                //todo:查询是否是代理商，是代理商钱给代理商
            }
        }
        order.setLimitTime(0l);
        order.setStatus(OrderStatus.SUCCESS.name());
        order.setSuccessTime(System.currentTimeMillis());
        //给买家推送APP消息
        PushRecord memberRecord = PushRecord.createMemberOrder(order.getMemberId(),"订单完成","您的订单已确认完成，点击查看",order.getId());
        orderDao.update(order);
        //增加销量、todo；
        //开启线程
        PushOrderThread thread = new PushOrderThread((PushSet)this.baseMyBatisDao.findById(PushSetDao.class,""), memberRecord,shopRecord);
        executor.execute(thread);
        //new Thread(thread).start();
    }

    /**
     * 订单定时任务
     */
    @Override
    public void updateTask() {
        long time = System.currentTimeMillis();
        //查询limitTime < time 的订单，并且订单状态是create / dispatch的
        List<Order> list = orderDao.listForTask(time);
        if(list != null && list.size() > 0) {
            List<String> orderIds = new ArrayList<>();
            for(Order order : list){
                if(OrderStatus.CREATE.name().equals(order.getStatus())){
                    //待支付的，关闭
                    order.setStatus(OrderStatus.CLOSE.name());
                    order.setOverTime(System.currentTimeMillis());
                    //：商品库存返回
                    orderIds.add(order.getId());
                    orderDao.update(order);
                }else if(OrderStatus.DISPATCH.name().equals(order.getStatus())) {

                    successOrder(order);
                }
            }
            if(orderIds.size() > 0) {
                updateProductStock(orderIds.toArray(new String[0]));
            }
        }

    }

    @Override
    public Integer countOrder(Map params) {
        return this.orderDao.countOrder(params);
    }

    private void updateProductStock(String[] orderIds){
        List<OrderProduct> list = orderProductDao.listByOrderIds(orderIds);
        if(list != null && list.size() > 0) {
            Map<String,Integer> map = new HashMap<>();
            List<String> productIds = new ArrayList<>();
            int count = 0;
            for(OrderProduct orderProduct : list){
                productIds.add(orderProduct.getProductId());
                if(map.get(orderProduct.getProductId()) != null) {
                    count = map.get(orderProduct.getProductId());
                }else{
                    count = 0;
                }
                count += orderProduct.getAmount();
                map.put(orderProduct.getProductId(), count);
            }
            //查询商品
            List<Product> products = productDao.listByIds(productIds.toArray(new String[0]));
            if(products != null && products.size() > 0){
                for(Product product : products) {
                    if(map.get(product.getId()) != null) {
                        //更改商品库存
                        productDao.updateStock(product.getId(), map.get(product.getId()));
                    }
                }
            }
        }
    }


    //推送APP消息线程
    class PushOrderThread implements Runnable {

        private PushRecord[] records;
        private PushSet pushSet;
        PushOrderThread(PushSet set,PushRecord... record){
            this.pushSet = set;
            this.records = record;
        }
        @Override
        public void run() {
            if(this.records == null || this.records.length == 0) {
                return;
            }
            Map<String,String> map = new HashMap<>();
            for(PushRecord record : records) {
                map.put("title", record.getTitle());
                map.put("dataId", record.getTargetId());
                map.put("type", record.getType());
                map.put("content", record.getContent());
                if(!StringUtil.isNullOrEmpty(record.getMemberId())) {
                    //会员单个推送
                    PushAPPUtil.pushMessageByAlias(this.pushSet,record.getTitle(),record.getMemberId(),map,record.getTargetType());
                }else if(!StringUtil.isNullOrEmpty(record.getShopId())) {
                    //商家单个推送
                    PushAPPUtil.pushMessageByAlias(this.pushSet, record.getTitle(), record.getShopId(), map, record.getTargetType());
                }else{//标签推送
                    PushAPPUtil.pushMessageByTags(this.pushSet, record.getTitle(), map,new String[]{record.getTargetId()}, record.getTargetType());
                }
            }
        }
    }
}
