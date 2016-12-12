package com.scht.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.scht.admin.bean.OrderStatus;
import com.scht.admin.bean.ProductTypeEnum;
import com.scht.admin.bean.Status;
import com.scht.admin.dao.*;
import com.scht.admin.entity.*;
import com.scht.admin.service.OrderService;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.*;
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
                //钱给商家
                addShopMoney(order);
                //给卖家推送APP消息
                shopRecord = PushRecord.createShopOrder(order.getShopId(),"订单完成",
                        "订单"+order.getNo()+"已确认完成",order.getId());
                this.baseMyBatisDao.insert(PushRecordDao.class, shopRecord);
            }else if(!StringUtil.isNullOrEmpty(order.getAgentId())){
                //查询是否是代理商，是代理商钱给代理商
                addAgentMoney(order);
            }
        }
        order.setLimitTime(0l);
        order.setStatus(OrderStatus.SUCCESS.name());
        order.setSuccessTime(System.currentTimeMillis());
        //给买家推送APP消息
        PushRecord memberRecord = PushRecord.createMemberOrder(order.getMemberId(),"订单完成","您的订单"+order.getNo()+"已确认完成",order.getId());
        this.baseMyBatisDao.insert(PushRecordDao.class, memberRecord);
        orderDao.update(order);
        //增加销量、
        updateProductSaleCount(order.getId());
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

    //货款给商家
    private void addShopMoney(Order order){
        //todo:
    }

    //代理商资金记录
    private void addAgentMoney(Order order){
        Admin admin = this.baseMyBatisDao.findById(AdminDao.class, order.getAgentId());
        if("1".equals(admin.getType())){
            //todo:代理商

        }
    }

    //订单关闭，库存返回
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
                        if(!ProductTypeEnum.EXTEND.name().equals(product.getProductType())) {
                            //更改商品库存
                            productDao.updateStock(product.getId(), map.get(product.getId()));
                        }
                    }
                }
            }
        }
    }

    //增加销量
    private void updateProductSaleCount(String orderId){
        List<OrderProduct> list = orderProductDao.listByOrderId(orderId);
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
                        productDao.updateSaleCount(product.getId(), map.get(product.getId()));
                    }
                }
            }
        }
    }

    /************  以下为ＡＰＰ接口  ******************/

    @Override
    public RetResult createOrder(String memberId, String productId, int amount, String remark,
                                 String userName, String telephone, String address, String express) {
        RetResult result = null;
        Order order = new Order();
        Member member = this.baseMyBatisDao.findById(MemberDao.class,memberId);
        if(member == null || !Status.NORMAL.name().equals(member.getStatus())) {
            result = new RetResult(RetResult.RetCode.User_Frozen);
            return result;
        }
        Product product = this.baseMyBatisDao.findById(ProductDao.class, productId);
        if(product == null || product.getStatus().equals(Status.FROZEN.name())) {
            result = new RetResult(RetResult.RetCode.Order_Product_Error);
            return result;
        }

        order.setMemberId(memberId);
        order.setMemberAccount(member.getAccount());
        order.setId(UUIDFactory.random());
        order.setNo(OrderUtil.createNo());
        order.setCreateTime(System.currentTimeMillis());
        if(ProductTypeEnum.EXTEND.name().equals(product.getProductType())) {
            order.setStatus(OrderStatus.PAY.name());
            order.setExpress("0");
            order.setPayTime(System.currentTimeMillis());
            order.setCode(OrderUtil.getUniqueCode());
        }else{
            if(product.getStock() < amount) {
                result = new RetResult(RetResult.RetCode.Order_Product_Error);
                return result;
            }
            order.setStatus(OrderStatus.CREATE.name());
            order.setExpress(express);
            order.setUserName(userName);
            order.setAddress(address);
            order.setTelephone(telephone);
            OrderLimitSet set = this.baseMyBatisDao.findById(OrderLimitSetDao.class,"");
            if(set != null && set.getPayLimit() > 0) {
                order.setLimitTime(DateUtil.addDays(System.currentTimeMillis(), set.getPayLimit()));
            }
        }
        order.setRemark(remark);
        order.setOrderType(product.getProductType());
        order.setAgentId(product.getAgentId());
        order.setShopId(product.getShopId());

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(UUIDFactory.random());
        orderProduct.setOrderId(order.getId());
        orderProduct.setProductId(productId);
        orderProduct.setProductName(product.getTitle());
        orderProduct.setProductImage(product.getIcon());
        orderProduct.setPrice(product.getPrice());
        orderProduct.setAmount(amount);
        orderProduct.setMoney(StringNumber.mul(product.getPrice(), amount + ""));

        order.setTotalMoney(orderProduct.getMoney());

        //保存
        this.baseMyBatisDao.insert(OrderProductDao.class, orderProduct);
        this.baseMyBatisDao.insert(OrderDao.class, order);
        //如果是商家，给商家发送APP消息
        if(!StringUtil.isNullOrEmpty(product.getShopId()) && OrderStatus.PAY.name().equals(order.getStatus())) {
            PushRecord record = PushRecord.createShopOrder(product.getShopId(),"新订单提醒",
                    "您好，您有一个新订单，商品“"+product.getTitle()+"”,会员账号“"+member.getAccount()+"”",order.getId());
            PushOrderThread thread = new PushOrderThread((PushSet)this.baseMyBatisDao.findById(PushSetDao.class,""), record);
            executor.execute(thread);
        }
        if(!ProductTypeEnum.EXTEND.name().equals(product.getProductType())) {
            //减少库存
            productDao.updateStock(productId, 0 - amount);
        }
        result = new RetResult(RetResult.RetCode.OK);
        List<OrderProduct> list = new ArrayList<>();
        list.add(orderProduct);
        order.setList(list);
        RetData data = new RetData(order);
        result.setData(data);
        return result;
    }

    @Override
    public RetResult closeOrder(String id) {
        RetResult result = null;
        Order order = this.baseMyBatisDao.findById(OrderDao.class, id);
        if(order == null || !OrderStatus.CREATE.name().equals(order.getStatus())){

            if(order == null) {
               result = new RetResult(RetResult.RetCode.Order_Not_exist);
            }else{
                result = new RetResult(RetResult.RetCode.Order_Status_Error);
            }
            return result;
        }
        order.setStatus(OrderStatus.CLOSE.name());
        order.setOverTime(System.currentTimeMillis());
        order.setLimitTime(0l);
        if(!ProductTypeEnum.EXTEND.name().equals(order.getOrderType())) {
            updateProductStock(new String[]{order.getId()});
        }
        this.baseMyBatisDao.update(OrderDao.class, order);
        return new RetResult(RetResult.RetCode.OK);
    }

    /**************    *******************/
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
                try {
                    if (!StringUtil.isNullOrEmpty(record.getMemberId())) {
                        //会员单个推送
                        PushAPPUtil.pushMessageByAlias(this.pushSet, record.getTitle(), record.getMemberId(), map, record.getTargetType());
                    } else if (!StringUtil.isNullOrEmpty(record.getShopId())) {
                        //商家单个推送
                        PushAPPUtil.pushMessageByAlias(this.pushSet, record.getTitle(), record.getShopId(), map, record.getTargetType());
                    } else {//标签推送
                        PushAPPUtil.pushMessageByTags(this.pushSet, record.getTitle(), map, new String[]{record.getTargetId()}, record.getTargetType());
                    }
                }catch (Exception e){

                }
            }
        }
    }
}
