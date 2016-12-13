package com.scht.util.PayUtil;

import com.scht.admin.entity.AliPaySet;
import com.scht.admin.entity.OrderPayRecord;
import com.scht.util.StringNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * 支付宝支付
 * Created by Administrator on 2016/12/5.
 */
public class AliPayUtil {

    private static Logger logger = LoggerFactory.getLogger(AliPayUtil.class);
    public static String NOTICE_URL="/rest/payback/alipay";
    // 签名方式 不需修改
    public static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
    public static String sign_type = "RSA";
    public static final String INPUT_CHARSET = "UTF-8";
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
        map.put("amount", StringNumber.formatPrecisionOfTwo(record.getMoney()));
        map.put("tradeNO", record.getNo());
        return map;
    }

    /**
     * 验证消息是否是支付宝发出的合法消息, wap
     * @param params 通知返回来的参数数组
     * @return 验证结果
     */
    public static boolean verifyWap(Map<String, String> params, AliPaySet set) {

        //判断responsetTxt是否为true，isSign是否为true
        //responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
        //isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
        String responseTxt = "false";
        if(params.get("notify_id") != null) {
            String notify_id = params.get("notify_id");
            responseTxt = verifyResponse(notify_id, set.getMchNo());
        }
        String sign = "";
        if(params.get("sign") != null) {sign = params.get("sign");}

        logger.info("params Sing:::" + sign);
        boolean isSign = getSignVeryfy(params, sign);

        logger.info("veryfy responseTxt:::" + responseTxt);
        logger.info("veryfy sign::::" + isSign);
        //写日志记录（若要调试，请取消下面两行注释）
        //String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign + "\n 返回回来的参数：" + AlipayCore.createLinkString(params);
        //AlipayCore.logResult(sWord);

        if (isSign && responseTxt.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取远程服务器ATN结果,验证返回URL
     *
     * @param notify_id 通知校验ID
     * @return 服务器ATN结果
     *         验证结果集：
     *         invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空
     *         true 返回正确信息
     *         false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
     */
    private static String verifyResponse(String notify_id, String partner) {
        //获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
        String veryfy_url = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notify_id;
        return checkUrl(veryfy_url);
    }

    /**
     * 根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @param sign 比对的签名结果
     * @return 生成的签名结果
     */
    private static boolean getSignVeryfy(Map<String, String> Params, String sign) {
        //过滤空值、sign与sign_type参数
        Map<String, String> sParaNew = paraFilter(Params);
        //获取待签名字符串
        String preSignStr = createLinkString(sParaNew);
        //获得签名验证结果
        boolean isSign = false;
        if(sign_type.equals("RSA")){
            isSign = RSA.verify(preSignStr, sign, ali_public_key, INPUT_CHARSET);
        }
        return isSign;
    }
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    /**
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
    /**
     * 获取远程服务器ATN结果
     * @param urlvalue 指定URL路径地址
     * @return 服务器ATN结果
     * 验证结果集：
     * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空
     * true 返回正确信息
     * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
     */
    public static String checkUrl(String urlvalue) {
        String inputLine = "";

        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection
                    .getInputStream()));
            inputLine = in.readLine().toString();
        } catch (Exception e) {
            e.printStackTrace();
            inputLine = "";
        }

        return inputLine;
    }
}
