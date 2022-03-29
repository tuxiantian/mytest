package com.tuxt.mytest.thread;

import java.util.concurrent.Callable;

public class AThrad implements Callable<String>{

	@Override
	public String call() throws Exception {
		Thread.currentThread().sleep(10000);
		return "run end";
	}
	
}
