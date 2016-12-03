package com.scht.front.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送http请求
 * User: Robin
 * Date: 14-8-31
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class HttpUrlConnection {
    private final String LOG_TAG = this.getClass().getSimpleName();
    private static String HEAD_URl = "http://123.56.12.54:7070/";


    /**
     * @param postUrl 请求的连接
     * @param params  参数
     * @param header  头信息
     * @return
     * @throws IOException
     */
    public static byte[] postFromWebByHttpUrlConnection(String postUrl, String params, Map<String, String> header) throws IOException {
        String key = "";
        if (header.get("userId") != null) {
            String userId = header.get("userId");
            String password = header.get("password");
            key = userId + ":" + password;
        }

        String version = "1";
        if (header.get(version) != null) {
            version = header.get(version);
        } 

        URL url = new URL(HEAD_URl + postUrl);
        System.out.println(HEAD_URl + postUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();//打开链接
        //设置头信息
//        if (key != null) {
        connection.addRequestProperty("Authorization", "1:7e5e8efc8bd51f5a63a714066bc7baf4");
//        }
        //connection.addRequestProperty("Accept-Charset", "UTF-8");
       // connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("Content-Encoding", "GZIP");
        connection.addRequestProperty("IV", version);
        //设置网络超时参数
        connection.setConnectTimeout(4000);
        connection.setReadTimeout(3000);

        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");

        connection.connect();

        OutputStream os = connection.getOutputStream();
       /* if (os != null) {
            os.write(params.getBytes());
            os.close();
        }*/
        int code = connection.getResponseCode();
        System.out.println("---  "+code);
        if(code==200) {
            InputStream is = connection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len;
            while ((len = is.read(data)) != -1) {
                bos.write(data, 0, len);
                bos.close();
            }
            return bos.toByteArray();
        }
        return null;

    }
    
    public static String doPostWithParams(String postUrl,String params) throws IOException {
        URL url = new URL(postUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();//打开链接
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.connect();
        System.out.println(postUrl+" : "+params);
        OutputStream os = connection.getOutputStream();
        os.write(params.getBytes("UTF-8"));
     //   PrintWriter writer = new PrintWriter(os);
     //   writer.print(params);
     //   writer.flush();
        os.flush();
    //    writer.close();
        os.close();

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

   public static void main(String[] args){
       try {
         /*  byte[]  result = postFromWebByHttpUrlConnection("rest/queryActivitySpot?spotName=222","mobile=18660421026&password=123456&identify=2KZlbuJkjH6Q3hONiTWuew==",new HashMap<String, String>());
           System.out.println("***  "+new String(result));*/
           
           //System.out.println(doPostWithParams("http://106.ihuyi.cn/webservice/sms.php?method=GetNum", "account=cf_maxinghua&password=maxinghua0125"));
           	//System.out.println(doPostWithParams("http://106.ihuyi.cn/webservice/sms.php?method=Submit", "account=cf_maxinghua&password=maxinghua0125&mobile=18706645733&content=您的验证码是：【1234】。请不要把验证码泄露给其他人。"));
    	   String result = doPostWithParams(HEAD_URl+"rest/accountLogin", "mobile=18660421026&password=123456&identify=7e5e8efc8bd51f5a63a714066bc7baf4");
    	   System.out.println(":: "+result);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
