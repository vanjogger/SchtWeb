package com.scht.common;

import java.util.Hashtable;
import java.util.Set;

/***
 * 字典类实体
 * @author gq
 *
 */
public class DictionaryEntity {

    public DictionaryEntity(String key, String value)
    {
        this.Key = key;
        this.Value = value;
    }
    private String Key = "";
    private String Value = "";
    private Hashtable<String,String> Attributes = new Hashtable<String,String>();
	public String getKey() {
		return Key;
	}
	public void setKey(String key) {
		Key = key;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public String getAttributes(String key) {
		return Attributes.containsKey(key)?Attributes.get(key):null;
	}
	public String getAttributes(String key,String def_value) {
		return Attributes.containsKey(key)?Attributes.get(key):def_value;
	}
	public void setAttributes(String key,String value) {
		Attributes.put(key, value);
	}
   public Set<String> GetAttributesKeySet()
   {
	   return Attributes.keySet();
   }
   

}
