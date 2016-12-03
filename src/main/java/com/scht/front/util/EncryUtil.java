package com.scht.front.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.*;

import org.apache.commons.codec.binary.Base64;

public class EncryUtil {
	
	private static String PADDING_MODEL = "AES/CBC/PKCS5Padding";
	
	 /**
    *
    * @param strKey 密钥
    * @param offset 偏移量
    * @param strIn  待加密字符串
    * @return
    * @throws Exception
    */
	private static byte[] encryptAES(String strKey, String offset,byte[] strIn) throws Exception {
       SecretKeySpec skeySpec = new SecretKeySpec(strKey.getBytes(), "AES");
       Cipher cipher = Cipher.getInstance(PADDING_MODEL);
       IvParameterSpec iv = new IvParameterSpec(offset.getBytes());
       cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

       return cipher.doFinal(strIn);
   }
   
  /**
   * 解码
   * @param strKey
   * @param offset
   * @param str
   * @return
   * @throws Exception
   */
	private static byte[] decryptAES(String strKey,String offset, byte[] str) throws Exception {

       SecretKeySpec key = new SecretKeySpec(strKey.getBytes(),"AES");
       Cipher cipher = Cipher.getInstance(PADDING_MODEL);
        IvParameterSpec spec = new IvParameterSpec(offset.getBytes());
       cipher.init(Cipher.DECRYPT_MODE, key,spec);

       return  cipher.doFinal(str);
   }
	
   
   //加密
	private static String encodeStr(byte[] b) {
       return  Base64.encodeBase64String(b);
   }
   
   //解密
	private static byte[] decode2Byte(String is) {
       return Base64.decodeBase64(is);
   }
   
   /**
    * 加密方式为 AES加密-Base64加密
    * @return
 * @throws Exception 
    */
   public static String encry(String strKey,String offset,String content) throws Exception{
	   return encodeStr(encryptAES(strKey, offset, content.getBytes()));
   }
   
   /**
    * 解密方式为  base64解密--AES解密
    * @param strKey
    * @param offset
    * @param content
    * @return
 * @throws Exception 
    */
   public static String decode(String strKey,String offset,String content) throws Exception{
	   return new String(decryptAES(strKey, offset, decode2Byte(content)));
   }
   
   
   
   public static void main(String[] args) {
	   	String strKey = "4AREA79RRVUYTOUR";
	   	String offset = "AREAZ35PZLIGTOUR";
	   	String content = "Area_tour_01";
	   	try {
			String enstr = encry(strKey, offset, content);
			System.out.println("-----  "+enstr);
			
			String destr = decode(strKey, offset, enstr);
			System.out.println("+++  "+destr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
}
