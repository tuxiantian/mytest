package com.tuxt.mytest.fastjson;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Fastjson {

	public static void main(String[] args) {
		JSONArray jsonArray=new JSONArray();
		Map<String, Object> map1=new HashMap<String, Object>();
		map1.put("name", "tom");
		map1.put("age", 20);
		JSONObject jsonObject1=new JSONObject(map1);
		System.out.println(jsonObject1.toJSONString());
		Map<String, Object> map2=new HashMap<String, Object>();
		map2.put("name", "jack");
		map2.put("age", 22);
		JSONObject jsonObject2=new JSONObject(map2);
		jsonArray.add(jsonObject1);
		jsonArray.add(jsonObject2);
		System.out.println(jsonArray.toJSONString());
		//迭代json数组
		for (int i = 0; i < jsonArray.size(); i++) {
			System.out.println(jsonArray.get(i));
		}
		
	}

}
