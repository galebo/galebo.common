package com.galebo.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;


public class UtilsString {
	public static Map<String,String> string2Map(String in) {
		Map<String,String> map=new HashMap<String,String>();
		if(StringUtils.isNotBlank(in)){
			String[] strings=in.split(",");
			for(String one:strings){
				if(StringUtils.isNotBlank(one)){
					String[] one2=one.split("=");
					if(one2!=null && one2.length==2){
						map.put(one2[0].trim(), one2[1].trim());
					}
				}
			}
		}
		return map;
	}
	public static String getString(Object obj){
		if(obj==null){
			return "";
		}else{
			return obj.toString();
		}
	}

	public static String replaceAllWithMap(Map<String,String> map,String in){
		Iterator<Entry<String, String>> iter = map.entrySet().iterator(); 
		while (iter.hasNext()) { 
			Entry<String, String> entry = (Entry<String, String>) iter.next(); 
			String key = entry.getKey(); 
			String val = entry.getValue(); 
			in=in.replaceAll(key,val);
		}
		return in;
	}
	public static Map<String,String> copy2Map(Map<String,String> map,Map<String,String> to){
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) { 
			Entry<String, String> entry = (Entry<String, String>) iter.next(); 
			String key = entry.getKey(); 
			String val = entry.getValue(); 
			to.put(key,val);
		}
		return to;
	}
}
