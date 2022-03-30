package com.tuxt.mytest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String[] args) {
		 Pattern pattern = Pattern.compile("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");
		  Matcher matcher = pattern.matcher("412829199109093219");
		  boolean b= matcher.matches();
		  System.out.println(b);
		
	}

}
