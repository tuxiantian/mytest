package com.tuxt.mytest.thread.share;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A extends Base{

	public void repair(){
		List<Map<String, Object>> value=new ArrayList<>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("key", "value");
		value.add(map);
		monitorMap.put("wxbdj", value);
	}
}
