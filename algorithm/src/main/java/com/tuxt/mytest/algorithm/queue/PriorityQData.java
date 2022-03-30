package com.tuxt.mytest.algorithm.queue;
 
public class PriorityQData {
	//结点的data类
	public Object elem;//结点的数据元素值
	public int priority;//结点的优先级
	public PriorityQData next;
	public PriorityQData(Object elem, int priority) {
		this.elem = elem;
		this.priority = priority;
	}
}