package com.tuxt.mytest.thread.share;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Base {

	protected	static ConcurrentHashMap<String,List<Map<String, Object>>> monitorMap = new ConcurrentHashMap<String, List<Map<String,Object>>>();
}
