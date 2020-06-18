package com.tuxt.mytest.util;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test1 {

	public static void main(String[] args) {
		/*System.out.println(Math.abs(-1));
		User user=new User();
		User user2=user.query(user);
		System.out.println(user2);
		System.out.println(new Timestamp(new Date().getTime()));*/
		List<String> list=new ArrayList<String>();
		list.add(new String("a"));
		list.add(new String("b"));
		String a=new String("a");
		String a2=new String("a");
		/*if (list.contains(a)) {
			System.out.println(true);
		}*/
		System.out.println(a==a2);//false
		System.out.println(a.equals(a2));//true
		List<UserInfo> users=new ArrayList<UserInfo>();
		users.add(new UserInfo("aba",10));
		users.add(new UserInfo("abc",12));
		UserInfo user=new UserInfo("aba",10);
		UserInfo user2=new UserInfo("aba",10);
		UserInfo user3=user;
		/*if (users.contains(user)) {
			System.out.println(true);
		}*/
		System.out.println(user.equals(user2));
		System.out.println(user==user2);
		
		System.out.println(user.equals(user3));
		System.out.println(user==user3);
		BigInteger bigInteger=new BigInteger("123");
		System.out.println(bigInteger);
	}

}
class UserInfo{
	String name;
	int age;
	public UserInfo(String name,int age){
		this.name=name;
		this.age=age;
	}
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
	@Override
	public boolean equals(Object obj) {
		UserInfo info = null;
		if (obj instanceof UserInfo) {
			info=(UserInfo) obj;
		}
		return (this.getName().equals(info.getName())&&this.getAge()==info.getAge());
	}
}