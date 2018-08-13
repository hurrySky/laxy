package com.sbs.base.music;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 *  获得 get请求的  dom 对象
	 * @param url
	 * @param map
	 * @return
	 */

	public static Document getDocument(String url, Map<String, String> map) {
		Connection connection = Jsoup.connect(url);
		Iterator it = map.entrySet().iterator();
		Document doc = null;
		while (it.hasNext()) {
		   Map.Entry entry = (Map.Entry) it.next();
		   String key = entry.getKey().toString();
		   String value = entry.getValue().toString();
		   connection.header(key, value);
		}
		try {
			doc = connection.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc; 
	}

}
