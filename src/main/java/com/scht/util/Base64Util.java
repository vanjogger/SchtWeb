package com.scht.util;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Administrator on 2014/10/29.
 */
public class Base64Util {
    //加密
    public static String encodeStr(String str) {

        return Base64.encodeBase64String(str.getBytes());
    }

    public static String encodeByte(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    // 解密
    public static String decodeStr(String s) {

        return new String(Base64.decodeBase64(s));
    }


    public static byte[] decode2Byte(String is) {
        return Base64.decodeBase64(is);
    }

    public static void main(String[] args) {
        String beianinfo = "uhhyDBcTSc5Wz8SBcZ4hcA==";

        String key = "4YEV79RRVU1Y6JAE";
        String offset = "L75ZZ35PZLIGHXHT";
        String beianhash = "UkpQeCF4mYAhqIuM1Mjc2A==";
        try {
            String hash = StringUtil.asHex(Base64.decodeBase64(beianhash));
            //byte[] b =  Base64.decodeBase64(beianinfo);
            byte[] b = AESUtil.decrypt(key, offset, Base64.decodeBase64(beianinfo));

            String dc = MD5Util.getMD5ofStr(b);
            //System.out.println(dc+" : "+hash+" : "+dc.equals(hash));

            // String str = Base64Util.encodeByte(StringUtil.hex2byte(MD5Util.getMD5ofStr("c6de45d3p7y2zsj0tzsit6z8ki86")));
            //  String str1 = Base64.encodeBase64String(StringUtil.hex2byte(MD5Util.getMD5ofStr("c6de45d3p7y2zsj0tzsit6z8ki86")));
            //  System.out.println(str);
            // System.out.println(str1);
            // System.out.println(str.equals(str1));
            byte[] bb = ZipUtil.unZip(b);
            System.out.println("===" + new String(bb, "GBK"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
