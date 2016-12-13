package com.scht.util.PayUtil;

import com.scht.admin.entity.OrderPayRecord;
import com.scht.admin.entity.WeixinPaySet;
import com.scht.util.StringNumber;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 微信支付UTIL
 * Created by Administrator on 2016/12/5.
 */
public class WeixinPayUtil {

    public static String NOTICE_URL="/rest/payback/weixinpay";

    private static String tyZf_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一支付接口

    /**
     * APP支付
     * @param request
     * @param ip 支付ip
     * @param backUrl  低层通知路径
     * @param record  支付记录
     * @param set  微信支付设置
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String,String> submitJsonForApp(HttpServletRequest request,String ip, String backUrl,
                                                      OrderPayRecord record,WeixinPaySet set) throws IOException, DocumentException {
        NativeZfSj nativeZfSj = new NativeZfSj();
        nativeZfSj.setAppid(set.getAppId());
        nativeZfSj.setMch_id(set.getMchNo());
        nativeZfSj.setProduct_id("");// 产品id 不是必填
        TyZf tyZf = createTyZf(ip, nativeZfSj, "APP", set.getPayKey(), record.getNo(), record.getMoney(), backUrl);
        nativeZfSj = tyZf(nativeZfSj, tyZf, set.getPayKey());
        if(!StringUtil.isNullOrEmpty(nativeZfSj.getPrepay_id())) {
            PayRequest payRequest = new PayRequest();
            payRequest.setAppId(nativeZfSj.getAppid());
            payRequest.setNonceStr(UUIDFactory.random());
            payRequest.setTimeStamp(String.valueOf(System.currentTimeMillis()).substring(0,10));
            payRequest.setSignType("MD5");
            payRequest.setPrepay_id(nativeZfSj.getPrepay_id());
            Map<String, String> map = new HashMap<String, String>();
            map.put("appid", payRequest.getAppId());
            map.put("partnerid",set.getMchNo());
            map.put("prepayid", payRequest.getPrepay_id());
            map.put("noncestr", payRequest.getNonceStr());
            map.put("timestamp", payRequest.getTimeStamp());
            map.put("package", "Sign=WXPay");
            map.put("sign", weiXinZfSign(map, set.getPayKey()));
//            String jsonString = new Gson().toJson(map);
//            jsonString = ZyUnicode.ascii2Native(jsonString);
            map.remove("package");
            map.put("packageValue", "Sign=WXPay");
            return map;
        } else {
            return null;
        }
    }


    //调用统一支付接口
    public static NativeZfSj tyZf(NativeZfSj nativeZfSj, TyZf tyZf, String key) throws IOException, DocumentException {
        String xmlString = dyTyZf(tyZf);
        Map<String, String> returnTyZfXmlMap = MessageUtil.parseXmlByString(xmlString);
        //判断调用统一支付接口后返回来的数据 是否返回数据正确。
        if ("FAIL".equals(returnTyZfXmlMap.get("return_code"))) {
            nativeZfSj.setReturn_code("FAIL");
            if(returnTyZfXmlMap.get("return_msg") == null) {
                nativeZfSj.setReturn_msg("通信失败");
            } else {
                nativeZfSj.setReturn_msg(returnTyZfXmlMap.get("return_msg"));
            }
        } else if ("FAIL".equals(returnTyZfXmlMap.get("result_code"))) {
            nativeZfSj.setReturn_code("FAIL");
            if(returnTyZfXmlMap.get("return_msg") == null) {
                nativeZfSj.setReturn_msg("重新操作或者联系商家");
            } else {
                nativeZfSj.setReturn_msg(returnTyZfXmlMap.get("return_msg"));
            }
            nativeZfSj.setResult_code("FAIL");
            if(returnTyZfXmlMap.get("err_code_des") == null) {
                nativeZfSj.setErr_code_des("业务失败");
            } else {
                nativeZfSj.setErr_code_des(returnTyZfXmlMap.get("err_code_des"));
            }
        } else {
            //判断签名是否成功
            String returnTyZfXmlMapSign = returnTyZfXmlMap.get("sign");
            returnTyZfXmlMap.remove("sign");
            String returnSign = weiXinZfSign(returnTyZfXmlMap, key);
            if (returnSign.equals(returnTyZfXmlMapSign)) {
                String prepay_id = returnTyZfXmlMap.get("prepay_id");//预支付id
                String appid = returnTyZfXmlMap.get("appid");
                String mch_id = returnTyZfXmlMap.get("mch_id");
                String code_url = returnTyZfXmlMap.get("code_url");
                nativeZfSj.setReturn_code("SUCCESS");
                nativeZfSj.setPrepay_id(prepay_id);
                nativeZfSj.setResult_code("SUCCESS");
                nativeZfSj.setAppid(appid);
                nativeZfSj.setMch_id(mch_id);
                nativeZfSj.setNonce_str(UUIDFactory.random());
                nativeZfSj.setCode_url(code_url);
                nativeZfSj.setSign(createNativeZfSjBackMap(nativeZfSj, key));
            } else {
                //签名失败
                nativeZfSj.setReturn_code("FAIL");
                if(returnTyZfXmlMap.get("return_msg") == null) {
                    nativeZfSj.setReturn_msg("签名失败");
                } else {
                    nativeZfSj.setReturn_msg(returnTyZfXmlMap.get("return_msg"));
                }
            }
        }
        return nativeZfSj;
    }


    //创建调用统一支付接口后返回数据时成功时生成签名所需要的数据
    private static String createNativeZfSjBackMap(NativeZfSj nativeZfSj, String key) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("return_code", nativeZfSj.getReturn_code());
        if (!StringUtil.isNullOrEmpty(nativeZfSj.getReturn_msg())) {
            map.put("return_msg", nativeZfSj.getReturn_msg());
        }
        map.put("appid", nativeZfSj.getAppid());
        map.put("mch_id", nativeZfSj.getMch_id());
        map.put("nonce_str", nativeZfSj.getNonce_str());
        map.put("prepay_id", nativeZfSj.getPrepay_id());
        map.put("result_code", nativeZfSj.getResult_code());
        if (!StringUtil.isNullOrEmpty(nativeZfSj.getErr_code_des())) {
            map.put("err_code_des", nativeZfSj.getErr_code_des());
        }
        String sign = weiXinZfSign(map, key);
        return sign;
    }
    //调用统一支付接口 返回预支付订单号
    public static String dyTyZf(TyZf t) throws IOException {
        String data = MessageUtil.tyZfToXml(t);
        String xmlString = requestByPost(tyZf_url, data);
        return xmlString;
    }
    public static String requestByPost(String urlString, String data) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setDefaultUseCaches(false);

        OutputStream os = conn.getOutputStream();
        try {
            os.write(data.getBytes("UTF-8"));
        } finally {
            if(os != null) os.close();
        }

        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = conn.getInputStream();
            baos = new ByteArrayOutputStream();
            int len;
            byte[] buff = new byte[1024 * 8];
            while((len = is.read(buff)) != -1)  {
                baos.write(buff, 0, len);
            }
            return new String(baos.toByteArray(), "UTF-8");
        } finally {
            if(baos != null) baos.close();
            if(is != null) is.close();
        }
    }

    public static TyZf createTyZf(String ip, NativeZfSj nativeZfSj, String type, String key, String orderNo, String realMoney, String backUrl) {
        TyZf tyZf = new TyZf();
        tyZf.setSpbill_create_ip(ip);
        tyZf.setAppid(nativeZfSj.getAppid());
        tyZf.setMch_id(nativeZfSj.getMch_id());
        tyZf.setOpenid(nativeZfSj.getOpenid());
        tyZf.setProduct_id(nativeZfSj.getProduct_id());
        tyZf.setNonce_str(UUIDFactory.random());
        String description = "";//商品描述
        description = description +  (orderNo);
        tyZf.setBody(description);  // 商品描述
        tyZf.setOut_trade_no(orderNo);//  商户订单号 确保在商户这端是唯一的
        int total_fee = Integer.valueOf(StringNumber.formatIgnoreOfZero(StringNumber.mul(realMoney, "100")));
        tyZf.setTotal_fee(total_fee);//  商品价格 单位为分
        tyZf.setNotify_url(backUrl);
        tyZf.setTrade_type(type);
        //获取签名
        tyZf.setSign(weiXinZfSign(createTyZfMap(tyZf), key));
        return tyZf;
    }


    //组装统一支付map数据
    private static Map<String, String> createTyZfMap(TyZf tyZf) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("appid", tyZf.getAppid());
        map.put("mch_id", tyZf.getMch_id());
        map.put("nonce_str", tyZf.getNonce_str());
        map.put("body", tyZf.getBody());
        map.put("out_trade_no", tyZf.getOut_trade_no());
        map.put("total_fee", String.valueOf(tyZf.getTotal_fee()));
        map.put("spbill_create_ip", tyZf.getSpbill_create_ip());
        map.put("notify_url", tyZf.getNotify_url());
        map.put("trade_type", tyZf.getTrade_type());
        if (!StringUtil.isNullOrEmpty(tyZf.getTime_expire())) {
            map.put("time_expire", tyZf.getTime_expire());
        }
        if (!StringUtil.isNullOrEmpty(tyZf.getTime_start())) {
            map.put("time_start", tyZf.getTime_start());
        }
        if (!StringUtil.isNullOrEmpty(tyZf.getGoods_tag())) {
            map.put("goods_tag", tyZf.getGoods_tag());
        }
        if (!StringUtil.isNullOrEmpty(tyZf.getOpenid())) {
            map.put("openid", tyZf.getOpenid());
        }
        if (!StringUtil.isNullOrEmpty(tyZf.getDevice_info())) {
            map.put("device_info", tyZf.getDevice_info());
        }
        if (!StringUtil.isNullOrEmpty(tyZf.getAttach())) {
            map.put("attach", tyZf.getAttach());
        }
        if (!StringUtil.isNullOrEmpty(tyZf.getProduct_id())) {
            map.put("product_id", tyZf.getProduct_id());
        }
        return map;
    }


    //微信支付签名
    public static String weiXinZfSign(Map<String, String> signMap, String key) {
        Collection<String> keyset= signMap.keySet();
        List<String> list = new ArrayList<String>(keyset);
        //对key键值按字典升序排序
        Collections.sort(list);
        String signTemp = "";
        for(String s : list) {
            if("".equals(signTemp)) {
                signTemp = s + "=" + signMap.get(s);
            } else {
                signTemp = signTemp + "&" + s + "=" + signMap.get(s);
            }
        }
        if(!StringUtil.isNullOrEmpty(key)) {
            signTemp = signTemp + "&" + "key=" + key;
        }
        signTemp = encrypt(signTemp).toUpperCase();
        return signTemp;
    }


    //对字符串进行加密
    private static String encrypt(String str) {
        String algorithmName = "md5";
        String encryptText=null;
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(str.getBytes("UTF8"));
            byte s[] = m.digest();
            return hex(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptText;
    }

    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }

    // 支付返回
    public static Object tyZfNotify(HttpServletRequest request, String payKey) throws UnsupportedEncodingException {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        String respMessage = ""; //
        String orderId="";
        TyNoticeSj returnTyNoticeSj = null;
        TyNoticeSj tyNoticeSj = new TyNoticeSj();
        // xml请求解析
        try {
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            orderId = requestMap.get("out_trade_no");
            if ("FAIL".equals(requestMap.get("return_code"))) {
                tyNoticeSj.setReturn_code("FAIL");
                tyNoticeSj.setReturn_msg("通信失败");
                respMessage = MessageUtil.tyNoticeToXml(tyNoticeSj);
            } else if ("FAIL".equals(requestMap.get("result_code"))) {
                tyNoticeSj.setReturn_code("FAIL");
                tyNoticeSj.setReturn_msg(requestMap.get("err_code_des"));
                respMessage = MessageUtil.tyNoticeToXml(tyNoticeSj);
            } else {
                //判断签名
                String returnSign = requestMap.get("sign");
                requestMap.remove("sign");
                if (requestMap.get("orderId") != null) {
                    requestMap.remove("orderId");
                }
                String zzSign = weiXinZfSign(requestMap, payKey);
                if (zzSign.equals(returnSign)) {
                    returnTyNoticeSj = createBackTyZfNotice(requestMap);
                    returnTyNoticeSj.setSign(returnSign);
                    tyNoticeSj.setReturn_code("SUCCESS");
                    respMessage = MessageUtil.tyNoticeToXml(tyNoticeSj);
                } else {//签名失败
                    tyNoticeSj.setReturn_code("FAIL");
                    tyNoticeSj.setReturn_msg("签名失败");
                    respMessage = MessageUtil.tyNoticeToXml(tyNoticeSj);
                }
            }
        } catch (Exception e) {
            tyNoticeSj.setReturn_code("FAIL");
            tyNoticeSj.setReturn_msg("参数格式错误");
            respMessage = MessageUtil.tyNoticeToXml(tyNoticeSj);
        }
        return new Object[]{returnTyNoticeSj, respMessage, orderId};
    }

    //微信通用通知商家时返回来的信息数据组装成对象
    private static TyNoticeSj createBackTyZfNotice(Map<String, String> requestMap) {
        TyNoticeSj tyNoticeSj = new TyNoticeSj();
        tyNoticeSj.setReturn_code(requestMap.get("return_code"));
        if (requestMap.get("return_msg") != null) {
            tyNoticeSj.setReturn_msg(requestMap.get("return_msg"));
        }
        tyNoticeSj.setAppid(requestMap.get("appid"));
        tyNoticeSj.setMch_id(requestMap.get("mch_id"));
        if (requestMap.get("device_info") != null) {
            tyNoticeSj.setDevice_info(requestMap.get("device_info"));
        }
        tyNoticeSj.setNonce_str(requestMap.get("nonce_str"));
        tyNoticeSj.setResult_code(requestMap.get("result_code"));
        if (requestMap.get("err_code") != null) {
            tyNoticeSj.setErr_code(requestMap.get("err_code"));
        }
        if (requestMap.get("err_code_des") != null) {
            tyNoticeSj.setErr_code_des(requestMap.get("err_code_des"));
        }
        tyNoticeSj.setOpenid(requestMap.get("openid"));
        tyNoticeSj.setIs_subscribe(requestMap.get("is_subscribe"));
        tyNoticeSj.setTrade_type(requestMap.get("trade_type"));
        tyNoticeSj.setBank_type(requestMap.get("bank_type"));
        tyNoticeSj.setTotal_fee(Integer.parseInt(requestMap.get("total_fee")));
        tyNoticeSj.setCoupon_fee(requestMap.get("coupon_fee"));
        if (requestMap.get("fee_type") != null) {
            tyNoticeSj.setFee_type(requestMap.get("fee_type"));
        }
        tyNoticeSj.setTransaction_id(requestMap.get("transaction_id"));
        tyNoticeSj.setOut_trade_no(requestMap.get("out_trade_no"));
        if (requestMap.get("attach") != null) {
            tyNoticeSj.setAttach(requestMap.get("attach"));
        }
        tyNoticeSj.setTime_end(requestMap.get("time_end"));
        return tyNoticeSj;
    }


}
