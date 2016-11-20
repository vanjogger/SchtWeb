package com.scht.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Administrator on 2014/11/10.
 */
public class AESUtil {

    /**
     * @param strKey 密钥
     * @param offset 偏移量
     * @param strIn  待加密字符串
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(String strKey, String offset, byte[] strIn) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(strKey.getBytes(), "AES");
        //getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(offset.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        return cipher.doFinal(strIn);
    }

    //对十六进制解码
    public static byte[] decrypt(String strKey, String offset, byte[] str) throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
       /* generator.init(128,new SecureRandom(strKey.getBytes()));

        AlgorithmParameterSpec spec = new IvParameterSpec(offset.getBytes());;
        SecretKey key = generator.generateKey();*/

        SecretKeySpec key = new SecretKeySpec(strKey.getBytes(), "AES");
        //getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec spec = new IvParameterSpec(offset.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, key, spec);

        return cipher.doFinal(str);
    }

    private static SecretKeySpec getKey(String strKey) throws Exception {
        byte[] arrBTmp = strKey.getBytes();
        byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");

        return skeySpec;
    }


    public static void main(String[] args) {
        String key = "4YEV79RRVU1Y6JAE";
        String offset = "L75ZZ35PZLIGHXHT";
        String str = "test";
        try {
            byte[] enc = encrypt(key, offset, str.getBytes());
            System.out.println(StringUtil.asHex(enc));
         /*  byte[] dec = decrypt(key,offset,enc);
           System.out.println(new String(dec));*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
