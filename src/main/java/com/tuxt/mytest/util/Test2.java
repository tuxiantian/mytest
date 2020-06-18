package com.tuxt.mytest.util;

import java.util.ArrayList;
import java.util.List;

public class Test2 {

	public static void main(String[] args) {
		List<String> colList=new ArrayList<String>();
		colList.add("a");
		colList.add("b");
		colList.add("c");
		String[] columns=colList.toArray(new String[colList.size()]);
		System.out.println(columns);
	}

}
