package com.scht.util;

import cn.jpush.api.JPushClient;

import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSONObject;
import com.scht.admin.entity.PushSet;

import java.util.HashMap;
import java.util.Map;

/**
 * app推送消息
 * Created by Administrator on 2016/12/8.
 */
public class PushAPPUtil {

    private static final String DEFAULT_TAG = "MEMBERSHOP";

    private static final String MEMBER_TAG = "MEMBER";
    private static final String SHOP_TAG = "SHOP";

    /**
     * 别名推送，适用于单个客户端消息通知
     * @param title 消息标题
     * @param alias  别名，使用用户id
     * @param map  数据集合,里面必须有 type、 content
     *@param type 推送设备 member  买家版  shop 商家版
     * @return
     */
    public static boolean pushMessageByAlias(PushSet set,String title, String alias, Map<String,String> map, String type){

        try {
            map.put("title", title);
            Message message = Message.newBuilder().addExtra("time_to_live",86400*3)
                    .setMsgContent(String.valueOf(JSONObject.toJSONString(map))).build();
            Notification notification = Notification.newBuilder().addPlatformNotification(IosNotification.newBuilder()
                    .setSound("happy").setAlert(title).addExtras(map).disableBadge().build()).build();
                   // Notification.ios(title, map);
            Notification androidNoti = Notification.newBuilder().addPlatformNotification(AndroidNotification.newBuilder()
                    .setAlert(title).addExtras(map).build()).build();//.android(map.get("content"), title, map);
            PushPayload.Builder builder = PushPayload.newBuilder().setAudience(Audience.alias(alias));
//            PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all())
//                    .setAudience(Audience.alias(alias)).setNotification(notification)
//                    .setNotification(androidNoti).build();
            PushPayload payLoadIOS = builder.setPlatform(Platform.ios()).setMessage(message).setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
            PushPayload payLoadAndroid = builder.setPlatform(Platform.android()).setMessage(message).build();
            if("member".equalsIgnoreCase(type)){
                try {
                    new JPushClient(set.getIosMasterSecret(), set.getIosAppKey()).sendPush(payLoadIOS);
                }catch (Exception e){}
                try {
                    new JPushClient(set.getAndroidMasterSecret(), set.getAndroidAppKey()).sendPush(payLoadAndroid);
                }catch (Exception e){}
            }else  if("shop".equalsIgnoreCase(type)) {
                try {
                    new JPushClient(set.getIosMasterSecret(), set.getIosAppKey()).sendPush(payLoadIOS);
                }catch (Exception e){}
                try {
                    new JPushClient(set.getAndroidMasterSecret(), set.getAndroidAppKey()).sendPush(payLoadAndroid);
                }catch (Exception e){}
            }
            return true;
        }  catch (Exception e) {
            return false;
        }
    }

    /**
     * 标签推送消息, 推送给多个用户使用
     * @param title 消息通知标题
     * @param map  数据集合,里面必须有 type、 content
     *@param type 推送设备 member  买家版  shop 商家版
     * @param tags  标签
     * @return
     */
    public static boolean pushMessageByTags(PushSet set,String title, Map<String,String> map, String[] tags, String type){
        map.put("title", title);
        if(tags == null) {
            tags = new String[]{DEFAULT_TAG};
        }
        try {
            Message message = Message.newBuilder().addExtra("time_to_live", 86400 * 3)
                    .setMsgContent(JSONObject.toJSONString(map).toString()).build();
            Notification notification = Notification.newBuilder().addPlatformNotification(IosNotification.newBuilder()
            .setSound("happy").addExtras(map).setAlert(title).disableBadge().build()).build();
                    //Notification.ios(title, map) ;
            Notification androidNoti = Notification.newBuilder().addPlatformNotification(AndroidNotification.newBuilder()
            .setAlert(title).addExtras(map).build()).build();//.android(map.get("content"), title, map);
            PushPayload.Builder builder = PushPayload.newBuilder().setAudience(Audience.tag(tags));
            PushPayload pushPayLoadIOS = builder.setPlatform(Platform.ios()).setMessage(message).setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
            PushPayload pushPayLoadAndroid = builder.setPlatform(Platform.android()).setMessage(message).build();
//            PushPayload pushPayload = PushPayload.newBuilder().setPlatform(Platform.all())
//                    .setAudience(Audience.tag(tags)).setNotification(notification).setNotification(androidNoti)
//                    .build();
            if("member".equalsIgnoreCase(type)){
                try {
                    new JPushClient(set.getIosMasterSecret(), set.getIosAppKey()).sendPush(pushPayLoadIOS);
                }catch (Exception e){}
                try {
                    new JPushClient(set.getAndroidMasterSecret(), set.getAndroidAppKey()).sendPush(pushPayLoadAndroid);
                }catch (Exception e){}
            }else  if("shop".equalsIgnoreCase(type)) {
                try {
                    new JPushClient(set.getIosMasterSecret(), set.getIosAppKey()).sendPush(pushPayLoadIOS);
                }catch (Exception e){}
                try {
                    new JPushClient(set.getAndroidMasterSecret(), set.getAndroidAppKey()).sendPush(pushPayLoadAndroid);
                }catch (Exception e){}
            }else{
                try {
                    new JPushClient(set.getIosMasterSecret(), set.getIosAppKey()).sendPush(pushPayLoadIOS);
                }catch (Exception e){}
                try {
                    new JPushClient(set.getAndroidMasterSecret(), set.getAndroidAppKey()).sendPush(pushPayLoadAndroid);
                }catch (Exception e){}
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args){
        Map<String,String> map = new HashMap<>();
        map.put("title", "titlesss");
        map.put("content", "contentss");
        PushSet set = new PushSet();
        set.setAndroidAppKey("5e5ccec24cf8f0a233afec0c");
        set.setAndroidMasterSecret("5399ab07ba2d480603511b79");
        set.setIosAppKey("5e5ccec24cf8f0a233afec0c");
        set.setIosMasterSecret("5399ab07ba2d480603511b79");
        pushMessageByTags(set, map.get("title"), map, new String[]{DEFAULT_TAG}, null);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(map));
    }
}
