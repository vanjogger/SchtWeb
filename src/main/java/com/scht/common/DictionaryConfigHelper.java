package com.scht.common;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/***
 * 字典信息
 * @author gq
 *
 */
public class DictionaryConfigHelper {
	private static Hashtable<String, Hashtable<String, List<DictionaryEntity>>> Temp_CacheList = new Hashtable<String, Hashtable<String, List<DictionaryEntity>>>();
	private static Hashtable<String, Document> _DocumentList = new Hashtable<String, Document>();


	/***
	 * 获取字典信息
	 * @param TagName 节点
	 * @param FileName 文件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<DictionaryEntity> getDictionaryEntityList(
			String TagName, String FileName) {
		if (FileName == null || FileName.trim().equals("")) {
			FileName =DictionaryConfigHelper.class.getResource("/").getPath()+File.separator + "Dictionary.xml";//默认文件名
		}

		if (Temp_CacheList.containsKey(FileName)) {
			if (Temp_CacheList.get(FileName).containsKey(TagName)) {
				return Temp_CacheList.get(FileName).get(TagName);
			}
		}
		List<DictionaryEntity> _DictionaryList = new ArrayList<DictionaryEntity>();

		if (!_DocumentList.containsKey(FileName)) {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc;
			try {
				doc = saxBuilder.build(new FileInputStream(new File(FileName)));

				_DocumentList.put(FileName, doc);
			} catch (JDOMException e) {

				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		List<Element> nodelist = _DocumentList.get(FileName).getRootElement()
				.getChild(TagName).getChildren();
		for (Element node : nodelist) {
			DictionaryEntity _DictionaryEntity = new DictionaryEntity(
					node.getAttributeValue("Key"), node.getText());

			for (Attribute obj : (List<Attribute>) node.getAttributes()) {
				_DictionaryEntity.setAttributes(obj.getName(), obj.getValue());
			}

			_DictionaryList.add(_DictionaryEntity);
		}

		return _DictionaryList;
	}
	public static void save(String TagName,List<DictionaryEntity> list)
	{
	save( TagName, list,null);
	
	}
	public static void save(String TagName, List<DictionaryEntity> list,String FileName)
	{
		if (FileName == null || FileName.trim().equals("")) {
			FileName = DictionaryConfigHelper.class.getResource("/").getPath()+File.separator + "Dictionary.xml";//默认文件名+File.separator + "Dictionary.xml";//默认文件名
		}
		Document doc=null;
		if (!_DocumentList.containsKey(FileName)) {
			SAXBuilder saxBuilder = new SAXBuilder();
			
			try {
				doc = saxBuilder.build(new FileInputStream(new File(FileName)));
				_DocumentList.put(FileName, doc);
			} catch (JDOMException e) {

				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			doc=_DocumentList.get(FileName);
		}
		if(doc==null){
			return;
		}
		Format format = Format.getRawFormat();

		format.setIndent("    ");
		// 设置文档字符编码
		format.setEncoding("GB2312");
		Element tagNode=doc.getRootElement().getChild(TagName);
		tagNode.removeChildren("Item");
		
		
		XMLOutputter outputter = new XMLOutputter();
		
		outputter.setFormat(format);
		String xml = outputter.outputString(doc);
		
		
		
		for(DictionaryEntity de:list){
			Element item=new Element("Item");
			
			for(String attrkey: de.GetAttributesKeySet())
			{
				if(attrkey.equals("Key")){
					continue;
				}
				Attribute attribute2=new Attribute(attrkey, de.getAttributes(attrkey));
				item.setAttribute(attribute2);
			}
			String key=de.getKey();
			Attribute attribute=new Attribute("Key", key);
			item.setAttribute(attribute);
			
			item.setText(de.getValue());
			tagNode.addContent(item);
		}
		
		
		
		String xml2 = outputter.outputString(doc);
		
		XMLOutputter XMLOut = new XMLOutputter(format);
		// 输出到文件
		try {
			XMLOut.output(doc, new FileOutputStream(FileName));
			
			if (Temp_CacheList.containsKey(FileName)) {
				if (Temp_CacheList.get(FileName).containsKey(TagName)) {
					 Temp_CacheList.get(FileName).remove(TagName);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


	/***
	 * 获取字典（当IfNotExitsAddKey中有部分不存在时，将添加到列表并返回）
	 * @param TagName 节点
	 * @param IfNotExitsAddKey 不存在自动添加的值
	 * @param FileName 文件
	 * @return
	 */
	public static List<DictionaryEntity> getDictionaryEntityList(
			String TagName, DictionaryEntity[] IfNotExitsAddKey, String FileName) {
		List<DictionaryEntity> temp = getDictionaryEntityList(TagName, FileName);
		for (DictionaryEntity dn : IfNotExitsAddKey) {
			if (dn.getKey() == null) {
				continue;
			}
			DictionaryEntity _DictionaryEntity = getDictionaryEntity(TagName,
					dn.getKey(), FileName);
			if (_DictionaryEntity == null) {
				temp.add(dn);
			}
		}
		return temp;
	}

	/***
	 * 获取单个字典信息
	 * @param TagName 节点
	 * @param Key 键
	 * @param FileName 文件
	 * @return
	 */
	public static DictionaryEntity getDictionaryEntity(String TagName,
			String Key, String FileName) {
		List<DictionaryEntity> _DictionaryEntityList = getDictionaryEntityList(
				TagName, FileName);
		for (DictionaryEntity temp : _DictionaryEntityList) {
			if (temp.getKey().equals(Key) ) {
				return temp;
			}
		}
		return null;
	}

	
	
	/***
	 * 获取单个字典信息
	 * @param TagName 节点
	 * @param Value 值
	 * @param FileName 文件
	 * @return
	 */
	public static DictionaryEntity getDictionaryEntityByValue(String TagName,
			String Value, String FileName) {
		List<DictionaryEntity> _DictionaryEntityList = getDictionaryEntityList(
				TagName, FileName);
		for (DictionaryEntity temp : _DictionaryEntityList) {
			if (temp.getValue().equals("Value")) {
				return temp;
			}
		}
		return null;
	}


	/***
	 * 获取单个字典信息
	 * @param TagName 节点
	 * @param Value 默认值
	 * @return
	 */
	public static DictionaryEntity getDictionaryEntityByValue(String TagName,
			String Value) {
		List<DictionaryEntity> _DictionaryEntityList = getDictionaryEntityList(TagName);
		for (DictionaryEntity temp : _DictionaryEntityList) {
			if (temp.getValue() .equals(Value)) {
				return temp;
			}
		}
		return null;
	}


	/***
	 * 根据键获取值
	 * @param TagName 节点
	 *  @param Value 值
	 * @param Def_Value 默认值
	 * @param FileName 文件
	 * @return
	 */
	public static String getDictionaryKeyByValue(String TagName, String Value,
			String Def_Value, String FileName) {
		DictionaryEntity dn = getDictionaryEntityByValue(TagName, Value,
				FileName);
		if (dn != null)
			return dn.getKey();

		return Def_Value;
	}

	
	
	/***
	 * 根据键获取值
	 * @param TagName 节点
	 * @param Value 值
	 * @param Def_Value 默认值
	 * @return
	 */
	public static String getDictionaryKeyByValue(String TagName, String Value,
			String Def_Value) {

		DictionaryEntity dn = getDictionaryEntityByValue(TagName, Value);
		if (dn != null)
			return dn.getKey();

		return Def_Value;
	}

	
	
	/***
	 * 根据键获取值
	 * @param TagName 节点
	 * @param Key 键
	 * @param Def_Value 默认值
	 * @param FileName 文件
	 * @return
	 */
	public static String getDictionaryValue(String TagName, String Key,
			String Def_Value, String FileName) {

		DictionaryEntity dn = getDictionaryEntity(TagName, Key, FileName);
		if (dn != null)
			return dn.getValue();

		return Def_Value;
	}


	/***
	 * 获取字典信息
	 * @param TagName 节点
	 * @return
	 */
	public static List<DictionaryEntity> getDictionaryEntityList(String TagName) {
		return getDictionaryEntityList(TagName, null);
	}

	
	/***
	 * 获取字典（当IfNotExitsAddKey中有部分不存在时，将添加到列表并返回）
	 * @param TagName 节点
	 * @param IfNotExitsAddKey 不存在自动添加的值
	 * @return
	 */
	public static List<DictionaryEntity> getDictionaryEntityListNoAdd(
			String TagName, String[] IfNotExitsAddKey) {
		List<DictionaryEntity> temp = getDictionaryEntityList(TagName);
		for (String Key : IfNotExitsAddKey) {
			if (Key == null) {
				continue;
			}
			DictionaryEntity _DictionaryEntity = getDictionaryEntity(TagName,
					Key);
			if (_DictionaryEntity == null) {
				temp.add(new DictionaryEntity(Key, Key));
			}
		}
		return temp;
	}

	
	/***
	 * 获取单个字典信息
	 * @param TagName 节点
	 * @param Key 键
	 * @return
	 */
	public static DictionaryEntity getDictionaryEntity(String TagName,
			String Key) {
		List<DictionaryEntity> _DictionaryEntityList = getDictionaryEntityList(TagName);
		for (DictionaryEntity temp : _DictionaryEntityList) {
			if (temp.getKey().equals(Key)) {
				return temp;
			}
		}
		return null;
	}

	
	/***
	 * 根据键获取值
	 * @param TagName 节点
	 * @param Key 键
	 * @param Def_Value 默认值
	 * @return
	 */
	public static String getDictionaryValue(String TagName, String Key,
			String Def_Value) {
		DictionaryEntity dn = getDictionaryEntity(TagName, Key);
		if (dn != null)
			return dn.getValue();
		return Def_Value;
	}
}
