package com.scht.util;

import com.scht.util.PayUtil.Base64;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/23.
 */
public class LocationUtil {
    private final static double DEF_PI = 3.14159265359; // PI
    private final static double DEF_PI180 = 0.01745329252; // PI/180.0
    private final static double DEF_R = 6370693.5; // radius of earth

    private static final String BAIDU_AK = "ICK7CITBUhm53QvyFsY45vey";
    /**
     * 获取百度坐标
     * @param x
     * @param y
     * @return  lat|lng
     */
    public static String getBaiDuLocationXY(String x, String y)  {
        String result = "";
//        String url = "http://api.map.baidu.com/geoconv/v1/?coords=" +x + "," + y
//                "&from=1&to=5&ak=ICK7CITBUhm53QvyFsY45vey";
        String url = "http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x="
                + x + "&y=" + y + "";
        try {
            String response = requestByPost(url, "");
            if (StringUtils.isNotBlank(response)) {
                JSONObject object = JSONObject.fromObject(response);
                if(object != null && (Integer)object.get("error") == 0)  {
                    byte[] xbuff = Base64.decode(object.get("x").toString());
                    byte[] ybuff = Base64.decode(object.get("y").toString());
                    result = new String(ybuff) + "|" + new String(xbuff);
                }
            }
        }catch (Exception e){
            return null;
        }
        System.out.println(result);
        return result;
    }

    public static String geoRegion(String lat, String lng) {
        try {
            String url = "http://api.map.baidu.com/geocoder/v2/?" +
                    "location=" + lat + "," + lng + "&output=json&pois=1&ak=" + BAIDU_AK;
//        String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address
//        + "&output=json&ak=ICK7CITBUhm53QvyFsY45vey&callback=showLocation";
            String response = requestByPost(url, "");
            System.out.println(response);
            JSONObject json = JSONObject.fromObject(response);
            if((Integer)json.get("status") == 0) {
                JSONObject resultObject = json.getJSONObject("result");
                JSONObject locationObject = resultObject.getJSONObject("addressComponent");
                return locationObject.getString("adcode");
            }else{
                return "";
            }
        }catch (Exception e){
            return "";
        }
    }

    public static double getLongDistance(String lon1, String lat1, String lon2, String lat2) {
            double ew1, ns1, ew2, ns2;
            double distance;
    // 角度转换为弧度
            ew1 = Double.valueOf(lon1) * DEF_PI180;
            ns1 = Double.valueOf(lat1) * DEF_PI180;
            ew2 = Double.valueOf(lon2) * DEF_PI180;
            ns2 = Double.valueOf(lat2) * DEF_PI180;
    // 求大圆劣弧与球心所夹的角(弧度)
            distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
    // 调整到[-1..1]范围内，避免溢出
            if (distance > 1.0)
                distance = 1.0;
            else if (distance < -1.0)
                distance = -1.0;
    // 求大圆劣弧长度
            distance = DEF_R * Math.acos(distance);
            return distance;
    }
    //百度计算距离，需要开通权限
    public static double calJuli(String start, String end){
        try {
            String url = "http://api.map.baidu.com/routematrix/v2/driving?output=json&" +
                    "origins=" + start + "&destinations=" + end + "&ak=" + BAIDU_AK;
            String response = requestByPost(url, "");
            System.out.println(response);
            JSONObject json = JSONObject.fromObject(response);
            if((Integer)json.get("status") == 0) {
                JSONObject resultObject = json.getJSONObject("result");
                JSONObject locationObject = resultObject.getJSONObject("distance");
                return locationObject.getDouble("value");
            }
           return 0d;
        }catch (Exception e){
            return 0d;
        }
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

    public static void main(String[] args){
       // event location ===37.380340576171875::117.98758697509766   山东省滨州市滨城区黄河五路391号

  // lng : 117.99349376541  lat : 37.387200474815
        String lng = "117.98758697509766";
        String lat = "37.380340576171875";

            //118.00137573272|37.391859192468

            String result = getBaiDuLocationXY(lng, lat);
        System.out.println(result);
            String[] latLng = result.split("\\|");
            System.out.println(geoRegion(37.38677761549855+"",117.99754905719125+""));
        String start = latLng[0]+"," + latLng[1];
        String end = lat+","+lng;
//        System.out.println("juli : " + getLongDistance(Double.valueOf(lng),Double.valueOf(lat),Double.valueOf(latLng[1]),Double.valueOf(latLng[0])));

        String address = " 滨城区滨州学院黄河五路马家小区公交站东";


    }
}
