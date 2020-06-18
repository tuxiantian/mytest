package com.tuxt.mytest.thread.share;

import java.util.List;
import java.util.Map;



public class B extends Base{

	public void get(String key){
		List<Map<String, Object>> maps=monitorMap.get(key);
		System.out.println(maps);
	}
}
