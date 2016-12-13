package com.scht.front.util;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class SmsUtil {

	private static String account = "";
	private static String password = "";
	private static String SEND_URL = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	private static String QUERY_URL = "http://106.ihuyi.cn/webservice/sms.php?method=GetNum";

	/**
	 * 转换xml字符串为Document
	 * 
	 * @param str
	 * @return
	 */
	public static Document parseStrToDoc(String str) {
		try {
			StringReader read = new StringReader(str);
			InputSource is = new InputSource(read);

			SAXBuilder builder = new SAXBuilder();
			return builder.build(is);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <?xml version="1.0" encoding="utf-8"?> <SubmitResult
	 * xmlns="http://106.ihuyi.cn/"> <code>2</code> <msg>提交成功</msg>
	 * <smsid>247139912</smsid> </SubmitResult>
	 * 
	 * @param result
	 * @return
	 */
	public static Map parseResult(String result) {
		Map map = new HashMap();
		map.put("code", "0");
		map.put("msg", "发送失败");
		try {
			Document doc = parseStrToDoc(result);
			Element root = doc.getRootElement();
			System.out.println("--   "+root.getName());
			List<Element> list = root.getChildren();
			for(Element e:list){
				map.put(e.getName(),e.getText());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static Map sendSms(String mobile, String content) {
		try {
			String param = "account=" + account + "&password=" + password
					+ "&mobile=" + mobile + "&content=" + content;
			String result = HttpUrlConnection.doPostWithParams(SEND_URL, param);
			return parseResult(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static Map queryNum(){
		try {
			String param = "account=" + account + "&password=" + password;
			//param = "account=cf_maxinghua&password=maxinghua0125";
			System.out.println(QUERY_URL);
			String result = HttpUrlConnection.doPostWithParams(QUERY_URL, param);
			System.out.println(result);
			return parseResult(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	public static void main(String[] args) {
		System.out.println(queryNum());
		System.out.println(sendSms("18706645733","您的验证码是：【1234】。请不要把验证码泄露给其他人。"));
	}

}
