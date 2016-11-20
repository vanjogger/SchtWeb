package com.scht.common;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


/***
 * 键值方式的配置操作类
 * 
 * @author gq
 * 
 */
public class ConfigHelper {
	
	//private static final Log log = LoggerFactory.getLogger(ConfigHelper.class);
	private String FilePath = ConfigHelper.class.getResource("/").getPath() 
			+ "Config.xml";
	private static ConfigHelper instance = null;
	private static Document doc = null;
	private static Element root = null;
	public static ConfigHelper GetInstance() {
		if (instance == null) {
			synchronized (ConfigHelper.class) {
				if (instance == null) {
					instance = new ConfigHelper();
				}
			}
		}
		return instance;
	}

	private ConfigHelper() {
		//log.info("path is {}",FilePath);
		File file = new File(FilePath);
		if (file.exists()) {
			try {
				SAXBuilder saxBuilder = new SAXBuilder();
				doc = saxBuilder.build(new FileInputStream(new File(FilePath)));
			} catch (Exception e) {
			//	log.error("在解析文件时出现错误了{}",e);
				doc = new Document();
			}
		}
		root = doc.getRootElement();
		if (root == null || !root.getName().equals("root")) {
			doc.removeContent();
			root = new Element("root");
			doc.setRootElement(root);
		}
	}

	private static final Object lock_w = new Object();

	/***
	 * 设置值
	 * 
	 * @param key
	 * @param value
	 */
	public void SetConfig(String key, String value) {
		synchronized (lock_w) {
			try {
				Element temp = root.getChild(key);
				if (temp == null) {
					temp = new Element(key).setText(value);
					root.addContent(temp);
				} else{
					temp.setText(value);
				}
				XMLOutputter outputter = null;
				Format format = Format.getCompactFormat();
				format.setEncoding("utf-8");
				format.setIndent("    ");
				outputter = new XMLOutputter(format);
				outputter.output(doc, new FileOutputStream(FilePath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static final Object lock_r = new Object();

	/***
	 * 获取值
	 * 
	 * @param key
	 * @param
	 * @return
	 */
	public String GetConfig(String key) {
		return GetConfig(key, null);
	}

	/***
	 * 获取值
	 * 
	 * @param key
	 * @param def_value
	 * @return
	 */
	public String GetConfig(String key, String def_value) {
		synchronized (lock_r) {
			Element temp = root.getChild(key);
			if (temp != null) {
				return temp.getText();
			} else {
				SetConfig(key, def_value);
				return def_value;
			}
		}
	}

}
