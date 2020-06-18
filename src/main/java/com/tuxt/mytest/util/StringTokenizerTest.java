package com.tuxt.mytest.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringTokenizerTest {

	public static void main(String[] args) {
		String oldString="100|371|200";
		StringTokenizer tokenizer=new StringTokenizer(oldString, "|");
		String[] prov=oldString.split("|");
		System.out.println(tokenizer.countTokens());
		List<String> pList=new ArrayList<String>();
		while (tokenizer.hasMoreElements()) {
			pList.add(String.valueOf(tokenizer.nextElement()));
		}
		System.out.println(pList.toString());
		
	}

}
