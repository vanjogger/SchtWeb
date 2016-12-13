package com.scht.front.controller;

import com.scht.admin.bean.PayStatus;
import com.scht.admin.dao.AliPaySetDao;
import com.scht.admin.dao.WeixinPaySetDao;
import com.scht.admin.entity.AliPaySet;
import com.scht.admin.entity.OrderPayRecord;
import com.scht.admin.entity.WeixinPaySet;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.OrderPayRecordService;
import com.scht.admin.service.OrderService;
import com.scht.common.BaseController;
import com.scht.util.PayUtil.AliPayUtil;
import com.scht.util.PayUtil.TyNoticeSj;
import com.scht.util.PayUtil.WeixinPayUtil;
import com.scht.util.StringNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/13.
 */
@Controller
@RequestMapping("/rest/payback/")
public class RestPayBackController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(RestPayBackController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    OrderPayRecordService orderPayRecordService;

    @Autowired
    BaseService baseService;

    //微信返回
    public String weixinPay(HttpServletRequest request){
        String respMessage = "";
        try {
        WeixinPaySet set = this.baseService.findById(WeixinPaySetDao.class,"");
        Object[] objects = (Object[]) WeixinPayUtil.tyZfNotify(request, set.getPayKey());
        TyNoticeSj returnTyNoticeSj = null;
        String orderId = "";
        returnTyNoticeSj = (TyNoticeSj) objects[0];
        respMessage = (String) objects[1];
        orderId = (String) objects[2];
        OrderPayRecord record = orderPayRecordService.findByNo(orderId);
        if(record == null) {
            return "fail";
        }
        logger.info("====money test ====" + returnTyNoticeSj.getTotal_fee());
        int readMoney = Integer.valueOf(StringNumber.formatIgnoreOfZero(StringNumber.mul(record.getMoney(), "100")));
        if(readMoney != returnTyNoticeSj.getTotal_fee()) {
            return "fail";
        }
            if ("SUCCESS".equals(returnTyNoticeSj.getReturn_code())) {  //
                System.out.println("微信支付成功:" + orderId);
                record.setTradeNo(returnTyNoticeSj.getTransaction_id());
                record.setBuyer(returnTyNoticeSj.getOpenid());
                record.setStatus(PayStatus.SUCCESS.name());
                orderService.payBack(record);
            }else{
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }

    //支付宝返回
    @RequestMapping(value = "alipay", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String alipay(HttpServletRequest request){
        Map<String, String> params = findParams(request);
        for(String key : params.keySet()) {
            System.out.println(key + "::" + params.get(key));
            logger.info(key + "::" + params.get(key));
        }
        String temp = params.get("out_trade_no");            //获取订单号
        String trade_status = params.get("trade_status");        //交易状态
        OrderPayRecord record = orderPayRecordService.findByNo(temp);
        if(record == null) {
            return "fail";
        }
        logger.info("====money test ====" + params.get("total_fee"));
        if(StringNumber.compareTo(StringNumber.formatPrecisionOfTwo(record.getMoney()), params.get("total_fee")) != 0) {
            return "fail";
        }
//        if(!order.getRealMoney().equals(params.get("total_fee"))) {
//            return "fail";
//        }
        AliPaySet set = this.baseService.findById(AliPaySetDao.class, "");
        if (AliPayUtil.verifyWap(params, set)) {//验证成功
            logger.info("verify code success");
            logger.info("trade_status::" + trade_status);
            logger.info("order Id ::" + temp);
            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                try {
                    record.setTradeNo(params.get("trade_no"));
                    record.setBuyer(params.get("buyer_logon_id"));
                    record.setStatus(PayStatus.SUCCESS.name());
                    orderService.payBack(record);
//                    orderService.orderPay(order_no, extra_common_param, PayType.ALIPAY_DIRECT);
                    return "success";    //请不要修改或删除——
                } catch (Exception e) {
                    return "fail";
                }
            }else {
                return "fail";
            }
        } else {
            return "fail";
        }
    }

    private Map<String, String> findParams(HttpServletRequest request) {
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
            params.put(name, valueStr);
        }
        return params;
    }
}
