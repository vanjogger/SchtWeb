package com.scht.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2016/12/8.
 */
public class OrderUtil {

    private static final int CODE_SIZE = 3;

    /**
    * 生成消费券密码
    * @return
            */
    public static String getUniqueCode() {
        StringBuffer keyBuffer = new StringBuffer();
        for (int i = 0; i < CODE_SIZE; ++i) {
            keyBuffer.append(createRandom());
            if (i != CODE_SIZE - 1) {
                keyBuffer.append("-");
            }
        }
        return keyBuffer.toString();
    }

    /**
     * 生成订单流水号
     * @return
     */
    public static String createNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String no = sdf.format(date);
        Random rm = new Random();
        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, 4);
        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);
        // 返回固定的长度的随机数
        return no + fixLenthString.substring(1, 4 + 1);
    }

    private static String createRandom() {
        Random rm = new Random();
        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, 4);
        // 将获得的获得随机数转化为字符串
        String fixLengthString = String.valueOf(pross);
        // 返回固定的长度的随机数
        return fixLengthString.substring(1, 4 + 1);
    }
}
