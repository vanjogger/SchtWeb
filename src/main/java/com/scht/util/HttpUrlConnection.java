package com.scht.util;


import org.jdom.Document;
import org.jdom.Element;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 发送http请求
 * Account: Robin
 * Date: 14-8-31
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class HttpUrlConnection {
    private final String LOG_TAG = this.getClass().getSimpleName();


    /**
     * @param postUrl 请求的连接
     * @return
     * @throws IOException
     */
    public static String doPost(String postUrl) throws IOException {
        URL url = new URL(postUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();//打开链接
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.connect();

        int code = connection.getResponseCode();
        if (code == 200) {
            InputStream is = connection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len;
            while ((len = is.read(data)) != -1) {
                bos.write(data, 0, len);
                bos.close();
            }
            return new String(bos.toByteArray(), "UTF-8");
        }
        return null;
    }

    public static String doPostWithParams(String postUrl,String params) throws IOException {
            URL url = new URL(postUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();//打开链接

          //  connection.addRequestProperty("Content-Type", "application/json;");
          connection.addRequestProperty("Content-Encoding", "gzip");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.connect();

            OutputStream os = connection.getOutputStream();
            if(StringUtil.isNotNull(params)) {
                os.write(params.getBytes("UTF-8"));
                os.flush();
                os.close();
            }

            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] data = new byte[1024];
                int len;
                while ((len = is.read(data)) != -1) {
                    bos.write(data, 0, len);
                    bos.close();
                }
                return new String(bos.toByteArray(), "UTF-8");
            }
            return null;
    }




    public static void main(String[] ars) {
       /* try {
           byte[]  result = doPost("http://www.8ycn.net/dhy/checkAccountCode.action?code=111");
            System.out.println(new String(result,"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            //doPostWidthParams("http://localhost:8080/front/test","<xml><msg>Hello</msg></xml>");
           // System.out.println(JSONObject.fromObject(q).toString());
           // String result = doPostWithParams("http://192.168.2.218:8080/member/notice_SelfPayBack", "v_oid=201602040942152977&v_pstatus=0&v_pstring=支付成功&v_pmode=null&v_md5str=22b242ceb98e500db2bc6d14282bffb2&v_amount=176.0&remark1=54c7375e9c9f469fa0cef6e3f52e18b6|bb2df414ecae47fd8f4c47c8e1bb8dbb|ORDER&remark2=");
          //  String result = doPostWithParams("http://123.56.12.54:7070/rest/accountLogin", "mobile=18660421026&password=123456&identify=7e5e8efc8bd51f5a63a714066bc7baf4");
          //  System.out.println(":: "+result);
            Document doc = new Document();
            Element root  = new Element("xml");

            Element appid = new Element("appid");
            appid.setText("wx2421b1c4370ec43b");
            root.addContent(appid);
            Element attach = new Element("attach");
            attach.setText("dasdsds");
            root.addContent(attach);
            Element body = new Element("body");
            body.setText("测试d产品");
            root.addContent(body);
            Element mch__id = new Element("mch_id");
            mch__id.setText("10000100");
            root.addContent(mch__id);
            Element nonce__str = new Element("nonce_str");
            nonce__str.setText("5K8264ILTKCH16CQ25");
            root.addContent(nonce__str);
            Element notify__url = new Element("notify_url");
            notify__url.setText("dddddd");
            root.addContent(notify__url);
            Element openid = new Element("openid");
            openid.setText("bv");
            root.addContent(openid);
            Element out__trade__no = new Element("out_trade_no");
            out__trade__no.setText("aasdsds");
            root.addContent(out__trade__no);
            Element spbill__create__ip = new Element("spbill_create_ip");
            spbill__create__ip.setText("218.154.2.105");
            root.addContent(spbill__create__ip);
            Element total__fee = new Element("total_fee");
            total__fee.setText("1");
            root.addContent(total__fee);
            Element trade__type = new Element("trade_type");
            trade__type.setText("APP");
            root.addContent(trade__type);
            Element sign = new Element("sign");
            sign.setText("a0615ef64b21524893fb8be58ba6e440");
            root.addContent(sign);


            doc.setRootElement(root);
            //String s = "<xml><appid>wx8c305584416361a2</appid><attach>附加信息</attach><body>测试产品</body><mch__id>1315855301</mch__id><nonce__str>9f93ef71b99340f88925a70eb6deb73d</nonce__str><notify__url>dddddd</notify__url><openid>bb</openid><out__trade__no>ccccccccc</out__trade__no><spbill__create__ip>218.154.2.105</spbill__create__ip><total__fee>1</total__fee><trade__type>APP</trade__type><sign>a0615ef64b21524893fb8be58ba6e440</sign></xml>";
            //StringBuffer s = XmlUtil.formatDocToStr(doc);
            String s = "<xml><appid>wx8c305584416361a2</appid><attach>附加信息</attach><body>测试产品</body><mch_id>1315855301</mch_id><nonce_str>74ef41c3e8c2409890cdbaf813576e01</nonce_str><notify_url>dddd</notify_url><openid>aa</openid><out_trade_no>20160227</out_trade_no><spbill_create_ip>218.154.2.105</spbill_create_ip><total_fee>1</total_fee><trade_type>APP</trade_type><sign>66ff711347b0b3f1d377e6210be1dd4c</sign></xml>";
            System.out.println(s);
            String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
            String result = HttpUrlConnection.doPostWithParams(url, s.toString());
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
