package com.tuxt.mytest.algorithm.queue;
 
public class ProcessManagement {
 
	public static void main(String[] args) throws Exception {
		PriorityQueue pm=new PriorityQueue();
		pm.offer(new PriorityQData(1, 20));
		pm.offer(new PriorityQData(2, 30));
		pm.offer(new PriorityQData(3, 20));
		pm.offer(new PriorityQData(4, 20));
		pm.offer(new PriorityQData(5, 40));
		pm.offer(new PriorityQData(6, 10));
		pm.peek();
		pm.display();
	}
 
}