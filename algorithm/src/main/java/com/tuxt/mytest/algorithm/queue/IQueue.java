package com.tuxt.mytest.algorithm.queue;
 
public interface IQueue {
	public void clear();
 
	public boolean isEmpty();
 
	public int length();
 
	public Object peek();// 取队首元素
 
	public void offer(Object x) throws Exception;// 入队
 
	public Object poll();// 出队
 
	public void display();
}