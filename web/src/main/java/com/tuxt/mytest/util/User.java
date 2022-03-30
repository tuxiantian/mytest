package com.tuxt.mytest.util;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public User query(User user) {
		user.setAge(11);
		user.setName("tom");
		try {
			throw new RuntimeException();
		} catch (Exception e) {
		}
		return user;
	}
	public static void test1(){
		
		List<String> list=new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			if (!"c".equals(list.get(i))) {
				list.remove(i);
				//i--;
				continue;
			}else {
				break;
			}
		}
		System.out.println(list);
	}
	public static void test2(){
		
		List<String> list=new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		int index=list.indexOf("c");
		if (index>0) {
			String temp=list.get(0);
			list.set(0, list.get(index));
			list.set(index, temp);
		}
		/*for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			if (!"c".equals(list.get(i))) {
				list.remove(i);
				//i--;
				continue;
			}else {
				break;
			}
		}*/
		System.out.println(list);
	}
	public static void main(String[] args) {
		test2();
	}
}
