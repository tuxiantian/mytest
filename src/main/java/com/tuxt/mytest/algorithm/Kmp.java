package com.tuxt.mytest.algorithm;

public class Kmp {

	public static void recursive(String src,String sub) {
		int i = 0,mark=i,total=0;
		for (int j = 0; j < sub.length(); j++) {
			if(src.charAt(i)==sub.charAt(j)){
				i++;
				total++;
			}
			else {
				mark=i;
				total=0;
				break;
			}
		}
		if (total==sub.length()-1) {
			System.out.println("在"+i+"位置找到了。");
		}else {
			recursive(src.substring(1), sub);
		}
	}
	public static void main(String[] args) {
		String src="BBC ABCDAB ABCDABCDABDE",sub="ABCDABD";
		System.out.println(src.substring(1));
		System.out.println(src.indexOf(sub));
//		recursive(src, sub);
	}

}
