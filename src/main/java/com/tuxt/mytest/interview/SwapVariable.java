package com.tuxt.mytest.interview;

public class SwapVariable {
	
	public static void method1(){
		int a=3,b=4;
		System.out.println("swap before:"+"a="+a+",b="+b);
		a=a+b;
		b=a-b;
		a=a-b;
		System.out.println("swap after:"+"a="+a+",b="+b);
	}
	public static void method2(){
		int a=3,b=4;
		System.out.println("swap before:"+"a="+a+",b="+b);
		a=a^b;
		b=a^b;//a^b^b
		a=a^b;//a^b^a^b^b
		System.out.println("swap after:"+"a="+a+",b="+b);
	}
	public static void main(String[] args) {
		method2();
	}

}
