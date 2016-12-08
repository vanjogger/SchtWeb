package com.scht.util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.scht.admin.entity.PushSet;
import net.sf.json.JSONObject;

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
                    .setMsgContent(String.valueOf(JSONObject.fromObject(map))).build();
            Notification notification = Notification.ios(title, map);
            Notification androidNoti = Notification.android(map.get("content"), title, map);
            PushPayload.Builder builder = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias));
//            PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all())
//                    .setAudience(Audience.alias(alias)).setNotification(notification)
//                    .setNotification(androidNoti).build();
            PushPayload payLoadIOS = builder.setNotification(notification).build();
            PushPayload payLoadAndroid = builder.setNotification(androidNoti).build();
            if("member".equalsIgnoreCase(type)){
                new JPushClient(set.getIosMasterSecret(),set.getIosAppKey()).sendPush(payLoadIOS);
                new JPushClient(set.getAndroidMasterSecret(),set.getAndroidAppKey()).sendPush(payLoadAndroid);
            }else  if("shop".equalsIgnoreCase(type)) {
                new JPushClient(set.getIosMasterSecret(),set.getIosAppKey()).sendPush(payLoadIOS);
                new JPushClient(set.getAndroidMasterSecret(),set.getAndroidAppKey()).sendPush(payLoadAndroid);
            }
            return true;
        } catch (APIConnectionException e) {
            System.out.println("connect error");
            return false;
        } catch (APIRequestException e) {
            System.out.println("http status : " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
            System.out.println("Msg ID: " + e.getMsgId());
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
                    .setMsgContent(JSONObject.fromObject(map).toString()).build();
            Notification notification = Notification.ios(title, map) ;
            Notification androidNoti = Notification.android(map.get("content"), title, map);
            PushPayload.Builder builder = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.tag(tags));
            PushPayload pushPayLoadIOS = builder.setNotification(notification).build();
            PushPayload pushPayLoadAndroid = builder.setNotification(androidNoti).build();
//            PushPayload pushPayload = PushPayload.newBuilder().setPlatform(Platform.all())
//                    .setAudience(Audience.tag(tags)).setNotification(notification).setNotification(androidNoti)
//                    .build();
            if("member".equalsIgnoreCase(type)){
                new JPushClient(set.getIosMasterSecret(),set.getIosAppKey()).sendPush(pushPayLoadIOS);
                new JPushClient(set.getAndroidMasterSecret(),set.getAndroidAppKey()).sendPush(pushPayLoadAndroid);
            }else  if("shop".equalsIgnoreCase(type)) {
                new JPushClient(set.getIosMasterSecret(),set.getIosAppKey()).sendPush(pushPayLoadIOS);
                new JPushClient(set.getAndroidMasterSecret(),set.getAndroidAppKey()).sendPush(pushPayLoadAndroid);
            }else{
                new JPushClient(set.getIosMasterSecret(),set.getIosAppKey()).sendPush(pushPayLoadIOS);
                new JPushClient(set.getAndroidMasterSecret(),set.getAndroidAppKey()).sendPush(pushPayLoadAndroid);

            }
            return true;
        } catch (APIConnectionException e) {
            System.out.println("connect error");
            return false;
        } catch (APIRequestException e) {
            System.out.println("http status : " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
            System.out.println("Msg ID: " + e.getMsgId());
            return false;
        }
    }

    public static void main(String[] args){
        Map<String,String> map = new HashMap<>();
        map.put("title", "titlesss");
        map.put("content", "contentss");
        System.out.println(JSONObject.fromObject(map));
    }
}
