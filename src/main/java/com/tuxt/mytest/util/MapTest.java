package com.tuxt.mytest.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapTest {
	static Map<String, Object> map=new HashMap<String, Object>();
	static{
		map.put("a", "a1");
		map.put("b", "b1");
	}
	public static void testAdd(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("a", "a1");
		map.put("b", "b1");
		for(Map.Entry<String, Object> entry:map.entrySet()){
			if ("a".equals(entry.getKey())) {
				//map.put("c", "c1"); 正常
			}
			//抛异常
			map.put("c", "c1");
		}
		System.out.println(map);
	}
	public static void testRepair(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("a", "a1");
		map.put("b", "b1");
		for(Map.Entry<String, Object> entry:map.entrySet()){
			if ("a".equals(entry.getKey())) {
				//map.put("a", "a11"); 正常
			}
			// 正常
			map.put("a", "a11");
		}
		System.out.println(map);
	}
	public static void testRemove(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("a", "a1");
		map.put("b", "b1");
		for(Map.Entry<String, Object> entry:map.entrySet()){
			if ("a".equals(entry.getKey())) {
				//1 map.remove("a"); 正常
				//map.remove("a");
				//map.remove("c");
			}
			//2 map.remove("a"); 抛异常
			//3 抛异常
			/*if (map.containsKey("a")) {
				map.remove("a");
			}*/
		}
		System.out.println(map);
	}
	public static void testHashMap(){
		//testAdd();
		testRepair();
		//testRemove();
	}
	public static void testRemoveConcurrentHashMap(){
		Map<String, Object> map=new ConcurrentHashMap<String, Object>();
		map.put("a", "a1");
		map.put("b", "b1");
		for(Map.Entry<String, Object> entry:map.entrySet()){
			//map.remove("a");
			if (map.containsKey("a")) {
				map.remove("a");
			}
		}
		System.out.println(map);
	}
	public static void testRepairConcurrentHashMap(){
		Map<String, Object> map=new ConcurrentHashMap<String, Object>();
		map.put("a", "a1");
		map.put("b", "b1");
		for(Map.Entry<String, Object> entry:map.entrySet()){
			map.put("a", "a2");
		}
		System.out.println(map);
	}
	public static void main(String[] args) {
		//testRemoveConcurrentHashMap();
		//testRepairConcurrentHashMap();
		/*for (int i = 0; i < 5; i++) {
			new TestMapWithThread().start();
		}*/
		//testRemove();
		MapTest testMap=new MapTest();
		ExecutorService executorService=Executors.newFixedThreadPool(2);
		executorService.submit(testMap.new ReadMap());
		executorService.submit(testMap.new WriteMap());
		/*testMap.new ReadMap().start();
		testMap.new WriteMap().start();*/
	}
	class ReadMap extends Thread{
		@Override
		public void run() {
			
				for(Map.Entry<String, Object> entry:map.entrySet()){
					if ("a".equals(entry.getKey())) {
						while (true) {
							entry.getKey();
						}
					}
				}
				System.out.println(map);
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
		}
	}
	class WriteMap extends Thread{
		@Override
		public void run() {
			
			for(Map.Entry<String, Object> entry:map.entrySet()){
				if ("a".equals(entry.getKey())) {
					map.put("a", "a11");
				}
			}
			System.out.println("write:"+map);
		}
	}
}

class TestMapWithThread extends Thread{
	public void testRemove(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("a", "a1");
		map.put("b", "b1");
		for(Map.Entry<String, Object> entry:map.entrySet()){
			if ("a".equals(entry.getKey())) {
				//1 map.remove("a"); 正常
				map.remove("a");
			}
		}
		System.out.println(map);
	}
	@Override
	public void run() {
		testRemove();
	}
}