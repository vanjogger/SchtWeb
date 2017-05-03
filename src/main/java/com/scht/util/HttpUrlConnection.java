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

        String params = "{\n" +
                "         \"memberId\": \"2e396e3723f84d49a36d89cefe38c102\",\n" +
                "         \"list\": [\n" +
                "             {\n" +
                "                 \"productId\": \"3857de40e2d948b09a2e82f9144c329a\",\n" +
                "                 \"amount\": \"1\",\n" +
                "                 \"name\": \"精美小炒\",\n" +
                "                 \"price\": \"12\"\n" +
                "             },\n" +
                "             {\n" +
                "                 \"productId\": \"075658d3a03d45b1bc0f0781c5032e35\",\n" +
                "                 \"amount\": \"1\",\n" +
                "                 \"name\": \"龙虾盒饭\",\n" +
                "                 \"price\": \"15\"\n" +
                "             },\n" +
                "             {\n" +
                "                 \"productId\": \"68ff29d6b3294b229413e404ac3c8079\",\n" +
                "                 \"amount\": \"1\",\n" +
                "                 \"name\": \"土豆丝\",\n" +
                "                 \"price\": \"11\"\n" +
                "             },\n" +
                "             {\n" +
                "                 \"productId\": \"f3dd533ab31146658837176aa97a13e3\",\n" +
                "                 \"amount\": \"1\",\n" +
                "                 \"name\": \"白米饭\",\n" +
                "                 \"price\": \"1\"\n" +
                "             },\n" +
                "             {\n" +
                "                 \"productId\": \"b3f21d6651ef46f996c753315040ba34\",\n" +
                "                 \"amount\": \"1\",\n" +
                "                 \"name\": \"米线\",\n" +
                "                 \"price\": \"10\"\n" +
                "             }\n" +
                "         ],\n" +
                "         \"remark\": \"\",\n" +
                "         \"express\": \"1\",\n" +
                "         \"userName\": \"拒绝\",\n" +
                "         \"telephone\": \"15236966963\",\n" +
                "         \"address\": \"北京市,北京市,东城区不能\"\n" +
                "     }";
        String url = "http://116.62.51.12/rest/order/createSaleOrder";
        try {
           String str = doPostWithParams(url,params);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
