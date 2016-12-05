/**
 * @(#) ZyUnicode.java    2014/10/30 09:54
 *
 * 鐗堟潈鎵?鏈? (c) 鍖椾含閾惰蒋缃戠粶鎶?鏈湁闄愬叕鍙?
 * 鍖椾含甯傛捣娣?鍖轰笂鍦板浗闄呭垱涓氬洯瑗垮尯1鍙?
 * 淇濈暀鎵?鏈夋潈鍒?
 */
package com.scht.util.PayUtil;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 张战军
 * @version 1.0
 */
public class ZyUnicode {
    //unicode转为本地
    public static String ascii2Native(String str) {
        StringBuilder sb = new StringBuilder();
        int begin = 0;
        int index = str.indexOf("\\u");
        while (index != -1) {
            sb.append(str.substring(begin, index));
            sb.append(ascii2Char(str.substring(index, index + 6)));
            begin = index + 6;
            index = str.indexOf("\\u", begin);
        }
        sb.append(str.substring(begin));
        return sb.toString();
    }

    private static char ascii2Char(String str) {
        if (str.length() != 6) {
            throw new IllegalArgumentException(
                    "Ascii string of a native character must be 6 character.");
        }
        if (!"\\u".equals(str.substring(0, 2))) {
            throw new IllegalArgumentException(
                    "Ascii string of a native character must start with \"\\u\".");
        }
        String tmp = str.substring(2, 4);
        int code = Integer.parseInt(tmp, 16) << 8;
        tmp = str.substring(4, 6);
        code += Integer.parseInt(tmp, 16);
        return (char) code;
    }
}
