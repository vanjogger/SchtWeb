package com.scht.util.PayUtil;

import com.scht.util.StringUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 14-8-8.
 */
public class MessageUtil {

    public static Map<String, String> parseXml(HttpServletRequest request) throws DocumentException, IOException {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }


    public static Map<String, String> parseXmlByString(String xmlString) throws DocumentException, IOException {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        StringReader read = new StringReader(xmlString);
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(source);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        return map;
    }

//    //组装文本消息
//    public static String textMessageToXml(TextMessage textMessage) {
//        /**
//         * <xml>
//         <ToUserName><![CDATA[toUser]]></ToUserName>
//         <FromUserName><![CDATA[fromUser]]></FromUserName>
//         <CreateTime>12345678</CreateTime>
//         <MsgType><![CDATA[text]]></MsgType>
//         <Content><![CDATA[你好]]></Content>
//         </xml>
//         */
//        String message = "<xml>" +
//                "<ToUserName><![CDATA[" + textMessage.getToUserName() + "]]></ToUserName>" +
//                "<FromUserName><![CDATA[" + textMessage.getFromUserName() + "]]></FromUserName>" +
//                "<CreateTime>" + textMessage.getCreateTime() + "</CreateTime>" +
//                "<MsgType><![CDATA[" + textMessage.getMsgType() + "]]></MsgType>" +
//                "<Content><![CDATA[" + textMessage.getContent() + "]]></Content>" +
//                "</xml>";
//        return message;
//    }

//    //组装图片消息
//    public static String imageMessageToXml(TextMessage textMessage) {
//        /**
//         * <xml>
//         <ToUserName><![CDATA[toUser]]></ToUserName>
//         <FromUserName><![CDATA[fromUser]]></FromUserName>
//         <CreateTime>12345678</CreateTime>
//         <MsgType><![CDATA[image]]></MsgType>
//         <Image>
//         <MediaId><![CDATA[media_id]]></MediaId>
//         </Image>
//         </xml>
//         */
//        String message = "<xml>" +
//                "<ToUserName><![CDATA[" + textMessage.getToUserName() + "]]></ToUserName>" +
//                "<FromUserName><![CDATA[" + textMessage.getFromUserName() + "]]></FromUserName>" +
//                "<CreateTime>" + textMessage.getCreateTime() + "</CreateTime>" +
//                "<MsgType><![CDATA[" + textMessage.getMsgType() + "]]></MsgType>" +
//                "<Image>" +
//                "<MediaId><![CDATA[" + textMessage.getMediaId() + "]]></MediaId>" +
//                "</Image>" +
//                "</xml>";
//        return message;
//    }


    //组装调用统一支付接口时所需要的数据
    public static String tyZfToXml(TyZf tyZf) {
        String message = "<xml>" +
                "<appid><![CDATA[" + tyZf.getAppid() + "]]></appid>" +
                "<mch_id><![CDATA[" + tyZf.getMch_id() + "]]></mch_id>" +
                "<nonce_str><![CDATA[" + tyZf.getNonce_str() + "]]></nonce_str>" +
                "<sign><![CDATA[" + tyZf.getSign() + "]]></sign>" +
                "<body><![CDATA[" + tyZf.getBody() + "]]></body>" +
                "<out_trade_no><![CDATA[" + tyZf.getOut_trade_no() + "]]></out_trade_no>" +
                "<total_fee><![CDATA[" + tyZf.getTotal_fee() + "]]></total_fee>" +
                "<spbill_create_ip><![CDATA[" + tyZf.getSpbill_create_ip() + "]]></spbill_create_ip>" +
                "<notify_url><![CDATA[" + tyZf.getNotify_url() + "]]></notify_url>" +
                "<trade_type><![CDATA[" + tyZf.getTrade_type() + "]]></trade_type>";
        if (!StringUtil.isNullOrEmpty(tyZf.getDevice_info())) {
            message = message + "<device_info><![CDATA[" + tyZf.getDevice_info() + "]]></device_info>";
        }
        if (!StringUtil.isNullOrEmpty(tyZf.getAttach())) {
            message = message + "<attach><![CDATA[" + tyZf.getAttach() + "]]></attach>";
        }
        if (!StringUtil.isNullOrEmpty(tyZf.getTime_start())) {
            message = message + "<time_start><![CDATA[" + tyZf.getTime_start() + "]]></time_start>";
        }
        if (!StringUtil.isNullOrEmpty(tyZf.getTime_expire())) {
            message = message + "<time_expire><![CDATA[" + tyZf.getTime_expire() + "]]></time_expire>";
        }
        if (!StringUtil.isNullOrEmpty(tyZf.getGoods_tag())) {
            message = message + "<goods_tag><![CDATA[" + tyZf.getGoods_tag() + "]]></goods_tag>";
        }
        if (!StringUtil.isNullOrEmpty(tyZf.getOpenid())) {
            message = message + "<openid><![CDATA[" + tyZf.getOpenid() + "]]></openid>";
        }
        if (!StringUtil.isNullOrEmpty(tyZf.getProduct_id())) {
            message = message + "<product_id><![CDATA[" + tyZf.getProduct_id() + "]]></product_id>";
        }
        message = message + "</xml>";
        return message;
    }

    //组装调用微信通用通知接口后返回给微信的信息数据
    public static String tyNoticeToXml(TyNoticeSj tyNoticeSj) {
        String message = "<xml>" +
                "<return_code><![CDATA[" + tyNoticeSj.getReturn_code() + "]]></return_code>";
        if(!StringUtil.isNullOrEmpty(tyNoticeSj.getReturn_msg())) {
            message = message +  "<return_msg><![CDATA[" + tyNoticeSj.getReturn_msg() + "]]></return_msg>";
        }
        message = message + "</xml>";
        return message;
    }
}





























