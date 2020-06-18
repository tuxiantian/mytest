package com.tuxt.mytest.util;

import java.util.concurrent.ConcurrentHashMap;

public class TestTryCatch {
	/**
	 * throw之后的代码不会被执行
	 * catch之后的代码会继续执行
	 */
	public static void test1(){
		try {
			if (true) {
				throw new NullPointerException();
			}
			System.out.println("throw之后的代码执行了");
		} catch (Exception e) {
		}
		System.out.println("catch块后的代码执行了");
	}
	/**
	 * 异常的精确匹配
	 */
	public static void test2(){
		try {
			if (true) {
				throw new NullPointerException();
			}
		}catch(NullPointerException e){
			System.out.println("NullPointerException");
		}catch (Exception e) {
			System.out.println("Exception");
		}
	}
	public static void test3(){
		try {
			System.out.println(111111);
			throw new NullPointerException();
		}finally{
			
		}
		//System.out.println(22222);//此处的代码不可执行
	}
	public static void test4(){
		final ConcurrentHashMap<String,String> lockMap = new ConcurrentHashMap<String,String>();
		lockMap.put("key", "123");
		lockMap.put("key", "1234");
		System.out.println(lockMap.get("key"));
		
	}
	public static void main(String[] args) {
		test4();
	}

}
