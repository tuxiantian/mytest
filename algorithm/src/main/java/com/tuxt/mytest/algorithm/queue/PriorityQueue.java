package com.tuxt.mytest.algorithm.queue;
 

public class PriorityQueue implements IQueue {
 
	private PriorityQData front;//队首的引用
	private PriorityQData rear;//队尾的引用
	
	
	public PriorityQueue() {
		front=rear=null;
	}
 
	@Override
	public void clear() {
		front=rear=null;
	}
 
	@Override
	public boolean isEmpty() {
		return front==null;
	}
 
	@Override
	public int length() {
		int length=0;
		while(front!=null){
			front=front.next;
			length++;
		}
		return length;
	}
 
	@Override
	public Object peek() {
		PriorityQData head = front;
		front = front.next;
		return head;
	}
 
	@Override
	public void offer(Object x) throws Exception {
		PriorityQData s=(PriorityQData)x;
		if(front==null){
			front=rear=s;
		}
		else {
			PriorityQData p=front;
			PriorityQData q=front;
			while(p!=null&&s.priority <= p.priority){
				q=p;
				p=p.next;
			}
			if(p==null){
				rear.next=s;
				rear=s;
			}
			else if (p==front) {
				s.next=front;
				front=s;
			}
			else {
				//s > q > p
				q.next=p;
				s.next=q;
			}
		}
	}

	@Override
	public Object poll() {
		return front;
	}
 
	@Override
	public void display() {
		if(!isEmpty()){

			while(front!=rear.next){
				PriorityQData q=front;
				System.out.println(q.elem+" "+q.priority);
				front=front.next;
			}
		}
		else {
			System.out.println("此队列为空");
		}
	}
 
}