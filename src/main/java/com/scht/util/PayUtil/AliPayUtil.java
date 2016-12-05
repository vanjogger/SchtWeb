package com.scht.util.PayUtil;

import com.scht.admin.entity.AliPaySet;
import com.scht.admin.entity.OrderPayRecord;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝支付
 * Created by Administrator on 2016/12/5.
 */
public class AliPayUtil {
    // 签名方式 不需修改
    public static String sign_type = "RSA";
    public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    //APP 支付
    public static Map<String, String> createAppMap(OrderPayRecord record,AliPaySet set, String noticeUrl) {
        Map<String,String> map = new HashMap<>();
        map.put("partner", set.getMchNo());
        map.put("seller", set.getAccount());
        map.put("rsaPriKey", set.getRsaKey());
        map.put("rsaPubKey", ali_public_key);
        map.put("notifyURL", noticeUrl);
        map.put("subject", record.getNo());
        map.put("body", record.getNo());
        map.put("amount", record.getMoney());
        map.put("tradeNO", record.getNo());
        return map;
    }
}
