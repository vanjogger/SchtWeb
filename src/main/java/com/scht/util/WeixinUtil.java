package com.scht.util;

import com.alibaba.fastjson.JSON;
import com.scht.admin.bean.WeixinUser;
import com.scht.admin.entity.WeixinPaySet;
import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wxh on 2017/5/3.
 */
public class WeixinUtil {
    private static long ACCESS_TOKEN_TIME = 0L; //上次获取token的时间
    private static double TOKEN_EXPIRES = 0d; //token有效期
    private static String ACCESS_TOKEN; //上次获取的token值
    //获取accessToken
    public static String getAccessToken(WeixinPaySet set){
        //如果有效期大于0 并且当前时间和上次获取时间差小于有效期，直接返回上次获取的token
        if(TOKEN_EXPIRES > 0 && (System.currentTimeMillis()-ACCESS_TOKEN_TIME < TOKEN_EXPIRES*1000)){
            return ACCESS_TOKEN;
        }
        String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
        String data = "&appid="+set.getGzAppId()+"&secret="+set.getSecret();
        String jsonString = null;
        try {
            jsonString = requestByPost(token_url,data);
            JSONObject map = JSONObject.fromObject(jsonString);
            TOKEN_EXPIRES = (Double)map.get("expires_in");//获取token有效期
            ACCESS_TOKEN_TIME = System.currentTimeMillis();//获取token时间
            ACCESS_TOKEN = (String) map.get("access_token");
            return ACCESS_TOKEN;
        } catch (Exception e) {
            return null;
        }
    }

    //获取用户基本信息
    public static WeixinUser getUser(WeixinPaySet set, String openId) throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" +
                getAccessToken(set) + "&openid="+openId+"&lang=zh_CN";
        String jsonstr = requestByPost(url,"");
        JSONObject object = JSONObject.fromObject(jsonstr);
        if(object.get("subscribe") != null && (Integer)object.get("subscribe") == 1) {
            WeixinUser user = new WeixinUser();
            user.setOpenId((String) object.get("openid"));
            user.setUnionId((String) object.get("unionid"));
            return user;
        }
        return null;

    }

    //拉取关注着列表
    public static List<String> getUserList(WeixinPaySet set) throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" +
                getAccessToken(set);
        String jsonStr = requestByPost(url, "");
        if(StringUtil.isNullOrEmpty(jsonStr)) return null;
        JSONObject object = JSONObject.fromObject(jsonStr);
        Object total = object.get("total");
        if(total == null) return null;
        if((Integer) total == 0) return null;
        net.sf.json.JSONObject j1 = object.getJSONObject("data");
        List<String> list = (List<String>) j1.get("openid");
        if((Integer)total > 10000) {
            int len = (Integer)total /10000;
            String nextOpenId = (String) object.get("next_openid");
            for(int i=0; i < len; i++) {
                Map<String,Object> map = userList(set, nextOpenId);
                if(map != null) {
                    list.addAll((Collection<? extends String>) map.get("list"));
                    nextOpenId = (String) map.get("next");
                }
            }
        }
        return list;
    }

    public static Map<String, Object> userList(WeixinPaySet set, String nextOpenId) throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" +
                getAccessToken(set) + "&next_openid=" + nextOpenId;
        String jsonStr = requestByPost(url, "");
        if(StringUtil.isNullOrEmpty(jsonStr)) return null;
        JSONObject object = JSONObject.fromObject(jsonStr);
        Object total = object.get("total");
        if(total == null) return null;
        if((Integer) total == 0) return null;
        net.sf.json.JSONObject j1 = object.getJSONObject("data");
        List<String> list = (List<String>) j1.get("openid");
        String next = (String) object.get("next_openid");
        Map<String,Object> map = new HashMap<>();
        map.put("list", list);
        map.put("next" ,next);
        return map;
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
       String str = "{\n" +
               "   \"subscribe\": 1, \n" +
               "   \"openid\": \"o6_bmjrPTlm6_2sgVt7hMZOPfL2M\", \n" +
               "   \"nickname\": \"Band\", \n" +
               "   \"sex\": 1, \n" +
               "   \"language\": \"zh_CN\", \n" +
               "   \"city\": \"广州\", \n" +
               "   \"province\": \"广东\", \n" +
               "   \"country\": \"中国\", \n" +
               "   \"headimgurl\":  \"http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0\",\n" +
               "  \"subscribe_time\": 1382694957,\n" +
               "  \"unionid\": \" o6_bmasdasdsad6_2sgVt7hMZOPfL\",\n" +
               "  \"remark\": \"\",\n" +
               "  \"groupid\": 0,\n" +
               "  \"tagid_list\":[128,2]\n" +
               "}";
        JSONObject object = JSONObject.fromObject(str);
        if(object.get("subscribe") != null && (Integer)object.get("subscribe") == 1) {
            WeixinUser user = new WeixinUser();
            user.setOpenId((String) object.get("openid"));
            user.setUnionId((String) object.get("unionid"));
             System.out.println(user.getUnionId());
        }
        System.out.println("none");
    }
}
