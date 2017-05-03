package com.scht.util.PayUtil;

import com.scht.admin.entity.WeixinPaySet;
import com.scht.common.ServiceException;
import com.scht.util.UUIDFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;

/**
 * Created by wxh on 2017/4/26.
 */
public class WeixinHbUtil {

    private static final String PAY_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    private static Map<String,String> createParams(WeixinPaySet set, String no, String openId, String amount, String ip){
        Map<String,String> map = new HashMap<>();
        map.put("mch_appid", set.getAppId()); //公众号ID wx8888888
        map.put("mchid", set.getMchNo());  //微信支付商户号
        map.put("nonce_str", UUIDFactory.random());
        map.put("parter_trade_no",no);
        map.put("openid", openId);
        map.put("check_name", "NO_CHECK");
        map.put("amount", amount);
        map.put("desc","有奖问题奖励");
        map.put("spbill_create_ip", ip);
        return map;
    }

    //发红包
    public static boolean sendHb(WeixinPaySet set, String no ,String openId, String amount, String ip, String rootPath){
        //组装map 数据
        Map<String,String> map = createParams(set,no,openId,amount,ip);
        map.put("sign",WeixinPayUtil.weiXinZfSign(map, set.getPayKey()));
        String respXml = createRespXml(map);
        try {
            Map<String, String> resultMap = sendExecute(respXml, set.getMchNo(), rootPath + set.getCerPath());
            return dealWithBackData(map);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //发送红包
    public static Map<String, String> sendExecute(String respXml, String mch_id, String filePath) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, KeyManagementException, DocumentException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(filePath)); //此处为证书所放的绝对路径
        try {
            keyStore.load(instream, mch_id.toCharArray());
        } finally {
            instream.close();
        }
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mch_id.toCharArray()).build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

        Map<String, String> map = new HashMap<String, String>();
        try {
            HttpPost httpPost = new HttpPost(PAY_URL);

            StringEntity reqEntity = new StringEntity(respXml, "utf-8");

            // 设置类型
            reqEntity.setContentType("application/x-www-form-urlencoded");

            httpPost.setEntity(reqEntity);

            System.out.println("executing request" + httpPost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 从request中取得输入流
                    InputStream inputStream = entity.getContent();
                    // 读取输入流
                    SAXReader reader = new SAXReader();
                    Document document = reader.read(inputStream);
                    // 得到xml根元素
                    Element root = document.getRootElement();
                    // 得到根元素的所有子节点
                    List<Element> elementList = root.elements();
                    // 遍历所有子节点
                    for (Element e : elementList) {
                        map.put(e.getName(), e.getText());
                    }
                    // 释放资源
                    inputStream.close();
                    inputStream = null;
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return map;
    }

    //处理返回信息
    private static boolean dealWithBackData(Map<String, String> map) throws Exception {
        String return_code = map.get("return_code"); // 返回状态码
        String return_msg = map.get("return_msg");//返回信息
        String result_code = map.get("result_code");  // 业务结果
        String err_code = map.get("err_code"); // 错误代码
        String err_code_des = map.get("err_code_des"); // 错误代码描述
        if("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)) {
            return true;
        } else {
            throw new Exception("return_msg=" + return_msg + " err_code=" + err_code + " err_code_des=" + err_code_des);
        }
    }

    private static String createRespXml(Map<String, String> respMap) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = respMap.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
        }
        sb.append("</xml>");
        return sb.toString();
    }
}
