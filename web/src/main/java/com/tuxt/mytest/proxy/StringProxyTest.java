package com.tuxt.mytest.proxy;

public class StringProxyTest {
	public static void main(String[] args) {
		/*java.lang.IllegalArgumentException: Cannot subclass final class java.lang.String*/
		StringProxy proxy=new StringProxy();
		String string=(String) proxy.getInstance(new String("abc"));
		System.out.println(string.length());
	}

}
