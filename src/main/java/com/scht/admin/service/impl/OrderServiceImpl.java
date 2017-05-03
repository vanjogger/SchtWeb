package com.scht.admin.service.impl;

import com.scht.admin.bean.*;
import com.scht.admin.dao.*;
import com.scht.admin.entity.*;
import com.scht.admin.service.OrderService;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.*;
import com.scht.util.PayUtil.AliPayUtil;
import com.scht.util.PayUtil.WeixinPayUtil;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
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
    ShopMoneyDao shopMoneyDao;
    @Autowired
    AgentMoneyDao agentMoneyDao;

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
                updateProductStock(orderIds.toArray(new String[0]),true);
            }
        }

    }

    @Override
    public Integer countOrder(Map params) {
        return this.orderDao.countOrder(params);
    }

    @Override
    public void pushDispatchMessage(Order order) {
        List<OrderProduct> product = orderProductDao.listByOrderId(order.getId());
        String message = "您好，您购买的商品“" + product.get(0).getProductName() + "”";
        if(order.isWb()) {
            message += "由快递员配送，联系电话：" + order.getWbTelephone() +",请注意查收！";
        }else {
            if (!StringUtil.isNullOrEmpty(order.getExpressName())) {
                message += "已由快递公司" + order.getExpressName() + "承接配送。";
                if (!StringUtil.isNullOrEmpty(order.getExpressNo())) {
                    message += "快递单号：" + order.getExpressNo() + "。请注意查收";
                }
            } else {
                message += "已发货，请注意查收！";
            }
        }
        PushRecord record = PushRecord.createMemberOrder(order.getMemberId(), "订单发货提醒",message, order.getId());
        this.baseMyBatisDao.insert(PushRecordDao.class, record);
        PushOrderThread thread = new PushOrderThread((PushSet) this.baseMyBatisDao.findById(PushSetDao.class, ""), record);
        executor.execute(thread);
    }

    //货款给商家
    private void addShopMoney(Order order){
        //todo:
        List<ShopMoney> moneys = this.shopMoneyDao.listByShopId(order.getShopId());
        ShopMoney money = null;
        String beforeAmount = "0";
        if(StringUtil.isNotEmpty(moneys)){
            money = moneys.get(0);
            beforeAmount = money.getAvailAmount();
            money.setAvailAmount(StringNumber.add(money.getAvailAmount(),order.getTotalMoney()));
            money.setTotalAmount(StringNumber.add(money.getTotalAmount(), order.getTotalMoney()));
            this.baseMyBatisDao.update(ShopMoneyDao.class,money);
        }else{
            money = new ShopMoney();
            money.setId(UUIDFactory.random());
            money.setShopId(order.getShopId());
            money.setShopName(order.getShopName());
            money.setAvailAmount(order.getTotalMoney());
            money.setFrozenAmount("0");
            money.setTotalAmount(order.getTotalMoney());
            this.baseMyBatisDao.insert(ShopMoneyDao.class,money);
        }
        //保存商家资金流水
        ShopFlow flow = new ShopFlow();
        flow.setId(UUIDFactory.random());
        flow.setShopId(order.getShopId());
        flow.setShopName(order.getShopName());
        flow.setBeforeAmount(beforeAmount);
        flow.setAmount(order.getTotalMoney());
        flow.setAfterAmount(money.getAvailAmount());
        flow.setCreateTime(new Date().getTime());
        flow.setType(AmountType.OrderFee.name());
        this.baseMyBatisDao.insert(ShopFlowDao.class, flow);
    }

    //代理商资金记录
    private void addAgentMoney(Order order){
        Admin admin = this.baseMyBatisDao.findById(AdminDao.class, order.getAgentId());
        if("1".equals(admin.getType())){
            //todo:代理商
            AgentMoney money = this.agentMoneyDao.findByAgentId(order.getAgentId());
            String beforeAmount = "0";
            if(money!=null){
                beforeAmount = money.getAvailAmount();
                money.setAvailAmount(StringNumber.add(money.getAvailAmount(),order.getTotalMoney()));
                money.setTotalAmount(StringNumber.add(money.getTotalAmount(), order.getTotalMoney()));
                this.baseMyBatisDao.update(AgentMoneyDao.class,money);
            }else{
                money = new AgentMoney();
                money.setAvailAmount(order.getTotalMoney());
                money.setFrozenAmount("0");
                money.setTotalAmount(order.getTotalMoney());
                money.setAgentId(admin.getId());
                money.setAgentName(admin.getLoginName());
                money.setId(UUIDFactory.random());
                this.baseMyBatisDao.insert(AgentMoneyDao.class,money);
            }
            //保存资金流水
            AgentFlow flow = new AgentFlow();
            flow.setId(UUIDFactory.random());
            flow.setAgentId(admin.getId());
            flow.setAgentAccount(admin.getLoginName());
            flow.setBeforeAmount(beforeAmount);
            flow.setAmount(order.getTotalMoney());
            flow.setAfterAmount(money.getAvailAmount());
            flow.setType(AmountType.OrderFee.name());
            flow.setCreateTime(new Date().getTime());
            this.baseMyBatisDao.insert(AgentFlowDao.class,flow);
        }
    }

    //订单关闭，库存返回
    private void updateProductStock(String[] orderIds, boolean flag){
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
                            if(flag) {
                                //更改商品库存
                                productDao.updateStock(product.getId(), map.get(product.getId()));
                            }else{
                                productDao.updateStock(product.getId(), 0- map.get(product.getId()));
                            }
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

    //外卖
    @Override
    public RetResult createOrder(Order order) {
        RetResult result = null;
        Member member = this.baseMyBatisDao.findById(MemberDao.class,order.getMemberId());
        if(member == null || !Status.NORMAL.name().equals(member.getStatus())) {
            result = new RetResult(RetResult.RetCode.User_Frozen);
            return result;
        }
        List<OrderProduct> list = order.getList();
        if(list == null || list.size() == 0) {
            result = new RetResult(RetResult.RetCode.BadRequest);
            return result;
        }
        order.setId(UUIDFactory.random());
        List<String> productIds = new ArrayList<>();
        //查询商品
        for(OrderProduct product : list) {
            productIds.add(product.getProductId());
        }
        List<Product> products = productDao.listByIds(productIds.toArray(new String[0]));
        Map<String,Product> map = new HashMap<>();
        for(Product product : products) {
            map.put(product.getId(), product);
        }
        //组装
        Product temp = null;
        String shopId = null;String agentId = null;
        String totalMoney = "0";
        for(int i=0; i < list.size(); i++) {
            OrderProduct product = list.get(i);
            temp = map.get(product.getProductId());
            if(i==0) {
                shopId = temp.getShopId();
                agentId = temp.getAgentId();
            }
            product.setId(UUIDFactory.random());
            product.setOrderId(order.getId());
            product.setProductName(temp.getTitle());
            product.setProductImage(temp.getIcon());
            product.setPrice(temp.getPrice());
            product.setMoney(StringNumber.mul(product.getPrice(), (product.getAmount() == 0 ? 1 : product.getAmount()) + ""));
          totalMoney = StringNumber.add(totalMoney, product.getMoney());
        }
        order.setShopId(shopId);
        order.setAgentId(agentId);
         order.setTotalMoney(totalMoney);
        order.setBalance("0");
        order.setRealMoney(totalMoney);
        order.setNo(OrderUtil.createNo());
        order.setOrderType(ProductTypeEnum.NORMAL.name());
        order.setMemberId(member.getId());
        order.setMemberAccount(member.getAccount());
        order.setCreateTime(System.currentTimeMillis());
        OrderLimitSet set = this.baseMyBatisDao.findById(OrderLimitSetDao.class,"");
        if(set != null && set.getPayLimit() > 0) {
            order.setLimitTime(DateUtil.addDays(System.currentTimeMillis(), set.getPayLimit()));
        }
        order.setStatus(OrderStatus.CREATE.name());
        if(StringUtil.isNullOrEmpty(order.getExpress())) {
            order.setExpress("1"); //外卖，发货
        }
        order.setMemberAssess("0");
        order.setShopAssess("0");
        //保存
        this.baseMyBatisDao.saveBatch(OrderProductDao.class, list);
        this.baseMyBatisDao.insert(OrderDao.class, order);
        result = new RetResult(RetResult.RetCode.OK);
        RetData data = new RetData(order);
        result.setData(data);
        return result;
    }
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
        order.setMemberAssess("0");
        order.setShopAssess("0");
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
            order.setExpress(express);
            order.setUserName(userName);
            order.setAddress(address);
            order.setTelephone(telephone);
            //todo：商家的五折商品也不需要支付
            if(!StringUtil.isNullOrEmpty(product.getShopId()) && ProductTypeEnum.DISCOUNT.name().equals(product.getProductType())){
                order.setStatus(OrderStatus.PAY.name());
                if("0".equals(order.getExpress())){
                    order.setCode(OrderUtil.getUniqueCode());
                }
            }else{
                order.setStatus(OrderStatus.CREATE.name());
                OrderLimitSet set = this.baseMyBatisDao.findById(OrderLimitSetDao.class,"");
                if(set != null && set.getPayLimit() > 0) {
                    order.setLimitTime(DateUtil.addDays(System.currentTimeMillis(), set.getPayLimit()));
                }
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
        order.setBalance("0");
        order.setRealMoney(order.getTotalMoney());
        if(StringNumber.compareTo(order.getTotalMoney(),"0") <=0) {
            order.setStatus(OrderStatus.PAY.name());
            order.setPayTime(System.currentTimeMillis());
            if("0".equals(order.getExpress())){
                order.setCode(OrderUtil.getUniqueCode());
            }
        }

        //保存
        this.baseMyBatisDao.insert(OrderProductDao.class, orderProduct);
        this.baseMyBatisDao.insert(OrderDao.class, order);
        //如果是商家，给商家发送APP消息
        if(!StringUtil.isNullOrEmpty(product.getShopId()) && OrderStatus.PAY.name().equals(order.getStatus())) {
            PushRecord record = PushRecord.createShopOrder(product.getShopId(),"新订单提醒",
                    "您好，您有一个新订单，商品“"+product.getTitle()+"”,会员账号“"+member.getAccount()+"”",order.getId());
            this.baseMyBatisDao.insert(PushRecordDao.class,record);
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
            updateProductStock(new String[]{order.getId()}, true);
        }
        this.baseMyBatisDao.update(OrderDao.class, order);
        return new RetResult(RetResult.RetCode.OK);
    }

    @Override
    public RetResult list(String shopId,String status, int pageNo, int pageSize) {
        RetResult result = null;
        try{
            if(pageNo<1)
                pageNo = 1;
            List<Order> list = this.orderDao.list(shopId,status,(pageNo-1)*pageSize,pageSize);
            Integer count = this.orderDao.count(shopId,status);
            if(StringUtil.isNotEmpty(list)){
                for(Order order:list){
                    List<OrderProduct> data = this.orderProductDao.listByOrderId(order.getId());
                    order.setList(data);
                }
            }
            RetData data = new RetData(pageNo,pageSize,list,count);
            result = new RetResult(RetResult.RetCode.OK);
            result.setData(data);
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }

    @Override
    public void payBack(OrderPayRecord r) {
        OrderPayRecord payRecord = this.baseMyBatisDao.findById(OrderPayRecordDao.class, r.getId());
        if(PayStatus.SUCCESS.name().equals(payRecord.getStatus())){
            return;
        }
        r.setPayTime(System.currentTimeMillis());
        this.baseMyBatisDao.update(OrderPayRecord.class, r);
        if(PayStatus.SUCCESS.name().equals(r.getStatus())) {
            //支付成功
            Order order = this.baseMyBatisDao.findById(OrderDao.class, r.getOrderId());
            if(OrderStatus.PAY.name().equals(order.getStatus())) {
                return;
            }
            order.setPayTime(r.getPayTime());
            order.setBalance(r.getBalance());
            order.setRealMoney(r.getMoney());
            order.setPayType(r.getPayType());
            payOver(order);
        }
    }

    @Override
    public RetResult pay(String orderId, String memberId, String payType, String couponId, HttpServletRequest request, String ip) throws IOException, DocumentException {
        RetResult result = null;
        Member member = this.baseMyBatisDao.findById(MemberDao.class, memberId);
        if(member == null) {
            result = new RetResult(RetResult.RetCode.User_Not_Exist);
        }else if( Status.FROZEN.name().equals(member.getStatus())) {
            result = new RetResult(RetResult.RetCode.User_Frozen);
        }
        if(result != null) return result;
        Order order = this.baseMyBatisDao.findById(OrderDao.class, orderId);
        if(order == null) {
            result = new RetResult(RetResult.RetCode.Order_Not_exist);
        }else if(!OrderStatus.CREATE.name().equals(order.getStatus())) {
            result = new RetResult(RetResult.RetCode.Order_Status_Error);
        }
        if(result != null) {
            return result;
        }
        String payMoney = order.getRealMoney();
        String useBalance = "0";
         if(StringUtil.isNotNull(couponId)) {
             CouponRecord couponRecord = this.baseMyBatisDao.findById(CouponRecordDao.class, couponId);
             if(couponRecord == null || !couponRecord.getMemberId().equals(memberId)) {
                 result = new RetResult(RetResult.RetCode.Illegal_Request);
                 result.setResMsg("优惠券不存在");
             }else if(couponRecord.isStatus()) {
                 result = new RetResult(RetResult.RetCode.Illegal_Request);
                 result.setResMsg("优惠券已使用");
             }
             order.setCouponId(couponId);
             if(StringNumber.compareTo(payMoney, couponRecord.getCouponMoney()) > 0) {
                payMoney = StringNumber.sub(payMoney, couponRecord.getCouponMoney());
             }else{
                 payMoney = "0";
             }
         }
        order.setRealMoney(payMoney);
        if(StringNumber.compareTo(payMoney, "0") > 0) {
            if(StringUtil.isNullOrEmpty(payType) || PayType.valueOf(payType) == null){
                result = new RetResult(RetResult.RetCode.Illegal_Request);
                return result;
            }
        }else{
            //优惠券支付完成
            payType = PayType.COUPON.name();
            order.setPayType(payType);
            order.setPayTime(System.currentTimeMillis());
            //完成订单
//            order.setBalance(useBalance);
            payOver(order);
            result = new RetResult(RetResult.RetCode.OK);
            return result;
        }

        OrderPayRecord payRecord = new OrderPayRecord();
        payRecord.setId(UUIDFactory.random());
        payRecord.setNo(OrderUtil.createNo());
        payRecord.setOrderId(order.getId());
        payRecord.setOrderNo(order.getNo());
        payRecord.setMemberId(memberId);
        payRecord.setTotalMoney(order.getTotalMoney());
        payRecord.setBalance("0");
        payRecord.setMoney(payMoney);
        payRecord.setCouponId(couponId);
        payRecord.setPayType(payType);
        payRecord.setStatus(PayStatus.WAIT.name());
        payRecord.setCreateTime(System.currentTimeMillis());
        this.baseMyBatisDao.insert(OrderPayRecordDao.class, payRecord);
        RetData data = null;
        String domain = "http://" + request.getServerName() + ":" + request.getServerPort();
        if(PayType.ALIPAY.name().equals(payType)) {
            AliPaySet aliPaySet = this.baseMyBatisDao.findById(AliPaySetDao.class,"");
            Map<String,String> map = AliPayUtil.createAppMap(payRecord, aliPaySet, domain + AliPayUtil.NOTICE_URL);
            data = new RetData(map);
        }else if(PayType.WEIXIN.name().equals(payType)) {
            WeixinPaySet weixinPaySet = this.baseMyBatisDao.findById(WeixinPaySetDao.class,"");
            Map<String,String> map = WeixinPayUtil.submitJsonForApp(request, ip, domain + WeixinPayUtil.NOTICE_URL,payRecord,weixinPaySet);
            data = new RetData(map);
        }
        if(data != null){
            result = new RetResult(RetResult.RetCode.OK);
            result.setData(data);
        }else{
            result = new RetResult(RetResult.RetCode.System_Error);
        }
        return result;
    }

    @Override
    public RetResult pay(String orderId, String memberId, String payType, boolean balance, HttpServletRequest request, String ip) throws IOException, DocumentException {
        RetResult result = null;
        Member member = this.baseMyBatisDao.findById(MemberDao.class, memberId);
        if(member == null) {
            result = new RetResult(RetResult.RetCode.User_Not_Exist);
        }else if( Status.FROZEN.name().equals(member.getStatus())) {
            result = new RetResult(RetResult.RetCode.User_Frozen);
        }
        if(result != null) return result;
        Order order = this.baseMyBatisDao.findById(OrderDao.class, orderId);
        if(order == null) {
            result = new RetResult(RetResult.RetCode.Order_Not_exist);
        }else if(!OrderStatus.CREATE.name().equals(order.getStatus())) {
            result = new RetResult(RetResult.RetCode.Order_Status_Error);
        }
        if(result != null) {
            return result;
        }
        String payMoney = order.getRealMoney();
        String useBalance = "0";
        if(balance) {
            //使用余额，判断余额
            MemberMoney memberMoney = this.baseMyBatisDao.findById(MemberMoneyDao.class, memberId);
            if(StringNumber.compareTo(memberMoney.getMoney(), payMoney) > 0) {
                payMoney = "0";
                useBalance = order.getRealMoney();
            }else{
                payMoney = StringNumber.sub(payMoney, memberMoney.getMoney());
                useBalance = memberMoney.getMoney();
            }
        }
        if(StringNumber.compareTo(payMoney, "0") > 0) {
            if(StringUtil.isNullOrEmpty(payType) || PayType.valueOf(payType) == null){
                result = new RetResult(RetResult.RetCode.Illegal_Request);
                return result;
            }
        }else{
            //余额支付完成
            payType = PayType.BALANCE.name();
            order.setPayType(payType);
            order.setPayTime(System.currentTimeMillis());
            //完成订单
            order.setBalance(useBalance);
            order.setRealMoney(payMoney);
            payOver(order);
            result = new RetResult(RetResult.RetCode.OK);
            return result;
        }

        OrderPayRecord payRecord = new OrderPayRecord();
        payRecord.setId(UUIDFactory.random());
        payRecord.setNo(OrderUtil.createNo());
        payRecord.setOrderId(order.getId());
        payRecord.setOrderNo(order.getNo());
        payRecord.setMemberId(memberId);
        payRecord.setTotalMoney(order.getTotalMoney());
        payRecord.setBalance(useBalance);
        payRecord.setMoney(payMoney);
        payRecord.setPayType(payType);
        payRecord.setStatus(PayStatus.WAIT.name());
        payRecord.setCreateTime(System.currentTimeMillis());
        this.baseMyBatisDao.insert(OrderPayRecordDao.class, payRecord);
        RetData data = null;
        String domain = "http://" + request.getServerName() + ":" + request.getServerPort();
        if(PayType.ALIPAY.name().equals(payType)) {
            AliPaySet aliPaySet = this.baseMyBatisDao.findById(AliPaySetDao.class,"");
           Map<String,String> map = AliPayUtil.createAppMap(payRecord, aliPaySet, domain + AliPayUtil.NOTICE_URL);
            data = new RetData(map);
        }else if(PayType.WEIXIN.name().equals(payType)) {
            WeixinPaySet weixinPaySet = this.baseMyBatisDao.findById(WeixinPaySetDao.class,"");
            Map<String,String> map = WeixinPayUtil.submitJsonForApp(request, ip, domain + WeixinPayUtil.NOTICE_URL,payRecord,weixinPaySet);
            data = new RetData(map);
        }
        if(data != null){
            result = new RetResult(RetResult.RetCode.OK);
            result.setData(data);
        }else{
            result = new RetResult(RetResult.RetCode.System_Error);
        }
        return result;
    }

    private void payOver(Order order){
        order.setStatus(OrderStatus.PAY.name());
        order.setLimitTime(0l);
        if("0".equals(order.getExpress())){
            order.setCode(OrderUtil.getUniqueCode());
        }
        List<OrderProduct> product = orderProductDao.listByOrderId(order.getId());
        if(!order.isWb()) {
            //减库存 todo：
            updateProductStock(new String[]{order.getId()}, false);
        }
        //订单支付完成
        this.baseMyBatisDao.update(OrderDao.class, order);
        if(!StringUtil.isNullOrEmpty(order.getShopId())) {//给商家推送消息
            PushRecord record = PushRecord.createShopOrder(order.getShopId(), "新订单提醒",
                    "您好，您有一个新订单，商品“" + product.get(0).getProductName() + "”,会员账号“" + order.getMemberAccount() + "”", order.getId());
            this.baseMyBatisDao.insert(PushRecordDao.class,record);
            PushOrderThread thread = new PushOrderThread((PushSet) this.baseMyBatisDao.findById(PushSetDao.class, ""), record);
            executor.execute(thread);
        }
        if(StringUtil.isNotNull(order.getCouponId())) {
            //使用了优惠券
            CouponRecord record = this.baseMyBatisDao.findById(CouponRecordDao.class, order.getCouponId());
            record.setUseTime(System.currentTimeMillis());
            record.setStatus(true);
            this.baseMyBatisDao.update(CouponRecordDao.class, record);
        }
        //如果使用了余额
        if(StringNumber.compareTo(order.getBalance(),"0") > 0) {
            //会员资金流水
            MemberMoney memberMoney = this.baseMyBatisDao.findById(MemberMoneyDao.class, order.getMemberId());
            String beforeMoney = memberMoney.getMoney();
            memberMoney.setMoney(StringNumber.sub(memberMoney.getMoney(), order.getBalance()));
            //资金流水
            MemberFlow flow = new MemberFlow();
            flow.setId(UUIDFactory.random());
            flow.setMemberId(memberMoney.getMemberId());
            flow.setMemberAccount(((Member)this.baseMyBatisDao.findById(MemberDao.class, memberMoney.getMemberId())).getAccount());
            flow.setType(MemberFlowType.ORDER.name());
            flow.setBeforeAmount(beforeMoney);
            flow.setAmount("-" + order.getBalance());
            flow.setAfterAmount(memberMoney.getMoney());
            flow.setCreateTime(System.currentTimeMillis());
            this.baseMyBatisDao.insert(MemberFlowDao.class, flow);
            this.baseMyBatisDao.update(MemberMoneyDao.class, memberMoney);
        }

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
