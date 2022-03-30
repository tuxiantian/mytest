package com.tuxt.mytest.skill;

import java.util.Collections;
import java.util.List;
/**
 * 当集合为空的时候要返回Collections.emptyList()，而不要返回null,这样就可以避免NullPointerException.
 * @author tuxiantian@163.com
 *
 */
public class NoReturnNull {

	public List<String> getStringCollection() {
		return Collections.emptyList();
	}
	
	public static void main(String[] args) {
		NoReturnNull noReturnNull=new NoReturnNull();
		System.out.println(noReturnNull.getStringCollection().size());
	}

}
